/**
 * Created by Promar on 04.11.2016.
 */

app.controller('AdminController', ['$scope', '$http', '$route', '$timeout', 'HOST', 'AdminService',
    function ($scope, $http, $route, $timeout, HOST, AdminService) {

        $scope.showInfo = false;
        $scope.load = true;


        $timeout(function () {
            $scope.showInfo = true;
            $scope.load = false;
        }, 1000);

        $scope.reloadRoute = function () {
            $route.reload();
        };


        AdminService.findCorrect()
            .then(function successCallback(response) {
                if (!angular.equals(response.data.length, 0)) {
                    $scope.isCorrect = true;
                }
            }, function errorCallback(response) {
                console.log('Error: find correct');
            });


        AdminService.findNotActiveAccount()
            .then(function successCallback(response) {
                if (!angular.equals(response.data.length, 0)) {
                    $scope.isNotActiveAccount = true;
                }
            }, function errorCallback(response) {
                console.log('Error: find not active account');
            });

    }]);