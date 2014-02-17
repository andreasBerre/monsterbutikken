var monsterApp = angular.module("monsterButikken", ['ngRoute', 'ui.bootstrap'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/',
            {
                controller: 'LoginController',
                templateUrl: 'login.html'
            })
            .when('/butikk',
            {
                controller: 'MonsterController',
                templateUrl: 'butikk.html'
            })
}]);


monsterApp.run(['$rootScope', '$location', 'autentiseringService', function ($rootScope, $location, autentiseringService) {
    $rootScope.$on('$routeChangeStart', function () {

        autentiseringService.innloggetKunde().success(function(innloggetKunde){
            if (!innloggetKunde.kundenavn) {
               // event.preventDefault();
                $location.path('/');
            }
            else {
                $location.path('/butikk');
            }
        })
    });
}]);