var monsterButikken = angular.module('monsterButikken', ['ui.bootstrap']);

monsterButikken.controller('MonsterController', ['$scope', '$http', '$modal', 'monsterService', 'handlekurvService', 'loggInnService', function($scope, $http, $modal, monsterService, handlekurvService, loggInnService) {
    $scope.leggTilMonster = function(monster, e){
        $scope.takkForKjop = false;
        if (e) {
            e.preventDefault();
            e.stopPropagation();
        }
        handlekurvService.leggTilMonster(monster).then(function(data){
            $scope.handlekurv = data;
        });
    };

    $scope.fjernMonster = function(kjop){
        handlekurvService.fjernMonster(kjop).then(function(data){
            $scope.handlekurv = data;
        });
    };

    $scope.getHandlekurvSum = handlekurvService.getHandlekurvSum;

    $scope.handlekurvTom = handlekurvService.handlekurvTom;

    $scope.betal = function(){
        if (!loggInnService.innloggetBruker())
            $scope.loggInn().then(function() {
                betal();
            });
        else
            betal();
    };

    function betal(){
        var modalInstance = $modal.open({
            templateUrl: 'kjopModal.html',
            controller: 'KjopModalCtrl',
            resolve: {
                handlekurv: function () {
                    return handlekurvService.getHandlekurv();
                },
                sum: function () {
                    return handlekurvService.getHandlekurvSum();
                }
            }
        });

        modalInstance.result.then(function () {
            handlekurvService.betal().then(function(data){
                $scope.handlekurv = data;
                $scope.takkForKjop = true;
            });
        });
    }



    $scope.loggInn = function(){
        var modalInstance = $modal.open({
            templateUrl: 'loggInnModal.html',
            controller: 'LoggInnModalCtrl'
        });

        return modalInstance.result.then(function (brukernavn) {
            loggInnService.loggInn(brukernavn).then(function(){
                $scope.brukernavn = brukernavn;
            })
        });
    }

    $scope.monstre = monsterService.getMonstre().success(function(data){
        $scope.monstre = data;
    });

}]);

monsterButikken.controller('LoggInnModalCtrl', ['$scope', '$modalInstance', function($scope, $modalInstance) {
    $scope.loggInn = function () {
        $modalInstance.close(this.brukernavn);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);

monsterButikken.controller('KjopModalCtrl', ['$scope', '$modalInstance', 'handlekurv', 'sum', function($scope, $modalInstance, handlekurv, sum) {
    $scope.handlekurv = handlekurv;
    $scope.sum = sum;

    $scope.bekreftKjop = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
