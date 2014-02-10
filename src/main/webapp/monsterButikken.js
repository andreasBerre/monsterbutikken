var monsterButikken = angular.module('monsterButikken', ['ui.bootstrap'])

    .controller('MonsterController', ['$scope', '$http', '$modal', function($scope, $http, $modal) {

        $scope.handlekurv = {};

        $scope.leggTilMonster = function(monster, e){
            $scope.takkForKjop = false;
            if (e) {
                e.preventDefault();
                e.stopPropagation();
            }
            eksisterendeMonster = $scope.handlekurv[monster.navn];
            if (!eksisterendeMonster)
                $scope.handlekurv[monster.navn] = {monster: monster, antall: 1};
            else
                $scope.handlekurv[monster.navn] = {monster: monster, antall: eksisterendeMonster.antall + 1};
        };

        $scope.fjernMonster = function(kjop){
            if (kjop.antall === 1)
                delete $scope.handlekurv[kjop.monster.navn];
            else
                $scope.handlekurv[kjop.monster.navn] = {monster: kjop.monster, antall: kjop.antall - 1};
        };

        $scope.getHandlekurvSum = function(){
            var sum = 0;

            for (var monsterNavn in $scope.handlekurv) {
                if ($scope.handlekurv.hasOwnProperty(monsterNavn)){
                    var kjop = $scope.handlekurv[monsterNavn];
                    sum = sum + (kjop.antall * kjop.monster.pris);
                }
            }
            return sum;
        };

        $scope.handlekurvTom = function(){
            for (var prop in $scope.handlekurv) if ($scope.handlekurv.hasOwnProperty(prop)) return false;
            return true;
        };

        $scope.betal = function(){
            if (!$scope.brukernavn)
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
                        return $scope.handlekurv;
                    },
                    sum: function () {
                        return $scope.getHandlekurvSum();
                    }
                }
            });

            modalInstance.result.then(function () {
                $scope.takkForKjop = true;
                $scope.handlekurv = {};
            });
        }

        $scope.loggInn = function(){
            var modalInstance = $modal.open({
                templateUrl: 'loggInnModal.html',
                controller: 'LoggInnModalCtrl'
            });

            return modalInstance.result.then(function (brukernavn) {
                $scope.brukernavn = brukernavn;
            });
        }

        $scope.monstre = {};

        $scope.getMonstre = function(){
            $http.get('/service/monstre').success(function(data){
                $scope.monstre = data;
            }).error(function(){
                console.log("klarte ikke hente monstre fra server, laster fra klient")
                $scope.monstre = [
                    {navn: "Ao (skilpadde)", pris: 100000},
                    {navn: "Bakeneko", pris: 120000},
                    {navn: "Basilisk", pris: 175000},
                    {navn: "Det erymanthiske villsvin", pris: 100},
                    {navn: "Griff", pris: 100},
                    {navn: "Hamløper", pris: 100},
                    {navn: "Hippogriff", pris: 100},
                    {navn: "Hydra", pris: 100},
                    {navn: "Kentaur", pris: 100},
                    {navn: "Kerberos", pris: 100},
                    {navn: "Kraken", pris: 100},
                    {navn: "Mannbjørn", pris: 100},
                    {navn: "Mantikora", pris: 100},
                    {navn: "Margyge", pris: 100},
                    {navn: "Marmæle", pris: 100},
                    {navn: "Minotauros", pris: 100},
                    {navn: "Nekomusume", pris: 100},
                    {navn: "Rokk", pris: 100},
                    {navn: "Seljordsormen", pris: 100},
                    {navn: "Sfinks", pris: 100},
                    {navn: "Sirene", pris: 100},
                    {navn: "Sjøorm", pris: 100},
                    {navn: "Succubus", pris: 100},
                    {navn: "Valravn", pris: 100},
                    {navn: "Vampyr", pris: 100},
                    {navn: "Varulv", pris: 100},
                ];
            });
        };
        $scope.getMonstre();
    }])

    .controller('LoggInnModalCtrl', ['$scope', '$modalInstance', function($scope, $modalInstance) {

        $scope.loggInn = function () {
            $modalInstance.close(this.brukernavn);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])

    .controller('KjopModalCtrl', ['$scope', '$modalInstance', 'handlekurv', 'sum', function($scope, $modalInstance, handlekurv, sum) {
        $scope.handlekurv = handlekurv;
        $scope.sum = sum;

        $scope.bekreftKjop = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);