app.config(function($provide) {
    $provide.decorator('$httpBackend', angular.mock.e2e.$httpBackendDecorator);
});

app.run(function($httpBackend) {
    $httpBackend.whenGET(/.*html/).passThrough();

    //Mocks for BasketService
    var basket = {};

    $httpBackend.whenGET('/service/basket/').respond(function(){
        return [200, basket];
    });

    $httpBackend.whenPOST(/\/service\/basket\/.*/).respond(function(method, url){
        var name = decodeURIComponent(url.substr(url.lastIndexOf('/') + 1, url.length));

        var basketItem = basket[name];
        if (!basketItem) {
            basket[name] = {name: name, number: 1, price: getMonsterType(name).price};
        } else {
            basketItem.number++;
        }
        return [200];
    });

    $httpBackend.whenDELETE(/\/service\/basket\/.*/).respond(function(method, url){
        var name = decodeURIComponent(url.substr(url.lastIndexOf('/') + 1, url.length));

        var basketItem = basket[name];
        if (basketItem.number === 1)
            delete basket[basketItem.name];
        else
            basketItem.number--;

        return [200];
    });

    $httpBackend.whenGET('/service/basket/sum').respond(function(){
        return [200, {sum: getBasketSum()}];
    });

    function getBasketSum(){
        var sum = 0;
        for (var monsterTypeName in basket) {
            if (basket.hasOwnProperty(monsterTypeName)){
                var basketItem = basket[monsterTypeName];
                sum = sum + (basketItem.number * basketItem.price);
            }
        }
        return sum;
    }


    //Mocks for orderService
    var orders = {};
    $httpBackend.whenPOST('/service/orders').respond(function(){
        var orderLineItems = [];
        for (var monsterTypeName in basket) {
            if (basket.hasOwnProperty(monsterTypeName)){
                orderLineItems.push(basket[monsterTypeName]);
            }
        }

        orders[guid()] = {date: new Date(), sum: getBasketSum(), orderLineItems: orderLineItems};
        basket = {};
        return [200];
    });

    $httpBackend.whenGET('/service/orders').respond(function(){
        return [200, orders];
    });

    $httpBackend.whenGET(/\/service\/orders\/.*/).respond(function(method, url){
        var orderId = decodeURIComponent(url.substr(url.lastIndexOf('/') + 1, url.length));

        return [200, orders[orderId]];
    });

    //Mocks for authService
    var customer;

    $httpBackend.whenPOST(/\/service\/auth\/logIn\/.*/).respond(function(method, url){
        customer = decodeURIComponent(url.substr(url.lastIndexOf('/') + 1, url.length));
        return [200];
    });

    $httpBackend.whenPOST('/service/auth/logOut').respond(function(){
        customer = null;
        basket = {};
        orders = {};
        return [200];
    });

    $httpBackend.whenGET('/service/auth/customer').respond(function(){
        return [200, {customerName: customer}];
    });


    //Mocks for MonsterService
    var monsterTypes = [
        {name: "Ao (skilpadde)", price: 100000},
        {name: "Bakeneko", price: 120000},
        {name: "Basilisk", price: 175000},
        {name: "Det erymanthiske villsvin", price: 25000},
        {name: "Griff", price: 12000},
        {name: "Hamløper", price: 8000},
        {name: "Hippogriff", price: 128000},
        {name: "Hydra", price: 38000},
        {name: "Kentaur", price: 76000},
        {name: "Kerberos", price: 31000},
        {name: "Kraken", price: 2800},
        {name: "Mannbjørn", price: 49000},
        {name: "Mantikora", price: 21000},
        {name: "Margyge", price: 73000},
        {name: "Marmæle", price: 149000},
        {name: "Minotauros", price: 28000},
        {name: "Nekomusume", price: 62000},
        {name: "Rokk", price: 12000},
        {name: "Seljordsormen", price: 56000},
        {name: "Sfinks", price: 39000},
        {name: "Sirene", price: 12900},
        {name: "Sjøorm", price: 240000},
        {name: "Succubus", price: 84000},
        {name: "Valravn", price: 92300},
        {name: "Vampyr", price: 420000},
        {name: "Varulv", price: 69000}
    ];

    function getMonsterType(name){
        for (var i = 0; i < monsterTypes.length; i++) {
            if (monsterTypes[i].name === name)
                return monsterTypes[i]
        }
        return null;
    }

    $httpBackend.whenGET('/service/monsterTypes').respond(function(){
        return [200, monsterTypes];
    });

    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    };

    function guid() {
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
    }

});



