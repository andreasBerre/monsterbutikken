Oppdrag
=======

Du er hentet inn som kunsulent av Monsterbutikken for å erstatte en tidligere utvikler som ikke ble ferdig med jobben før han kom ut for en stygg ulykke med en av produktene.

Den avdøde utvileren rakk å gjøre ferdig klient-siden av appen og hadde akkurat kommet i gang med å lage REST-api'ene når han ble monster-mat.

På blodige ark finner du notatene under:

#### Domeneanalyse

* _Monster_ Et skummelt og dødelig vesen du kan kjøpe i monsterbutikken. Identifiseres av monsternavn.
* _Handlekurvlinje_ En monstertype plassert i handlekurven, i et visst antall til en viss pris.
* _Handlekurv_ Foreløpig, ubekreftet ordre. Har en eller flere handlekurvlinjer. En kunde kan bare ha en handlekurv om gangen.
* _Ordre bekreftelse_ Omdanner innholdet i handlekurven til en ordre og tømmer handlekurven.
* _Ordre_ En bekreftet ordre fra en kunde med en eller flere ordrelinjer.
* _Ordrelinje_ En bekreftet ordre på en monstertype, i et visst antall til en viss pris.
* _Kunde_ En person som handler i monsterbutikken. Identifiseres med kundenavn.


#### Notater til etablering av server-side

![Event Sourcing](eventsourcing.png "Event Sourcing")

* Eventstore skal kunne ta i mot hendelser og lagre i en logg.
* Projeksjoner skal abonnere på hendelser, og endre tilstand ved mottak.
* Eventstore skal publisere mottatte hendelser til abonnerende projeksjoner.
* CommandHandler skal motta og validere commands, og sende hendelser til EventStore hvis commanden er gyldig.
* Controller skal sende commands til CommandHandler
* Controller skal lese fra projeksjoner
* Kommenter ut serverMock.js fra index.htm, og lag et fungerende rest api!

#### Lenker
----------------------------------------------------------------------
monsterbutikken java  | https://github.com/andreasBerre/monsterbutikken
monsterbutikken c#	  | https://github.com/oven/monsterbutikken.net
presentasjon          | http://goo.gl/G1U9HS