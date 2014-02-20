Oppdrag
=======

Du er hentet inn som kunsulent av Monsterbutikken for å erstatte en tidligere utvikler som ikke ble ferdig med jobben før han kom ut for en stygg ulykke medet av produktene.

Den avdøde utvileren rakk å gjøre ferdig klient-siden av appen og hadde akkurat kommet i gang med å lage REST-api'ene når han ble monster-mat.

På blodige ark finner du notatene under:

#### Domeneanalyse

* _Monster_: Et skummelt og dødelig vesen du kan kjøpe i monsterbutikken. Identifiseres av monsternavn.
* _Handlekurvlinje_: En monstertype plassert i handlekurven, i et visst antall til en viss pris.
* _Handlekurv_: Foreløpig, ubekreftet ordre. Har en eller flere handlekurvlinjer. En kunde kan bare ha en handlekurv om gangen.
* _Ordre bekreftelse_: Omdanner innholdet i handlekurven til en ordre og tømmer handlekurven.
* _Ordre_: En bekreftet ordre fra en kunde med en eller flere ordrelinjer.
* _Ordrelinje_: En bekreftet ordre på en monstertype, i et visst antall til en viss pris.
* _Kunde_: En person som handler i monsterbutikken. Identifiseres med kundenavn.


#### Notater til etablering av server-side

Det er så vidt startet på server-side delen av oppgaven. Innlogging og henting av produktkatalogen (monstere) er tatt hånd om. men rest API'et for handlekurven har bare stubs uten implementasjon. Det er heller ikke lagd noe skrive eller lese-lag for applikasjonen.

![Event Sourcing](eventsourcing.png "Event Sourcing")

* Eventstore skal kunne ta i mot hendelser og lagre i en logg. Eksempel på en hendelse kan være MonsterLagtTilIHandlekurv.
* Projeksjoner skal abonnere på hendelser, og endre tilstand ved mottak. 
* Eventstore skal publisere mottatte hendelser til abonnerende projeksjoner.
* CommandHandler skal motta og validere commands, og sende hendelser til EventStore hvis commanden er gyldig.
* Controller skal sende commands til CommandHandler.
* Controller skal lese fra projeksjoner.
* Kommenter ut serverMock.js fra index.htm, og lag et fungerende rest api!
 
#### Notater til etablering av klient-side
Her bør man ikke trenge å gjøre noe mer enn å kommentere ut serverMock.js i index.html.

#### Lenker

monsterbutikken java    https://github.com/andreasBerre/monsterbutikken  
monsterbutikken c#      https://github.com/oven/monsterbutikken.net  
presentasjon            http://goo.gl/G1U9HS  
