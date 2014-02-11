monsterButikken.factory('handlekurvService',[ '$q', function($q) {
    var handlekurv = {};
    return {
        getHandlekurv: function(){
            return handlekurv;
        },

        leggTilMonster: function(monster){
            eksisterendeMonster = handlekurv[monster.navn];
            if (!eksisterendeMonster)
                handlekurv[monster.navn] = {monster: monster, antall: 1};
            else
                handlekurv[monster.navn] = {monster: monster, antall: eksisterendeMonster.antall + 1};

            var deferred = $q.defer();
            deferred.resolve(handlekurv);
            return deferred.promise;
        },

        fjernMonster: function(kjop){
            if (kjop.antall === 1)
                delete handlekurv[kjop.monster.navn];
            else
                handlekurv[kjop.monster.navn] = {monster: kjop.monster, antall: kjop.antall - 1};

            var deferred = $q.defer();
            deferred.resolve(handlekurv);
            return deferred.promise;
        },

        getHandlekurvSum: function(){
            var sum = 0;
            for (var monsterNavn in handlekurv) {
                if (handlekurv.hasOwnProperty(monsterNavn)){
                    var kjop = handlekurv[monsterNavn];
                    sum = sum + (kjop.antall * kjop.monster.pris);
                }
            }
            return sum;
        },

        handlekurvTom: function(){
            for (var prop in handlekurv) if (handlekurv.hasOwnProperty(prop)) return false;
            return true;
        },

        betal: function(){
            handlekurv = {};
            var deferred = $q.defer();
            deferred.resolve(handlekurv);
            return deferred.promise;
        }
    };
}]);

monsterButikken.factory('monsterService', ['$http', function($http) {
    return {
        getMonstre: function() {
            return $http.get('/service/monstre').error(function(){
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
                        {navn: "Varulv", pris: 100}
                    ];
                }
            )
        }
    };
}]);