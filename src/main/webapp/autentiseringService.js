app.factory('autentiseringService',['$http', function($http) {
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