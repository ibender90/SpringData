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

    $scope.deleteProduct = function (productID) {
        $http.delete(contextPath + '/products/' + productID)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.addNewProduct = function(newProduct){
        console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response){
                $scope.loadProducts();
        });
    };

    $scope.loadProducts();
});