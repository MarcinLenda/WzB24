/**
 * Created by Promar on 03.11.2016.
 */

app.controller('ClientOperation', ['$scope', '$rootScope', '$http','$route', 'ClientService', 'HOST', 'ngDialog', function ($scope, $rootScope, $http, $route, ClientService, HOST, ngDialog) {

    $scope.form = {};
    $scope.clients = [];
    $scope.editData = {};
    $scope.names = ["STA", "STB", "STC",
        "STE", "STX"];

    $http({
        method: 'GET',
        url: HOST + '/all_client',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.clients = data;


    }).error(function (data) {
        console.log('Nie udało się pobrać WZ');

    });

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.createAccountClient = function () {

        var nameClient = $scope.form.nameClient;
        var numberClient = $scope.form.numberClient;
        var abbreviationNameClient = $scope.form.abbreviationName;
        var nameTeam;

        if ($scope.nameTeam == 'STA') {
            nameTeam = 'STA';
        } else if ($scope.nameTeam == 'STB') {
            nameTeam = 'STB';
        } else if ($scope.nameTeam == 'STC') {
            nameTeam = 'STC';
        } else if ($scope.nameTeam == 'STE') {
            nameTeam = 'STE';
        }else{
            nameTeam = 'STX'
        }

        ClientService.addClient(nameClient, numberClient, nameTeam, abbreviationNameClient);
    };

    $scope.deleteClient = function () {
        $rootScope.nameClient = $scope.editData.accounts.name;
        $rootScope.numberClient = $scope.editData.accounts.numberClient;

        ngDialog.open({
            template: 'DeleteClient',
            controller: 'ClientOperation',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.deleteClientConfirm = function () {

        ClientService.deleteClient($rootScope.nameClient ,$rootScope.numberClient);
    };

}]);