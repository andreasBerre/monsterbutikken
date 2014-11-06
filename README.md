![Event Sourcing](src/main/webapp/img/logo.png "The Monster Shop")
================

Need monsters for the dungeons of your secret lair? Require foul beasts to unleash on an unsuspecting population? The Monster Shop provides the webs best selection of terrifying horrors, all accessible through a user friendly webpage.

##Your mission (should you choose to accept it)

You've been brought in as a consultant to complete work on the Monster Shops server side after the previous developer had an unfortunate accident involving one of the shops products.

The late developer completed the client side of the application, and had just started work on the API's before becoming monster food - so we have a working front end, but the back end consists of the login subsystem and a few controllers.

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


### Creating the server side
The server side implementation has been started, but not completed. Login and retrial of the product catalogue has been taken care of, and controllers for the REST API created, but the rest of the application is missing. This is where you come in.

The next step is to create the applications write stack in the form of an event sourced domain layer, and the read stack in the form of projections of the event log. 

![Event Sourcing](https://www.lucidchart.com/publicSegments/view/545944ec-8b08-404a-8d31-07630a00c2c4/image.png "Event Sourcing")

In order to do this we need the following components:

#### The Application Services
An application service is a good place to start. This is were the mechanics of your event-sourced write layer comes together. Here's an example of what an application service might look like in Java:

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
The _aggregate_ domain object should be able to recreate its state by reading supplied events. 
An aggreagate should be able to:
* Create itself by accepting it's previously stored events and modify its internal state by replaying these. After the replay the aggregate should be in its current state.
* Accept commands from the application service, validating these, and deriving appropriate events.
* Return a list of derived events to the application service.

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

#### Client side 
Finally, remove the serverMock.js include from the index.html file - this will switch off mocking and the client will make its requests directly to the server. 

### Resources
* The monster-shop (Java Edition): https://github.com/andreasBerre/monsterbutikken
* Slides from presentation: http://goo.gl/BgQAYH
