angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {
    const cartPath = 'http://localhost:8188/carts/api/v1/carts/' + $localStorage.marchMarketGuestCartId;

    $scope.loadCart = function () {
        $http.get(cartPath)
            .then(function (response) {
                console.log(cartPath);
                $scope.cart = response.data;
            });
    };

    $scope.clearCart = function () {
            $http.get(cartPath + '/clear')
                .then(function (response) {
                    $scope.loadCart();
                });
        };

    $scope.removeProductFromCart = function (itemID) {
            $http.delete(cartPath + '/remove/' + itemID)
                        .then(function (response){
                             $scope.loadCart();
                    });
        };

    $scope.reduceQuantity = function (itemID) {
        $http.get(cartPath + '/reduce/' + itemID)
              .then(function (response){
                            $scope.loadCart();
        });
    };

    $scope.increaseQuantity = function (itemID) {
        $http.get(cartPath + '/increase/' + itemID)
            .then(function (response){
                 $scope.loadCart();
        });
    };

    $scope.placeOrder = function () {
        $http.post('http://localhost:8188/core/api/v1/orders')
            .then(function (response) {
                $location.path("/orders")
            });
    };

    $scope.loadCart();
});