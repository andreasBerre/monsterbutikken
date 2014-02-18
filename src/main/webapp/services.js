monsterApp.factory('handlekurvService',['$http', function($http) {
    return {
        getHandlekurv: function(){
            // Returnerer nåværende tilstand på handlekurv. Handlekurv er et js objekt med en property pr ordrelinje, med
            // monsternavn som nøkkel og et ordrelinjeobjekt som value. Ordrelinjeobjektet har properties monsternavn,
            // pris, og antall
            return $http.get('/service/handlekurv/');
        },

        leggTilMonster: function(monsternavn){
            // Legger til et monster i handlekurven. Om en eksisterende ordrelinje finnes for monstertypen inkrementeres
            // antall, hvis ikke opprettes en ny ordrelinje i brukerens handlekurv.
            return $http.post('/service/handlekurv/leggTil/' + monsternavn);
        },

        fjernMonster: function(monsternavn){
            // Fjerner et monster fra handlekurven (dekrementerer antall i ordrelinjen). Hvis resultatet er at antall blir
            // null fjernes ordrelinjen.
            return $http.post('/service/handlekurv/fjern/' + monsternavn);
        },

        bekreftOrdre: function(){
            // Oppretter en ordre basert på innholdet i handlekurven, og tømmer brukerens handlekurv.
            return $http.post('/service/handlekurv/bekreftOrdre');
        },

        handlekurvSum: function(){
            // Henter sum av pris * antall for alle ordrelinjer i handlekurven. Sum er et objekt med en property, sum.
            return $http.get('/service/handlekurv/sum');
        }

    };
}]);

monsterApp.factory('autentiseringService',['$http', function($http) {
    return {
        loggInn: function(kundenavn){
            // Logger inn kunden. I monsterbutikken stoler vi på våre kunder, så det er ikke noe passord. Kundenavnet
            // settes på session på serversiden.
            return $http.post('/service/autentisering/logginn/' + kundenavn)
        },

        loggUt: function(){
            // Logger kunden ut ved å fjerne kundenavn fra session.
            return $http.post('/service/autentisering/loggut')
        },

        innloggetKunde: function(){
            // Henter kundenavnet til innlogget kunde. Kundenavn er et objekt med en property, kundenavn.
            return $http.get('/service/autentisering/innloggetKunde')
        }
    }
}]);

monsterApp.factory('monsterService', ['$http', function($http) {
    return {
        getMonstre: function() {
            return $http.get('/service/monstre');
        }
    };
}]);