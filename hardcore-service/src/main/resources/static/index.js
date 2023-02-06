angular.module('data_and_angular', []).controller('indexController', function ($scope, $http){
    const corePath = 'http://localhost:8181/market/api/v1';
    const cartPath = 'http://localhost:8182/market-carts/api/v1/cart';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: corePath + '/products',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response){
                $scope.ProductsList = response.data.content;
            });
    };

    $scope.showProductInfo = function (productID) {
        $http.get(corePath + '/products/' + productID)
            .then(function (response){
                alert(response.data);
            });
    };

    $scope.loadCart = function () {
        $http.get(cartPath)
            .then(function (response){
                    $scope.cart = response.data;
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


    $scope.deleteProduct = function (productID) {
        $http.delete(corePath + '/products/' + productID)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addNewProduct = function(newProduct){
        $http.post(corePath + '/products', $scope.newProduct)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addProductToCart = function(productId){
            $http.get(cartPath + '/add/' + productId)
                .then(function (response){
                    $scope.loadCart();
            });
        };

    $scope.placeOrder = function(){
            $http.post(corePath + '/orders/new')
                .then(function(response){
                    alert(response.data);
                });
    };

    $scope.loadProducts();
    $scope.loadCart();
});