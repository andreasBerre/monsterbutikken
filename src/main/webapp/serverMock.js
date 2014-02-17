monsterApp.config(function($provide) {
    $provide.decorator('$httpBackend', angular.mock.e2e.$httpBackendDecorator);
});

monsterApp.run(function($httpBackend) {
    $httpBackend.whenGET(/.*html/).passThrough();


    //Mocks for AutorisasjonService
    var innloggetKunde;

    $httpBackend.whenPOST(/\/service\/autentisering\/logginn\/.*/).respond(function(method, url){
        innloggetKunde = url.substr(url.lastIndexOf('/') + 1, url.length)
        return [200];
    });

    $httpBackend.whenPOST('/service/autentisering/loggut').respond(function(method, url){
        innloggetKunde = null;
        return [200];
    });

    $httpBackend.whenGET('/service/autentisering/innloggetKunde').respond(function(){
        return [200, {kundenavn: innloggetKunde}];
    });


    //Mocks for HandlekurvService
    var handlekurv = {};

    $httpBackend.whenGET('/service/handlekurv/').respond(function(){
        return [200, handlekurv];
    });

    $httpBackend.whenPOST(/\/service\/handlekurv\/leggTil\/.*/).respond(function(method, url){
        var monsternavn = url.substr(url.lastIndexOf('/') + 1, url.length)

        var ordrelinje = handlekurv[monsternavn];
        if (!ordrelinje) {
            handlekurv[monsternavn] = {monsternavn: monsternavn, antall: 1, pris: getMonster(monsternavn).pris};
        } else {
            ordrelinje.antall++;
        }
        return [200];
    });

    $httpBackend.whenPOST(/\/service\/handlekurv\/fjern\/.*/).respond(function(method, url){
        var monsternavn = url.substr(url.lastIndexOf('/') + 1, url.length)

        var ordrelinje = handlekurv[monsternavn];
        if (ordrelinje.antall === 1)
            delete handlekurv[ordrelinje.monsternavn];
        else
            ordrelinje.antall--;

        return [200];
    });

    $httpBackend.whenPOST('/service/handlekurv/bekreftOrdre').respond(function(){
        handlekurv = {};
        return [200];
    });

    $httpBackend.whenGET('/service/handlekurv/sum').respond(function(){
        var sum = 0;
        for (var monsternavn in handlekurv) {
            if (handlekurv.hasOwnProperty(monsternavn)){
                var ordrelinje = handlekurv[monsternavn];
                sum = sum + (ordrelinje.antall * ordrelinje.pris);
            }
        }
        return [200, {sum: sum}];
    });


    //Mocks for MonsterService
    var monstre = [
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

    function getMonster(monsternavn){
        for (var i = 0; i < monstre.length; i++) {
            if (monstre[i].navn === monsternavn)
                return monstre[i]
        }
        return null;
    }

    $httpBackend.whenGET('/service/monstre').respond(function(){
        return [200, monstre];
    });

});



