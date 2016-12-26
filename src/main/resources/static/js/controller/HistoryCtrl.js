/**
 * Created by Promar on 22.11.2016.
 */

app.controller('HistoryCtrl', ['$scope', '$http',
    function ($scope, $http) {

    $scope.deleteDocument = [];
    $scope.correctsDocument = [];
    $scope.loggedInUser = [];

        $http({
            method: 'GET',
            url: '/all_deleteE'
        }).success(function (data) {
            $scope.deleteDocument = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });

        $http({
            method: 'GET',
            url: '/all_corrects'
        }).success(function (data) {
            $scope.correctsDocument = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });

        $http({
            method: 'GET',
            url: '/all_logged'
        }).success(function (data) {
            $scope.loggedInUser = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });


    }]);