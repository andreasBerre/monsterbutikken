var app = angular.module("MonsterShopApp", ['ngRoute', 'ui.bootstrap'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/',
            {
                controller: 'LoginController',
                templateUrl: 'login.html'
            })
            .when('/shop',
            {
                controller: 'ShopController',
                templateUrl: 'shop.html'
            })
}]);


app.run(['$rootScope', '$location', 'authService', function ($rootScope, $location, authService) {
    $rootScope.$on('$routeChangeStart', function () {
        authService.customer().success(function(customer){
            if (!customer.customerName) {
                $location.path('/');
            }
            else {
                $location.path('/shop');
            }
        })
    });
}]);