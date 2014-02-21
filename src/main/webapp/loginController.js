app.controller('LoginController', ['$scope', 'authService', '$location',  function($scope, authService, $location) {
    $scope.logIn = function(){
        authService.logIn($scope.customerName).then(function(){
            $location.url('/shop');
        })
    }
}]);