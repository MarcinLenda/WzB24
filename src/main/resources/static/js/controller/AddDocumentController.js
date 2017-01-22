/**
 * Created by Promar on 11.10.2016.
 */

app.controller('DocumentOperation', ['$scope', '$http', '$window', '$route','$filter', 'documentWZ','HOST',
    function ($scope, $http, $window, $route, $filter, documentWZ, HOST) {

    $scope.form = {};
    $scope.listTrader = '';
    $scope.listClient = '';
    $scope.resultListClient = [];
    $scope.resultListTrader = [];


    $scope.reloadRoute = function () {
        $route.reload();
    };

    $http({
        method: 'GET',
        url: HOST + '/all_trader',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.listTrader = data;

            angular.forEach($scope.listTrader, function (value, key) {
                $scope.resultListTrader.push(value.surname);
            });

        }).error(function (data) {
        $scope.listClient = 'Nie udało się pobrać listy handlowców.'
    });

    $http({
        method: 'GET',
        url: HOST + '/all_client',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.listClient = data;

            angular.forEach($scope.listClient, function (value, key) {
                $scope.resultListClient.push(value.abbreviationName);
            });


        }).error(function (data) {
        $scope.listClient = 'Nie udało się pobrać listy klientów.'
    });


    $scope.createDocument = function () {

        var numberWZ = $scope.form.numberWZ;
        var subProcess = $scope.form.subProcess;
        var client = $scope.form.nameClients;
        var nameTrader = $scope.form.myTrader;
        var date = new Date($scope.form.date);

        documentWZ.addWZ(numberWZ, subProcess, client, nameTrader, date);
    };


    $scope.deleteWZ = function () {
        var numberWZ = $scope.form.numberWZ;
        documentWZ.removeWZ(numberWZ);
    }


}]);