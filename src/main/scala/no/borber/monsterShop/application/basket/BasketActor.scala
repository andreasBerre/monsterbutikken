package no.borber.monsterShop.application.basket

import akka.actor.Props
import akka.persistence.PersistentActor
import basket._

case class AddItemToBasket(montertype: String)
case class RemoveItemFromBasket(monstertype: String)
object CreateBasket

object BasketActor{
  def props(id: String):Props = Props(classOf[BasketActor], id);
}

class BasketActor(id: String) extends PersistentActor {

  var state = new BasketState(persistenceId);

  override def receiveRecover: Receive = {
    case evt: ItemAddedToBasket => updateState(evt)
    case evt: ItemRemovedFromBasket => updateState(evt)
  }

  override def receiveCommand: Receive = {
    case CreateBasket => {
      persist(BasketCreated(Some(persistenceId)))({e => updateState(e); sender ! e})
    }
    case AddItemToBasket(monstertype) =>
      persist(ItemAddedToBasket(Some(persistenceId), Some(monstertype)))(e => {
        updateState(e); sender ! "ok"
      })
    case RemoveItemFromBasket(monstertype) => {
      if (!state.canRemoveItem(monstertype))
        throw new RuntimeException("Can't remove monster")
      persist(ItemRemovedFromBasket(Some(persistenceId), Some(monstertype)))(e => {
        updateState(e);
        sender ! "ok"
      })
    }
    case "print" => print(state.toString);
    case "state" => sender ! state;
    case "fail" => throw new RuntimeException("Failing")
  }

  def updateState(event: Any) = event match {
    case e: ItemAddedToBasket =>
      state.addItemToBasket(e.getMonstertype)
    case e: ItemRemovedFromBasket =>
      state.removeItemFromBasket(e.getMonstertype)
    case e: BasketCreated =>
      state.setCreated(true);
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    sender ! reason;
    super.preRestart(reason, message)
  }

  override def persistenceId: String = id;
}

