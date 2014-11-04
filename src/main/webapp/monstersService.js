app.factory('monsterService', ['$http', function($http) {
    return {
        getMonsterTypes: function() {
            return $http.get('/service/monsterTypes');
        }
    };
}]);