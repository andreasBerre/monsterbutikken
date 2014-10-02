app.factory('orderService',['$http', function($http) {
    return {
        getOrders: function(){
            return $http.get('/service/orders');
        },

        getOrder: function(orderId){
            return $http.get('/service/orders/' + encodeURIComponent(orderId));
        }
    };
}]);
