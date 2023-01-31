angular.module('data_and_angular', []).controller('indexController', function ($scope, $http){
    const contextPath = 'http://localhost:8181/data_and_angular/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
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
        $http.get(contextPath + '/products/' + productID)
            .then(function (response){
                alert(response.data);
            });
    };

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response){
                    $scope.cart = response.data;
                });
    };

    $scope.removeProductFromCart = function (itemID) {
        $http.delete(contextPath + '/cart/remove/' + itemID)
            .then(function (response){
                 $scope.loadCart();
        });
    };

    $scope.reduceQuantity = function (itemID) {
        $http.get(contextPath + '/cart/reduce/' + itemID)
              .then(function (response){
                            $scope.loadCart();
        });
    };

    $scope.increaseQuantity = function (itemID) {
        $http.get(contextPath + '/cart/increase/' + itemID)
            .then(function (response){
                 $scope.loadCart();
        });
    };


    $scope.deleteProduct = function (productID) {
        $http.delete(contextPath + '/products/' + productID)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addNewProduct = function(newProduct){
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addProductToCart = function(productId){
            $http.get(contextPath + '/cart/add/' + productId)
                .then(function (response){
                    $scope.loadCart();
            });
        };

    $scope.placeOrder = function(cart){
            $http.post(contextPath + '/orders/new', $scope.cart)
                .then(function(response){
                    alert(response.data);
                });
    }

    $scope.loadProducts();
    $scope.loadCart();
});