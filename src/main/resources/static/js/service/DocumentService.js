/**
 * Created by Promar on 28.10.2016.
 */

app.service('DocumentWzService', function ($rootScope, $http, $route, $uibModal, HOST) {

    $rootScope.showInfo = false;
    $rootScope.errorCodeSearchDocument = false;
    var self = this;

    $rootScope.reloadRoute = function () {
        $route.reload();
    };


    self.addWZ = function (numberWZ, subProcess, client, traderName,
                           date) {
        return $http({
            method: 'POST',
            url: HOST + '/saveDocument',
            data: {
                "numberWZ": numberWZ,
                "subProcess": subProcess,
                "client": client,
                "traderName": traderName,
                "date": date
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.findDocumentByNumberWZ = function (numberWZ, subPro) {
        return $http({
            method: 'POST',
            url: HOST + '/findByNumber',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.findByClientName = function (client) {
        return $http({
            method: 'POST',
            url: HOST + '/findByClient',
            data: {
                "abbreviationName": client
            },
            headers: {'Content-type': 'application/json'},
        });
    };


    self.findByClientNr = function (clientNumber) {
        return $http({
            method: 'POST',
            url: HOST + '/findByClientNumber',
            data: {
                "findClientNumber": clientNumber
            },
            headers: {'Content-type': 'application/json'},
        });
    };


    self.findByTrader = function (traderName) {
        return $http({
            method: 'POST',
            url: HOST + '/findByTraderName',
            data: traderName,
            headers: {'Content-type': 'application/json'},
        });
    };


    self.findByNameTeam = function (nameTeam) {
        return $http({
            method: 'POST',
            url: HOST + '/find_nameteam',
            data: nameTeam,
            headers: {'Content-type': 'application/json'},
        });
    };


    self.deleteDocument = function (numberWZ, subProcess) {
        return $http({
            method: 'DELETE',
            url: HOST + '/deleteDocument',
            data: {
                "numberWZ": numberWZ,
                "subPro": subProcess
            },
            headers: {'Content-type': 'application/json'},
        });
    };


    self.correctWZ = function (numberWZ, subPro) {
        return $http({
            method: 'PATCH',
            url: HOST + '/by_correct',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.allDocuments = function () {
        return $http({
            method: 'GET',
            url: HOST + '/showAllDocuments',
            headers: {'Content-type': 'application/json'},
        })
    };


    self.allDocumentsCorrect = function () {
        return $http({
            method: 'GET',
            url: HOST + '/find_correct',
            headers: {'Content-type': 'application/json'},
        });
    };


    self.allClient = function () {
        return $http({
            method: 'GET',
            url: HOST + '/client/all_client',
            headers: {'Content-type': 'application/json'},
        });
    };


    self.allTrader = function () {
        return $http({
            method: 'GET',
            url: HOST + '/all_trader',
            headers: {'Content-type': 'application/json'},
        })
    };

});


