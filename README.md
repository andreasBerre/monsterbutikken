![Event Sourcing](src/main/webapp/img/logo.png "The Monster Shop")
================

Need monsters for the dungeons of your secret lair? Require foul beasts to unleash on an unsuspecting population? The Monster Shop provides the webs best selection of terrifying horrors, all accessible through a user friendly webpage.

##Your mission (should you choose to accept it)

You've been brought in as a consultant to complete work on the Monster Shops backend after the previous developer had an unfortunate accident involving one of the shops products.

The late developer completed the client side of the application, and had just started work on the API's before becoming monster food - so we have a working front end, but the back end consists of the login subsystem and a few API stubs.

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


### Plans for the server side

The server side implementation has been started, but not completed. Login and retrial of the product catalogue has been taken care of, but the API for doing the actual shopping consists of stubs with no implementation. 

The plan is to create an event sourced presistance mechanism, with projections from this store forming the read layer, as shown in the illustration below.

![Event Sourcing](https://www.lucidchart.com/publicSegments/view/53270feb-641c-4392-8090-20110a005809/image.png "Event Sourcing")

Note that there are multiple patterns for event sourcing, the above being one of the more common. So while you're free to choose your own implementation, the below could function as a guide.

Some general advice: 

* Start by implementing the framwork of the application (event store, projections, subscriptions etc) before moving on to implement the missing features in the Monster Shop.
* Test driven development is your friend: each component can be tested by itself before you integrate them.
* Many event-sourced applications are async in nature, however, the Monster Shop has been designed with an syncronous event store in mind. While you're free to create an async solution, it will make the task quite a bit more complex.
 
A suggested path for the implementation:

5. The _application service_ should implement command methods. These should 
  * Get stored events from the _event store_ by the supplied aggregate id.
  * Construct aggregate by supplying stored events
  * Call command method on aggregate
  * Retrieve derived events from aggregate, and store them to the event store.
1. The _event store_ should accept new events and store them to a eventlog. The events should include an _aggregate root id_, and an _aggregate type_. The store should also include a method for retireving an aggregates events by its id. The order of events should be maintained. A simple ArrayList works fine as an eventlog).
2. A _projection_ should be able to receive events and change state according to the nature of the event. This state could be kept in a suitable structure within the class.
3. The projection should be able to _subscribe_ to events from the event store. On recieving a subscription, the event store should send all stored events to the subscribing projection.
4. The event store should, after a new event is received and stored, publish the event to any _subscribing_ projections.
5. The _aggregate_ domain object should be able to recreate its state by reading supplied events, and alter its state and derive events on receipt of command.
8. The HTTP-Api controller should be able to _dispatch commands_ to the application service.
9. The HTTP-Api controller should _query_ the projections to retrieve system state when needed.
10. Finally, remove the serverMock.js include from the index.html file - this will switch off mocking and the client will make its requests directly to the server. 

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

### Resources

* The monster-shop (Java Edition): https://github.com/andreasBerre/monsterbutikken
* Slides from presentation: http://goo.gl/BgQAYH
