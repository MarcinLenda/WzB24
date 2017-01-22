/**
 * Created by Promar on 28.10.2016.
 */

app.service('documentWZ', function ($rootScope, $http, $route, $uibModal,HOST) {

        $rootScope.showInfo = false;
        $rootScope.errorCodeSearchDocument = false;

        $rootScope.reloadRoute = function () {
            $route.reload();
        };

        this.addWZ = function (numberWZ, subProcess, client, traderName,
                               date) {
            $http({
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
            })
                .then(function successCallback(response) {

                    $rootScope.titleModal = 'Dodano dokument';
                    $rootScope.responseModalBody = 'Dodano nowy dokument WZ: '+numberWZ+
                        ' / '+subProcess+ ' do bazy danych.';

                    var modalInstance = $uibModal.open({
                        templateUrl: 'updateResponseFromServer.html',
                        controller: 'ModalInstanceCtrl2',
                        controllerAs: '$ctrl'
                    });

                    modalInstance.result.then(function (selectedItem) {
                    }, function () {
                        console.log('Anulowano');
                    });
                    $rootScope.reloadRoute();

                }, function errorCallback(response) {
                    if (angular.equals(response.data.errorCode, 'DOCUMENT_ALREADY_EXISTS')) {
                        $rootScope.titleModal = 'Błąd dodawanie dokumentu';
                        $rootScope.responseModalBody = 'Wybrany numer WZ: '+numberWZ+
                            ' / '+subProcess+' istnieje w bazie danych.';
                    } else {
                        $rootScope.titleModal = 'Błąd dodawanie dokumentu: ' + numberWZ;
                        $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';
                    }

                    var modalInstance = $uibModal.open({
                        templateUrl: 'updateResponseFromServer.html',
                        controller: 'ModalInstanceCtrl2',
                        controllerAs: '$ctrl'
                    });

                    modalInstance.result.then(function (selectedItem) {
                    }, function () {
                        console.log('Anulowano');
                    });
                });
        };

        this.findDocumentByNumberWZ = function (numberWZ, subPro) {
            $rootScope.showInfo = false;
            $rootScope.documentSearch = [];
            $http({
                method: 'POST',
                url: HOST + '/findByNumber',
                data: {
                    "numberWZ": numberWZ,
                    "subPro": subPro
                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {
                $rootScope.errorCodeSearchDocument = false;
                $rootScope.documentSearch.push(response.data);

            }, function errorCallback(response) {
                if (angular.equals(response.data.errorCode, 'DOCUMENT_NOT_FOUND')) {
                    $rootScope.errorCodeSearchDocument = true;
                } else {

                    $rootScope.titleModal = 'Błąd';
                    $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                    var modalInstance = $uibModal.open({
                        templateUrl: 'updateResponseFromServer.html',
                        controller: 'ModalInstanceCtrl2',
                        controllerAs: '$ctrl'
                    });

                    modalInstance.result.then(function (selectedItem) {
                    }, function () {
                        console.log('Anulowano');
                    });
                }
            });

        };

        this.findByClientName = function (client) {
            $rootScope.documentSearch = [];

            $http({
                method: 'POST',
                url: HOST + '/findByClient',
                data: {
                    "abbreviationName": client
                },
                headers: {'Content-type': 'application/json'},
            }).then(function successCallback(response) {

                $rootScope.documentSearch = response.data;
                if ($rootScope.documentSearch.length != 0) {
                    $rootScope.errorCodeSearchDocument = false;
                } else {
                    $rootScope.errorCodeSearchDocument = true;
                }

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });

        };

        this.findByClientNr = function (clientNumber) {
            $rootScope.documentSearch = [];

            $http({
                method: 'POST',
                url: HOST + '/findByClientNumber',
                data: {

                    "findClientNumber": clientNumber
                },

                headers: {'Content-type': 'application/json'},
            }).then(function successCallback(response) {

                $rootScope.documentSearch = response.data;
                if ($rootScope.documentSearch.length != 0) {
                    $rootScope.errorCodeSearchDocument = false;
                } else {
                    $rootScope.errorCodeSearchDocument = true;
                }

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });
        };

        this.findByTrader = function (traderName) {
            $rootScope.documentSearch = [];

            $http({
                method: 'POST',
                url: HOST + '/findByTraderName',
                data: traderName,

                headers: {'Content-type': 'application/json'},
            }).then(function successCallback(response) {

                $rootScope.documentSearch = response.data;
                if ($rootScope.documentSearch.length != 0) {
                    $rootScope.errorCodeSearchDocument = false;
                } else {
                    $rootScope.errorCodeSearchDocument = true;
                }

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });
        };

        this.findByNameTeam = function (nameTeam) {
            $rootScope.documentSearch = [];

            $http({
                method: 'POST',
                url: HOST + '/find_nameteam',
                data: nameTeam,
                headers: {'Content-type': 'application/json'},
            }).then(function successCallback(response) {

                $rootScope.documentSearch = response.data;

                if ($rootScope.documentSearch.length != 0) {
                    $rootScope.errorCodeSearchDocument = false;
                } else {
                    $rootScope.errorCodeSearchDocument = true;
                }

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });
        };


        this.deleteDocument = function (numberWZ, subProcess) {
            $http({
                method: 'DELETE',
                url: HOST + '/deleteDocument',
                data: {
                    "numberWZ": numberWZ,
                    "subPro": subProcess
                },
                headers: {'Content-type': 'application/json'},
            }).then(function successCallback(response) {

                $rootScope.titleModal = 'Usunięto dokument';
                $rootScope.responseModalBody = 'Dokument: '+numberWZ + ' / '+subProcess+' został usunięty pomyślnie.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });

                $rootScope.reloadRoute();

            }, function errorCallback(response) {

                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });

        };


        this.correctWZ = function (numberWZ, subPro) {
            $rootScope.documents = [];
            $http({
                method: 'PATCH',
                url: HOST + '/by_correct',
                data: {
                    "numberWZ": numberWZ,
                    "subPro": subPro
                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {

                $rootScope.titleModal = 'Korekta dokument';
                $rootScope.responseModalBody = 'Dokument: '+numberWZ + ' / '+subPro+' został skorygowany pomyślnie.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $rootScope.reloadRoute();
            }, function errorCallback(response) {

                $rootScope.titleModal = 'Błąd';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });
        };

    }
);

app.controller('ModalInstanceCtrl2', function ($uibModalInstance, $rootScope) {
    var $ctrl = this;

    $rootScope.orUser = true;

    $ctrl.ok = function () {
        $uibModalInstance.close();
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
