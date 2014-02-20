app.factory('handlekurvService',['$http', function($http) {
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
