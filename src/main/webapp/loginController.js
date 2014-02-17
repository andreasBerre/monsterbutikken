monsterApp.controller('LoginController', ['$scope', 'autentiseringService', '$location',  function($scope, autentiseringService, $location) {
    $scope.loggInn = function(){
        autentiseringService.loggInn($scope.kundenavn).then(function(){
            $location.url('/butikk');
        })
    }
}]);