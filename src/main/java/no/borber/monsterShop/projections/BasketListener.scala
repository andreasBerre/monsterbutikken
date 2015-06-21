package no.borber.monsterShop.projections

import akka.actor.{Actor, Props}
import akka.persistence.PersistentRepr
import akka.serialization.SerializationExtension
import basket.{BasketCreated, ItemAddedToBasket, ItemRemovedFromBasket}
import eventstore._
import eventstore.tcp.ConnectionActor

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.reflect.ClassTag
import scala.util.Try

object BasketListener {
  def props = Props.create(classOf[BasketListener]);
  def getBaskLineItems(basketId: String): java.util.List[BasketLineItemInfo] = {
    val items: Iterable[BasketLineItemInfo] = basketProjection.getBasketLineItems(basketId)
    seqAsJavaListConverter(items.toSeq).asJava
  };

  var basketProjection: BasketProjection = new BasketProjection;
}

class BasketListener extends Actor{
  val connection = context.actorOf(ConnectionActor.getProps());
  val projection = BasketListener.basketProjection


  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    val connection = EventStoreExtension(context.system).actor
    context.actorOf(StreamSubscriptionActor.props(connection, self, EventStream.Id("$ce-basket"), resolveLinkTos = true), "subscription")
  }

  val serialization = SerializationExtension(context.system)


  def deserialize[A](bytes: Array[Byte])(implicit ct: ClassTag[A]): Try[A] =
    serialization.deserialize(bytes, ct.runtimeClass).asInstanceOf[Try[A]]

  override def receive: Receive = {
    case e: ResolvedEvent => {
      val classname: Class[_] = Class.forName(e.linkedEvent.data.eventType)
      println("Event received " + e.linkedEvent.data.eventType)
      val x = for {
        pr <- deserialize[PersistentRepr](e.linkedEvent.data.data.value.toArray)
      } yield pr.payload

      if(x.isSuccess) {
        x.get match {
          case e: BasketCreated => projection.handleEvent(e);
          case e: ItemAddedToBasket => projection.handleEvent(e);
          case e: ItemRemovedFromBasket => projection.handleEvent(e);
          case e: Any => println("Unhandled event " + e)
        }
        println(x.get)
      }
      else
        println("Failed to deserialize " + x);
    }

    case e: Any => println(e)
  }
}

class BasketProjection {

  val baskets = new HashMap[String, HashMap[String,BasketLineItemInfo]]

  def getBasketLineItems (basketId: String) = {
    getBasket(basketId).values;
  }

   def handleEvent (event: BasketCreated) {

  }

   def handleEvent ( event: ItemAddedToBasket) {
     val basketid: String = event.basketid.get
     val basket: mutable.HashMap[String, BasketLineItemInfo] = getBasket(basketid)
     val monstertype: String = event.monstertype.getOrElse("")
     val update: BasketLineItemInfo = basket.getOrElseUpdate(monstertype, new BasketLineItemInfo(monstertype))
     update.incrementQuantity()
  }

   def handleEvent (event: ItemRemovedFromBasket) {
     getBasket(event.getBasketid).getOrElseUpdate(event.monstertype.getOrElse(""), new BasketLineItemInfo(event.monstertype.getOrElse(""))).decrementQuantity()
  }

  def getBasket(basketId: String): mutable.HashMap[String, BasketLineItemInfo] = {
    baskets.getOrElseUpdate(basketId, new HashMap[String, BasketLineItemInfo]);
  }

   /*def handleEvent (BasketCheckedOut event) {
    if (!baskets.containsKey(event.getAggregateId()))
      throw new RuntimeException("The projection does not contain the specified basket, checkout failed");
    baskets.remove(event.getAggregateId());
  }*/
}
