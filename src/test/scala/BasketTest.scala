
import akka.actor.Props
import akka.testkit.TestActorRef
import no.borber.monsterShop.application.basket.{AddItemToBasket, BasketActor}

class BasketTest extends AkkaSpec{

  trait Actor {
    val testActor = system.actorOf(Props[BasketActor])
  }

  it should "compute length of a string and return the result" in new Actor {
    suppressStackTraceNoise {
      testActor ! AddItemToBasket("monster")
      testActor ! "print"
      expectMsgClass(classOf[String])

    }
  }

}
