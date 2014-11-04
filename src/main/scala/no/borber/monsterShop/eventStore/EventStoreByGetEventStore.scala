package no.borber.monsterShop.eventStore

import java.util

import _root_.eventstore.EsError.StreamNotFound
import _root_.eventstore._
import _root_.eventstore.tcp.ConnectionActor
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import com.google.gson.Gson
import no.borber.monsterShop.application.AggregateType
import no.borber.monsterShop.eventStore.eventstore.EventMetadata

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Failure

class EventStoreByGetEventStore extends EventStore{
  val system = ActorSystem("MySystem")
  val connection = system.actorOf(ConnectionActor.props())
  val gson = new Gson();

  override def store(eventsToSaveParam: util.List[Event]): Unit = {
    val eventsToSave = eventsToSaveParam.asScala
    if(eventsToSaveParam.size() < 1) throw new RuntimeException("Must be events to save");
    val aggregateId = eventsToSaveParam.get(0).getAggregateId
    val aggregateType = eventsToSaveParam.get(0).getAggregateType
    for(x: Event <- eventsToSave){
      if(x.getAggregateId != aggregateId || aggregateType != x.getAggregateType) throw new RuntimeException("All events must have same aggregateId and aggregateType");
    }

    val events = for(x : Event <- eventsToSave ) yield EventData(x.getClass().getSimpleName, data = Content.Json(gson.toJson(x)), metadata = Content.Json(gson.toJson(new EventMetadata(x.getClass, x.getAggregateId, x.getAggregateType))))


    implicit val timeout = Timeout(30 seconds)
    val future = connection ask WriteEvents(EventStream.Id(streamname(aggregateType, aggregateId)), events.toList)
    Await.result(future, Duration("30 seconds")) match {
      case WriteEventsCompleted(range, position) =>
      case Failure(e: EsException) =>
        throw e;
    }
  }

  override def subscribe(aggregateType: AggregateType, projection: Projection): Unit = {
    val of: ActorRef = system.actorOf(Props(classOf[SubscriptionActor], projection))
    system.actorOf(StreamSubscriptionActor.props(connection = connection, client = of, EventStream.Id("$ce-" + aggregateType), resolveLinkTos = true))
  }



  override def getById(aggregateType:AggregateType, id: String): util.List[Event] = {
    implicit val timeout = Timeout(30 seconds)
    val future = connection ask ReadStreamEvents(EventStream.Id(streamname(aggregateType, id)), EventNumber.First, maxCount = 10000 )
    try {
      Await.result(future, Duration("30 seconds")) match {
        case ReadStreamEventsCompleted(events, nextEventNumber, lasEventNumber, endOfStream, lastCommitPosition, direction) =>
          val result = new util.ArrayList[Event]()
          for (event <- events) {
            val metadata = gson.fromJson(event.data.metadata.value.utf8String, classOf[EventMetadata])
            result.add(gson.fromJson(event.data.data.value.utf8String, metadata.getEventClassAsClass))
          }
          return result
        case Failure(e: EsException) =>
          throw e;
      }
    } catch {
      case e: EsException => {
        if(StreamNotFound.equals(e.reason))
          return new util.ArrayList[Event]()
        else throw e;
      }
    }

  }

  def streamname(aggregateType : AggregateType, id: String ) = "" + aggregateType + "-" + id;
}

class SubscriptionActor(projection: Projection) extends Actor{

  val gson = new Gson();

  def receive = {
    case ResolvedEvent(linkedEvent,linkEvent) ⇒ {

      val metadataString: String = linkedEvent.data.metadata.value.utf8String

      val data: String = linkedEvent.data.data.value.utf8String

      val metadata = gson.fromJson(metadataString,classOf[EventMetadata])
      val event: Event = gson.fromJson(data, metadata.getEventClassAsClass)
      println("Event " + event)
      projection.handleEvent(event)
    }
    case x:Any ⇒ {
      println("OTHER " +x)
    }
  }
}