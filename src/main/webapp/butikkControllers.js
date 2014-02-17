monsterApp.controller('MonsterController', ['$scope', '$modal', 'monsterService', 'handlekurvService', 'autentiseringService', '$location', function($scope, $modal, monsterService, handlekurvService, autentiseringService, $location) {

    autentiseringService.innloggetKunde().success(function(data){
        $scope.kundenavn = data.kundenavn;
    });

    $scope.loggUt = function(){
        autentiseringService.loggUt();
        $location.url('/');
    };

    $scope.handlekurvTom = true;

    function getHandlekurv() {
        handlekurvService.getHandlekurv().success(function(data){
            $scope.handlekurv = data;
        })
    }

    getHandlekurv();

    $scope.leggTilMonster = function(monsternavn, e){
        $scope.takkForBestilling = false;
        if (e) {
            e.preventDefault();
            e.stopPropagation();
        }
        handlekurvService.leggTilMonster(monsternavn).then(function(){
            getHandlekurv();
        });
    };

   $scope.fjernMonster = function(monsternavn){
        handlekurvService.fjernMonster(monsternavn).then(function(){
            getHandlekurv();
        });
    };

    $scope.$watch('handlekurv', function() {
        var handlekurvTom = true;
        for (var prop in $scope.handlekurv){
            if ($scope.handlekurv.hasOwnProperty(prop)){
                handlekurvTom = false;
            }
        }
        $scope.handlekurvTom = handlekurvTom;
    }, true);

    $scope.$watch('handlekurv', function() {
        handlekurvService.handlekurvSum().success(function(data){
            $scope.handlekurvSum =  data.sum;
        })
    }, true);

    $scope.betal = function(){
        var modalInstance = $modal.open({
            templateUrl: 'kjopModal.html',
            controller: 'KjopModalCtrl',
            resolve: {
                handlekurv: function () {
                    return handlekurvService.getHandlekurv();
                },
                sum: function () {
                    return handlekurvService.handlekurvSum();
                }
            }
        });

        modalInstance.result.then(function () {
            handlekurvService.bekreftOrdre().success(function(){
                getHandlekurv();
                $scope.takkForBestilling = true;
            });
        });
    };

    $scope.monstre = monsterService.getMonstre().success(function(data){
        $scope.monstre = data;
    })

}]);

monsterApp.controller('KjopModalCtrl', ['$scope', '$modalInstance', 'handlekurv', 'sum', function($scope, $modalInstance, handlekurv, sum) {
    $scope.handlekurv = handlekurv.data;

    $scope.sum = sum.data.sum;

    $scope.bekreftKjop = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
