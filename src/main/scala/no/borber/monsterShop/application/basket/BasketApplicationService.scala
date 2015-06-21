package no.borber.monsterShop.application.basket

import akka.actor.{ActorRef, ActorSystem}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import scala.collection.mutable


@Component
class BasketApplicationService {

  @Autowired
  private var system: ActorSystem = null

  var  baskets = new mutable.HashMap[String, ActorRef];


  def this(system: ActorSystem) {
    this()
    this.system = system
  }

  def createBasket(id: String) {
    getBasketActor(id) ! CreateBasket
  }

  def getBasketActor(id: String): ActorRef = {
    baskets.getOrElseUpdate(id,system.actorOf(BasketActor.props("basket-"+id)));
  }

  def addItemToBasket(id: String, monsterType: String) {
    getBasketActor(id) ! AddItemToBasket(monsterType)
  }

  def removeItemFromBasket(id: String, monsterType: String) {
    getBasketActor(id) ! RemoveItemFromBasket(monsterType)
  }

  def checkoutBasket(basketId: String, customerId: String, orderId: String) {
    /*val basket: BasketAggregate = new BasketAggregate(eventStore.getById(basketId))
    val currentBasketLineItems: List[BasketLineItem] = basket.checkoutBasket
    val order: OrderAggregate = new OrderAggregate(eventStore.getById(orderId))
    order.createOrder(orderId, customerId, currentBasketLineItems, LocalDateTime.now)
    val derivedEvents: List[Event] = new ArrayList[Event]
    derivedEvents.addAll(basket.getDerivedEvents)
    derivedEvents.addAll(order.getDerivedEvents)
    eventStore.store(derivedEvents)*/
  }
}
