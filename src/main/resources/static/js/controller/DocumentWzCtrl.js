/**
 * Created by Promar on 12.10.2016.
 */

app.controller('documentCtrl', function ($scope, $http, $rootScope, $route, $location, $timeout, $interval, documentWZ, $uibModal, HOST) {

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
    $rootScope.documentSearch = [];


    //--------------------------------------------------------------------------------------

    $rootScope.documentsByCorrect = [];
    $http({
        method: 'GET',
        url: HOST + '/find_correct',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $rootScope.documentsByCorrect = data;


    }).error(function (data) {
        ngDialog.open({
            template: 'errorFindDocument',
            controller: 'findDocument',
            className: 'ngdialog-theme-default'
        });

    });


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
        $scope.message = 'Kliknij na skrót klienta ...';

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

    $rootScope.documents = [];

    $http({
        method: 'GET',
        url: HOST + '/showAllDocuments',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.documents = data;

    }).error(function (data) {
        console.log('Nie udało się pobrać WZ');

    });

    $scope.correctBy = function (document) {
        $rootScope.titleModal = 'Korekta';
        $rootScope.responseModalBody = 'Czy skorygować dokument: '+document.numberWZ+' / '+document.subProcess;

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return document;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            documentWZ.correctWZ(selectedItem.numberWZ, selectedItem.subProcess);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.clickToDelete = function (document) {
        $rootScope.titleModal = 'Usuwanie dokumentu';
        $rootScope.responseModalBody = 'Czy usunąć dokument: '+document.numberWZ+' / '+document.subProcess;

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return document;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            documentWZ.deleteDocument(selectedItem.numberWZ, selectedItem.subProcess);
        }, function () {
            console.log('Anulowano');
        });
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
    };

    $scope.findByNameTeam = function () {
        var nameTeam = $scope.form.nameTeam;
        documentWZ.findByNameTeam(nameTeam);

    };

});