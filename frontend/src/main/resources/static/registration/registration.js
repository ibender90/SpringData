angular.module('market').controller('registrationController', function ($scope, $http, $location, $localStorage) {

    $scope.register = function () {
        $http.post('http://localhost:8188/auth/api/v1/auth/register', $scope.reguser)
            .then(function (response) {
                if (response.data.token) {
                                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                                    $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};
                $localStorage.reguser = null;
                $location.path("/"); }
            });
    };

});