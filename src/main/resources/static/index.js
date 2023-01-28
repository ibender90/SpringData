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
        $http.get(contextPath + '/added_to_cart_products')
            .then(function (response){
                    $scope.PurchasedProductsList = response.data;
                });
    };

    $scope.removeProductFromCart = function(productID) {
        $http.delete(contextPath + '/added_to_cart_products/' + productID)
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

    $scope.addProductToCart = function(productToAdd){
            $http.post(contextPath + '/added_to_cart_products', productToAdd)
                .then(function (response){
                    $scope.loadCart();
            });
        };

    $scope.loadProducts();
    $scope.loadCart();
});