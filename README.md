![Event Sourcing](src/main/webapp/img/logo.png "The Monster Shop")
================

Need monsters for the dungeons of your secret lair? Require foul beasts to unleash on an unsuspecting population? The Monster Shop provides the webs best selection of terrifying horrors, all accessible through a user friendly webpage.

##Your mission (should you choose to accept it)

You've been brought in as a consultant to complete work on the Monster Shops backend after the previous developer had an unfortunate accident involving one of the shops products.

After giving his bloody notes a quick clean you gather the information below:


---------------------------------------


### The Domain

The context of our domain is the Monster Shop, and its mechanisms for browsing monsters and placing orders. A quick domain dictionary:

* _Monster type_: A species of monster avaliable for sale in the monster shop. Due to advanced cloning facilities the monster shop can provide an infinite amount of each monster type.
* _Monster_: A "monster instance": the physical monster transported to the customer upon a confirmed order. 
* _Basket_: The temporary shopping basket a customer uses to create his order. 
* _Basket line item_: A item in the basket, consisting of a specified monster type, a price, and an amount of monsters of that type. Any basket line items in the customers current basket are transformed into order line items upon order confirmation.
* _Order confirmation_: A confirmation issued by the customer transforming the basket to an order.
* _Order_: A confirmed order consisting of one or more order line items.
* _Order line item_: A confirmed order for a specified number of monsters at a specified price.
* _Customer_: A evil overlord shopping and placing orders at the monster shop.

### TODO (before one of the monsters decides to eat me)

1. Functionality for adding or removing monsters from a basket is missing from the BasketAggregate. This is causing BasketAggregateTest to fail.
2. The BasketApplicationService should use the new add and remove functionality of the aggregate. Implement this to make BasketApplicationServiceTest succeed.
3. The BasketProjections should handle MonsterAdded and removed events. Implement this to make the BasketProjectionTest succeed.

### General information: components of an Event Sourced System

#### The Event Store
* The event store receives, stores, and publishes incoming events
* Events are _immutable_ objects
* The event log is _append-only_
* All events are read on startup
* Reading of the log is always done from the oldest to the newest event (no random access)

#### Projections
* Projections form the read layer of the application
* Subscribes to events from a store
* Alter state based on received events

#### Application services
* Forms, with the event store, the write layer of the application
* Receives and validates incomming commands. 
* Performes operations required to complete the command, and dispatches derived events to the event store


```
interface CustomerApplicationService {
  ...
  public void reportRelocation(CustomerId id, Address newAddress){
	events = eventStore.getEventsById(id)
	customer = new Customer(events)
	customer.reportRelocation(newAddress)
	derivedEvents = customer.getDerivedEvents()
	eventStore.save(derivedEvents)
 }
 ...
}
```

### How to JettyRun
```
$ mvn jetty:run
```
### Resources

* The monster-shop (Java Edition): https://github.com/andreasBerre/monsterbutikken
* Slides from presentation: http://goo.gl/BgQAYH
