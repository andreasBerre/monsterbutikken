package no.borber.monsterShop.application.basket

import akka.persistence.PersistentActor
import basket.ItemAddedToBasket

case class AddItemToBasket(montertype: String)


class BasketActor extends PersistentActor{

  var state = new BasketState(persistenceId);

  override def receiveRecover: Receive = {
    case evt:ItemAddedToBasket => updateState(evt)
  }

  override def receiveCommand: Receive = {
    case AddItemToBasket(monstertype) =>
      persist(ItemAddedToBasket(Some(persistenceId), Some(monstertype)))(updateState)
    case "print" => print(state.toString);
  }

  def updateState(event: ItemAddedToBasket) = event match {
    case e: ItemAddedToBasket =>
      state.addItemToBasket(e.getMonstertype)
  }

  override def persistenceId: String = "basket-1"
}
