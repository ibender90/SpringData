angular.module('data_and_angular', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage){
    const authPath = 'http://localhost:8180/auth';
    const corePath = 'http://localhost:8180/core';
    const cartPath = 'http://localhost:8180/cart';

    if ($localStorage.marchMarketUser) {
            try {
                let jwt = $localStorage.marchMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.marchMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.marchMarketUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marchMarketUser.token;
            }
    }

    $scope.tryToAuth = function () {
            $http.post(authPath + '/auth/authenticate', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.marchMarketUser = {email: $scope.user.email, token: response.data.token};

                        $scope.user.email = null;
                        $scope.user.password = null;
                    }
                }, function errorCallback(response) {
                });
    };

    $scope.tryToLogout = function () {
            $scope.clearUser();
    };

    $scope.clearUser = function () {
            delete $localStorage.marchMarketUser;
            $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
            if ($localStorage.marchMarketUser) {
                return true;
            } else {
                return false;
            }
    };

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
        $http.get(cartPath + '/carts/reduce/' + itemID)
              .then(function (response){
                            $scope.loadCart();
        });
    };

    $scope.increaseQuantity = function (itemID) {
        $http.get(cartPath + '/carts/increase/' + itemID)
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