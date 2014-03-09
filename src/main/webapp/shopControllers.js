app.controller('ShopController', ['$scope', '$modal', 'monsterService', 'basketService', 'authService', '$location', 'orderService',
    function($scope, $modal, monsterService, basketService, authService, $location, orderService) {

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
        basketService.addMonster(monsterType).then(getBasket);
    };

   $scope.removeMonster = function(monsterType){
       basketService.removeMonster(monsterType).then(getBasket);
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
            orderService.placeOrder().success(function(){
                getBasket();
                getOrders();
                $scope.thanksForYourOrder = true;
            });
        });
    };

    getOrders();

    $scope.viewOrder = function(orderId){
        $modal.open({
            templateUrl: 'viewOrderModal.html',
            controller: 'ViewOrderModalCtrl',
            resolve: {
                orders: function () {
                    return orderService.getOrder(orderId);
                }
            }
        });
    };

    function getOrders() {
        orderService.getOrders().success(function(orders){
            $scope.orders = orders;
        })
    }

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

app.controller('ViewOrderModalCtrl', ['$scope', '$modalInstance', 'orders', function($scope, $modalInstance, order) {
    $scope.order = order.data;

    $scope.close = function () {
        $modalInstance.dismiss();
    };
}]);

