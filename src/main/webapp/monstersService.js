app.factory('monsterService', ['$http', function($http) {
    return {
        getMonsterTypes: function() {
            return $http.get('/service/monsterTypes');
        },

        getOrder: function(monstertype){
            return $http.get('/service/monsterTypes/' + encodeURIComponent(monstertype));
        }
    };
}]);