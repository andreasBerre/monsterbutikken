
import java.util.UUID

import akka.pattern.ask
import no.borber.monsterShop.application.basket.{BasketState, AddItemToBasket, BasketActor, RemoveItemFromBasket}

import scala.concurrent.Await

class BasketTest extends AkkaSpec{

  trait Actor {
    val testActor = system.actorOf(BasketActor.props(UUID.randomUUID().toString))
  }

  it should "accept command and save event" in new Actor {
    suppressStackTraceNoise {
      testActor ! AddItemToBasket("monster")
      expectMsg("ok")
      val future = testActor ? ("state")

      val lineitems = Await.result(future, timeout.duration).asInstanceOf[BasketState].getBasketLineItems.size()
      lineitems shouldEqual(1)
    }
  }

  it should "reject removeItemFromBasket that are not present" in new Actor {
    suppressStackTraceNoise {
      watch(testActor)
      testActor ! RemoveItemFromBasket("monsterremove")
      expectMsgClass(timeout.duration, classOf[Exception])
    }
  }

  it should "fail if fail is sent and respond with failure to sender" in new Actor {
    suppressStackTraceNoise {
      val future = testActor ? "fail"
      print(Await.result(future, timeout.duration))
    }
  }

}
