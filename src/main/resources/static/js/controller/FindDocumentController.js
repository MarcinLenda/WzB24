/**
 * Created by Promar on 12.10.2016.
 */

app.controller('findDocument', function ($scope, $http, $rootScope, $route, $location, $timeout, $interval, documentWZ, ngDialog, HOST) {

    $scope.form = {};
    $scope.empty = [];
    $scope.info = '';
    $scope.editData = {};
    $scope.showInfo = false;
    $scope.load = true;

    $scope.names = [];
    $scope.trader = [];
    $scope.clientSpin = false;
    $scope.traderSpin = false;


    //--------------------------------------------------------------------------------------



    $http({
        method: 'GET',
        url: HOST + '/all_client',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.names = data;

    }).error(function (data) {
        console.log('Nie udało pobrać się użytkowników.');

    });

    $http({
        method: 'GET',
        url: HOST + '/all_trader',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.trader = data;

    }).error(function (data) {
        console.log('Nie udało pobrać się użytkowników.');

    });



    $scope.focusIn = function() {
        $scope.clientSpin = true;
        $scope.message = 'Sprawdzam ...';

    };
    $scope.focusOut = function() {
        $scope.clientSpin = false;
        $scope.message = '';
    };



    //_-----------------------------------------------------------------------

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 900);

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $rootScope.documents = '';



    $scope.correctBy = function () {
        $rootScope.numberWZtoByCorrect = $scope.editData.documents.numberWZ;
        $rootScope.subProByCorrect = $scope.editData.documents.subProcess;

        ngDialog.open({
            template: 'ByCorrect',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.clickToDelete = function () {
        $rootScope.numberWZtoDelete = $scope.editData.documents.numberWZ;
        $rootScope.subProDelete = $scope.editData.documents.subProcess;

        ngDialog.open({
            template: 'AreYouSureDeleteDocument',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.correctDone = function () {
        documentWZ.correctWZ($scope.numberWZtoByCorrect, $scope.subProByCorrect);

    };

    $scope.deleteDocument = function () {
        documentWZ.deleteDocument($scope.numberWZtoDelete, $scope.subProDelete);
    };

    $scope.findByNumber = function () {
        var numberWZ = $scope.form.numberDocument;
        var subPro = $scope.form.subProcess;
        documentWZ.findDocumentByNumberWZ(numberWZ, subPro);
    };

    $scope.findByClient = function () {
       var client = '';
        if(this.nameClient != null) {
            client = this.nameClient.title;
        }
        $scope.clientSpin = false;
        $scope.message = '';
        documentWZ.findByClientName(client);

    };

    $scope.findByClientNumber = function () {
        var numberClient = $scope.form.numberClient;
        documentWZ.findByClientNr(numberClient);
    };

    $scope.findByTrader = function () {
        var traderName = '';
        if(this.nameTrader != null){
            traderName = this.nameTrader.title;
        }
        $scope.clientSpin = false;
        $scope.message = '';

        documentWZ.findByTrader(traderName);
    }

    $scope.findByNameTeam = function () {
        var nameTeam = $scope.form.nameTeam;
        documentWZ.findByNameTeam(nameTeam);

    };

});