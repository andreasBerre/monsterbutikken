app.factory('basketService',['$http', function($http) {
    return {
        getBasket: function(){
            return $http.get('/service/basket/');
        },

        addMonster: function(monsterType){
            return $http.post('/service/basket/add/' + encodeURIComponent(monsterType));
        },

        removeMonster: function(monsterType){
            return $http.post('/service/basket/remove/' + encodeURIComponent(monsterType));
        },

        confirmOrder: function(){
            return $http.post('/service/basket/confirm');
        },

        basketSum: function(){
            return $http.get('/service/basket/sum');
        }
    };
}]);
