app.factory('orderService',['$http', function($http) {
    return {
        getOrders: function(){
            return $http.get('/service/orders');
        },

        placeOrder: function(){
            return $http.post('/service/placeOrder');
        }
    };
}]);
