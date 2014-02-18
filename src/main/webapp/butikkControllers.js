app.controller('MonsterController', ['$scope', '$modal', 'monsterService', 'handlekurvService', 'autentiseringService', '$location',
    function($scope, $modal, monsterService, handlekurvService, autentiseringService, $location) {

    autentiseringService.innloggetKunde().success(function(innloggetKunde){
        $scope.kundenavn = innloggetKunde.kundenavn;
    });

    $scope.loggUt = function(){
        autentiseringService.loggUt().success(function(){
            $location.url('/');
        });
    };

    getHandlekurv();

    function getHandlekurv() {
        handlekurvService.getHandlekurv().success(function(handlekurv){
            $scope.handlekurv = handlekurv;
        })
    }

    $scope.leggTilMonster = function(monsternavn, e){
        $scope.takkForBestilling = false;
        if (e) {
            e.preventDefault();
            e.stopPropagation();
        }
        handlekurvService.leggTilMonster(monsternavn).then(getHandlekurv());
    };

   $scope.fjernMonster = function(monsternavn){
        handlekurvService.fjernMonster(monsternavn).then(getHandlekurv());
    };

    $scope.handlekurvTom = true;
    $scope.$watch('handlekurv', function() {
        var handlekurvTom = true;
        for (var prop in $scope.handlekurv){
            if ($scope.handlekurv.hasOwnProperty(prop))
                handlekurvTom = false;
        }
        $scope.handlekurvTom = handlekurvTom;
    }, true);

    $scope.$watch('handlekurv', function() {
        handlekurvService.handlekurvSum().success(function(handlekurvSum){
            $scope.handlekurvSum =  handlekurvSum.sum;
        })
    }, true);

    $scope.bestill = function(){
        var bekreftModal = $modal.open({
            templateUrl: 'bekreftOrdreModal.html',
            controller: 'BekreftOrdreModalCtrl',
            resolve: {
                handlekurv: function () {
                    return handlekurvService.getHandlekurv();
                },
                sum: function () {
                    return handlekurvService.handlekurvSum();
                }
            }
        });

        bekreftModal.result.then(function () {
            handlekurvService.bekreftOrdre().success(function(){
                getHandlekurv();
                $scope.takkForBestilling = true;
            });
        });
    };

    $scope.monstre = monsterService.getMonstre().success(function(monstre){
        $scope.monstre = monstre;
    })

}]);

app.controller('BekreftOrdreModalCtrl', ['$scope', '$modalInstance', 'handlekurv', 'sum', function($scope, $modalInstance, handlekurv, sum) {
    $scope.handlekurv = handlekurv.data;
    $scope.sum = sum.data.sum;

    $scope.bekreftOrdre = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
