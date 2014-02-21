app.controller('ShopController', ['$scope', '$modal', 'monsterService', 'basketService', 'authService', '$location',
    function($scope, $modal, monsterService, basketService, authService, $location) {

    authService.customer().success(function(customer){
        $scope.customerName = customer.customerName;
    });

    $scope.logOut = function(){
        authService.logOut().success(function(){
            $location.url('/');
        });
    };

    getBasket();

    function getBasket() {
        basketService.getBasket().success(function(basket){
            $scope.basket = basket;
        })
    }

    $scope.addMonster = function(monsterType, e){
        $scope.thanksForYourOrder = false;
        if (e) {
            e.preventDefault();
            e.stopPropagation();
        }
        basketService.addMonster(monsterType).then(getBasket());
    };

   $scope.removeMonster = function(monsterType){
       basketService.removeMonster(monsterType).then(getBasket());
    };

    $scope.basketEmpty = true;
    $scope.$watch('basket', function() {
        var basketEmpty = true;
        for (var prop in $scope.basket){
            if ($scope.basket.hasOwnProperty(prop))
                basketEmpty = false;
        }
        $scope.basketEmpty = basketEmpty;
    }, true);

    $scope.$watch('basket', function() {
        basketService.basketSum().success(function(basketSum){
            $scope.basketSum =  basketSum.sum;
        })
    }, true);

    $scope.order = function(){
        var confirmationModal = $modal.open({
            templateUrl: 'confirmOrderModal.html',
            controller: 'ConfirmOrderModalCtrl',
            resolve: {
                basket: function () {
                    return basketService.getBasket();
                },
                sum: function () {
                    return basketService.basketSum();
                }
            }
        });

        confirmationModal.result.then(function () {
            basketService.confirmOrder().success(function(){
                getBasket();
                $scope.thanksForYourOrder = true;
            });
        });
    };

    monsterService.getMonsterTypes().success(function(monsterTypes){
        $scope.monsterTypes = monsterTypes;
    })

}]);

app.controller('ConfirmOrderModalCtrl', ['$scope', '$modalInstance', 'basket', 'sum', function($scope, $modalInstance, basket, sum) {
    $scope.basket = basket.data;
    $scope.sum = sum.data.sum;

    $scope.confirm = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
