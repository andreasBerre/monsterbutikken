var monsterButikken = angular.module('monsterButikken', ['ui.bootstrap'])
    .controller('MonsterController', function($scope) {

        $scope.handlekurv = {};

        $scope.leggTilMonster = function(monster, e){
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

        $scope.fjernMonster = function(monster){
            var index=$scope.handlekurv.indexOf(monster)
            $scope.handlekurv.splice(index,1);
        };

        $scope.getHandlekurvSum = function(){
            var sum = 0;

            for (var monsterNavn in $scope.handlekurv) {
                if ($scope.handlekurv.hasOwnProperty(monsterNavn)){
                    kjop = $scope.handlekurv[monsterNavn];
                    sum = kjop.antall * kjop.monster.pris;
                }
            }
            return sum;
        };


        $scope.betal = function(){

        };

        $scope.monstre =  [
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