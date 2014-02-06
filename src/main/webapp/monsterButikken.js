var monsterButikken = angular.module('monsterButikken', ['ui.bootstrap'])
    .controller('MonsterController', function($scope) {

        $scope.handlekurv = [];

        $scope.kjopMonster = function(monster, e){
            if (e) {
                e.preventDefault();
                e.stopPropagation();
            }
            $scope.handlekurv.push(monster);
        };

        $scope.getHandlekurvSum = function(){
            return $scope.handlekurv.reduce(
                function (a, b) {
                    return a + b.pris; }, 0
            )
        }


        $scope.monstre =  [
            {navn: "Ao (skilpadde)", pris: 100},
            {navn: "Bakeneko", pris: 100},
            {navn: "Basilisk", pris: 100},
            {navn: "Borak", pris: 100},
            {navn: "Byakko", pris: 100},
            {navn: "Enhjørning", pris: 100},
            {navn: "Det erymanthiske villsvin", pris: 100},
            {navn: "Fenghuang", pris: 100},
            {navn: "Fucanglong", pris: 100},
            {navn: "Griff", pris: 100},
            {navn: "Hamløper", pris: 100},
            {navn: "Hippogriff", pris: 100},
            {navn: "Hydra", pris: 100},
            {navn: "Isonade", pris: 100},
            {navn: "Kentaur", pris: 100},
            {navn: "Kerberos", pris: 100},
            {navn: "Kraken", pris: 100},
            {navn: "Mannbjørn", pris: 100},
            {navn: "Mantikora", pris: 100},
            {navn: "Margyge", pris: 100},
            {navn: "Marmæle", pris: 100},
            {navn: "Minotauros", pris: 100},
            {navn: "Nekomusume", pris: 100},
            {navn: "Nian", pris: 100},
            {navn: "Nue", pris: 100},
            {navn: "Pegasus", pris: 100},
            {navn: "Rokk", pris: 100},
            {navn: "Sagari", pris: 100},
            {navn: "Seljordsormen", pris: 100},
            {navn: "Sfinks", pris: 100},
            {navn: "Sirene", pris: 100},
            {navn: "Sjøorm", pris: 100},
            {navn: "Strix", pris: 100},
            {navn: "Succubus", pris: 100},
            {navn: "Valravn", pris: 100},
            {navn: "Vampyr", pris: 100},
            {navn: "Varulv", pris: 100},
            {navn: "Xiangliu", pris: 100},
            {navn: "Yama-inu", pris: 100},
            {navn: "Yatagarasu", pris: 100},
            {navn: "Ziz", pris: 100}
        ];
    });