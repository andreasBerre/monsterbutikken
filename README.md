![Event Sourcing](src/main/webapp/img/logo.png "The Monster Shop")
================

Need monsters to populate the dungeons of your secret lair? The Monster Shop provides the internets best selection of foul beasts and terrifying horrors, all accessible through a user friendly webpage.

##Your mission (should you choose to accept it)

You've been brought in as a consultant to complete the work on the Monster Shop after the previous developer had an unfortunate accident involving one of the shops products.

The late developer completed the client side, and had just started the rest API's before becoming monster food - so we have a working front end, but the back end consists of the login handling and a few API stubs.

After giving his bloody notes a quick clean you gather the information below:


---------------------------------------


### The domain

The context of our domain is the Monster Shop, and it's mechanisms for shopping and placing orders.

* _Monster type_: A species of monster avaliable for sale in the monster shop
* _Monster_: A "monster instance": the physical monster transported to the customer upon a confirmed order.
* _Basket_: The temporary shopping basket a customer uses to create his order. A item in the basket is transformed to a item in an order upon confirmation.
* _Basket line item_: A item in the basket, consisting of a specified monster type, a price, and an amount of monsters of that type.
* _Order confirmation_: A confirmation issued by the customer transforming the basket to an order.
* _Order_: A confirmed order consisting of one or more order line items.
* _Order line item_: A confirmed order for a specified number of monsters at a specified price.
* _Customer_: A evil overlord shopping and placing orders at the monster shop.


### Plans for the server side

The server side implementation has been started, but not completed. Login and retrial of the product catalogue has been taken care of, but the api for doing the actual shopping consists of stubs with no implementation. There is no write or read layer either.

The plan is to implement the write layer as an event store, and the read layer as projections of the events in this store, as shown in the illustration below.

![Event Sourcing](eventsourcing2.png "Event Sourcing")

Note that there are multiple patterns for event sourcing, the above being one of the more common. So while you're free to choose your own implementation, the below could function as a guide.

Some general advice: Start by getting a complete loop working before implementing remaining features. Test driven development is your friend: each component can be tested by itself before you integrate them.

NOTE: Many event-sourced applications are async in nature, however, the Monster Shop has been designed with an syncronous event store in mind, in order to keep complexity low.

1. The _event store_ should accept new events and store them to a journal. The events should include an _aggregate root id_, and an _aggregate type_. The store should also include a method for retireving a aggregates events by its id. The order of events should be maintained. (an ArrayList can work as an eventlog)
2. A _projection_ should be able to receive events and change state according to the nature of the event. This state could be kept in a suitable collection within the class.
3. The projection should be able to _subscribe_ to events from the event store. On recieving a subsscription, the event store should send all stored events to the subscrbing projection.
4. The event store should, after a new event is received and stored, publish the event to any _subscribing_ projections.
5. The _application service_ should implement command methods. These should 
  * Get stored events from the _event store_ by the supplied aggregate id.
  * Construct aggregate by supplying stored events
  * Call command method on aggregate
  * Retrieve derived events from aggregate, and store them to the event store.
5. The _aggregate_ domain object should be able to recreate its state by reading supplied events, and alter its state and derive events on receipt of command.
8. The REST controller should be able to _dispatch commands_ to the application service.
9. The REST controller should _query_ the projections to retrieve system state when needed.
10. Finally, remove the serverMock.js include from the index.html file - this will switch off mocking and the client will make its requests directly to the server. 

### Components of an Event Sourced System

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

### Resources

* monstershopen java: https://github.com/andreasBerre/monstershopen
* monstershopen c#: https://github.com/oven/monstershopen.net
* slides from presentation: http://goo.gl/G1U9HS  
