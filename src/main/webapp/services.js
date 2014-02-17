monsterApp.factory('handlekurvService',['$http', function($http) {
    return {
        getHandlekurv: function(){
            //returnerer nåværende tilstand på handlekurv
            return $http.get('/service/handlekurv/');
        },

        leggTilMonster: function(monsternavn){
            //legger til et monster i handlekurven. Om det er en eksisterende ordrelinje økes antallet, ellers opprettes en ordrelinje.
            return $http.post('/service/handlekurv/leggTil/' + monsternavn);
        },

        fjernMonster: function(monsternavn){
            //fjerner et monster fra handlekurven. Om dette resulterer at det er ingen av typen igjen fjernes ordrelinjen.
            return $http.post('/service/handlekurv/fjern/' + monsternavn);
        },

        bekreftOrdre: function(){
            //oppretter en ordre basert på innholdet i handlekurven, og tømmer denne.
            return $http.post('/service/handlekurv/bekreftOrdre');
        },

        handlekurvSum: function(){
            //henter sum av pris * antall for alle ordrelinjer i handlekurven.
            return $http.get('/service/handlekurv/sum');
        }

    };
}]);

monsterApp.factory('autentiseringService',['$http', function($http) {
    return {
        loggInn: function(kundenavn){
            //logger inn kunden. I monsterbutikken stoler vi på våre kunder, så det er ikke noe passord. Kundenavnet settes på session på serversiden.
            return $http.post('/service/autentisering/logginn/' + kundenavn)
        },

        loggUt: function(){
            //logger kunden ut, fjerner kundenavn fra session
            return $http.post('/service/autentisering/loggut')
        },

        innloggetKunde: function(){
            //henter kundenavnet til innlogget kunde
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