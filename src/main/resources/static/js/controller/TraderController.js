/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', '$route', '$rootScope', 'TraderService','HOST','ngDialog', function ($scope, $http, $route, $rootScope, TraderService,HOST, ngDialog) {

    $scope.form = {};
    $scope.traders = [];
    $scope.editData = {};
    $scope.names = ["STA", "STB", "STC",
        "STE", "STX"];

    $http({
        method: 'GET',
        url: HOST + '/all_trader',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.traders = data;

    }).error(function (data) {
        console.log('Nie udało się pobrać WZ');

    });

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.createTrader = function () {
        var name = $scope.form.nameTrader;
        var surname = $scope.form.surnameTrader;
        var numberTrader = $scope.form.numberTrader;
        var nameTeam;

        if ($scope.nameTeam == 'STA') {
            nameTeam = 'STA';
        } else if ($scope.nameTeam == 'STB') {
            nameTeam = 'STB';
        } else if ($scope.nameTeam == 'STC') {
            nameTeam = 'STC';
        } else if($scope.nameTeam == 'STE') {
            nameTeam = 'STE';
        }else{
            nameTeam = 'STX';
        }

        TraderService.addTrader(name, surname, nameTeam, numberTrader);
    };

    $scope.deleteTrader = function () {
        $rootScope.surnameTrader = $scope.editData.accounts.surname;
        $rootScope.numberTrader = $scope.editData.accounts.numberTrader;
        ngDialog.open({
            template: 'DeleteTrader',
            controller: 'TraderOperation',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.deleteTraderConfirm = function () {
        TraderService.deleteTrader($rootScope.surnameTrader, $rootScope.numberTrader);
    };




}]);