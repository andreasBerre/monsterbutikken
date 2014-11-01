app.factory('authService',['$http', function($http) {
    return {
        logIn: function(kundenavn){
            return $http.post('/service/currentCustomer/' + encodeURIComponent(kundenavn))
        },

        logOut: function(){
            return $http.delete('/service/currentCustomer/')
        },

        customer: function(){
            return $http.get('/service/currentCustomer/')
        }
    }
}]);