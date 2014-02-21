app.factory('authService',['$http', function($http) {
    return {
        logIn: function(kundenavn){
            return $http.post('/service/auth/logIn/' + encodeURIComponent(kundenavn))
        },

        logOut: function(){
            return $http.post('/service/auth/logOut')
        },

        customer: function(){
            return $http.get('/service/auth/customer')
        }
    }
}]);