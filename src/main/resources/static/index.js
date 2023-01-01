angular.module('data_and_angular', []).controller('indexController', function ($scope, $http){
    const contextPath = 'http://localhost:8181/data_and_angular';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/product')
            .then(function (response){
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productID) {
        $http.get(contextPath + '/product/delete/' + productID)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addNewProduct = function(newProduct){
        console.log($scope.newProduct);
        $http.post(contextPath + '/product', $scope.newProduct)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.loadProducts();
});