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

First, some general advice:
* Before you start hammering out code: think. What aggregates do you need? What commands will they implement? What will your projections look like?
* Focus on the framwork of your application. Get your first application service, event store, projections, subscriptions etc. working before moving on to complete the API stubs.
* Test driven development is your friend: each component can be tested by itself before you later integrate them.
* You'll have to wire up your application in some way, injecting your event store into the applications services and projections, and your services into the controllers. One approach is to use Spring, which is already included in the project.
* Many event-sourced applications are async in nature, however, the Monster Shop has been designed with an syncronous event store in mind. While you're free to create an async solution, it will make the task quite a bit more complex.
 
We'll now present the different components of an event sourced system, in an order we feel makes sense implementation wise.

#### The Application Services
The application service is a good place to start. This is were the mechanics of your event-sourced write layer comes together. Here's an example of what an application service might look like in Java:

```Java
class CustomerApplicationService {
  ...
  public void reportRelocation(CustomerId id, Address newAddress){
	List<Event> events = eventStore.getEventsById(id)
	Customer customer = new Customer(events)
	customer.reportRelocation(newAddress)
	List<Event> derivedEvents = customer.getDerivedEvents()
	eventStore.save(derivedEvents)
 }
 ...
}
```
The service gets logged events from the event store and uses this to create a customer aggregate. The aggregate recreates it's internal state based on these events, and is then ready for use. 
Calling the reportRelocation method on the aggregate causes it to validate the command and to produce derived events from this command, which are then retrieved and saved to the event log.

#### Aggregates
The _aggregate_ domain object should be able to recreate its state by reading supplied events. When receiving a command it should validate it against this state, and derive relevant events..

#### Events
Events describe a change of application state, and are created by aggregates in response to commands. They are always named in the past tense; they describe something that has happened. They should include the following information:
* An id uniquely identifying the aggregate the event applies to, e.g. customer id.
* Fields describing the state change which occured, e.g. a customer relocated event could include the new address
* The aggregate type of the event, describing which type of aggregate it applies to, e.g. "CUSTOMER". This will allow projections to subscribe to a category of events, instead of spesific events.

#### Event Store
The _event store_ should accept new events and store them to an internal eventlog, which could be as simple as an array list. The store should also include a method for retireving an aggregates events by its id. The order of events should be maintained. A simple ArrayList works fine as an eventlog).
The event store should, after a new event is received and stored, publish the event to any _subscribing_ projections.

#### Projections
A _projection_ should be able to receive events and change state according to the nature of the event. This state could be kept in a suitable structure within the class.
The projection should be able to _subscribe_ to events from the event store. On recieving a subscription, the event store should send all stored events to the subscribing projection.
* Projections form the read layer of the application
* Subscribes to events from a store
* Alter state based on received events

#### HTTP Controllers
The HTTP-Api controller should be able to _dispatch commands_ to the application service.
The HTTP-Api controller should _query_ the projections to retrieve system state when needed.

#### Client side 
Finally, remove the serverMock.js include from the index.html file - this will switch off mocking and the client will make its requests directly to the server. 

### Resources
* The monster-shop (Java Edition): https://github.com/andreasBerre/monsterbutikken
* Slides from presentation: http://goo.gl/BgQAYH
