/**
 * Created by Promar on 11.10.2016.
 */

app.controller('DocumentWzCtrl', ['$scope', '$rootScope', '$http', '$route', 'DocumentWzService', 'HOST', '$timeout', '$uibModal',
    function ($scope, $rootScope, $http, $route, DocumentWzService, HOST, $timeout, $uibModal) {

        $scope.form = {};
        $scope.listTrader = '';
        $scope.listClient = '';
        $scope.resultListClient = [];
        $scope.resultListTrader = [];
        $scope.showInfo = false;
        $scope.load = true;

        $scope.reloadRoute = function () {
            $route.reload();
        };


        $timeout(function () {
            $scope.showInfo = true;
            $scope.load = false;
        }, 1000);


        DocumentWzService.allDocuments()
            .then(function successCallback(response) {
                $scope.documents = response.data;
            }, function errorCallback(response) {

            });


        DocumentWzService.allDocumentsCorrect()
            .then(function successCallback(response) {
                $scope.documentsByCorrect = response.data;
            }, function errorCallback(response) {

            });


        DocumentWzService.allClient()
            .then(function successCallback(response) {
                $scope.clients = response.data;
                angular.forEach($scope.clients, function (value, key) {
                    $scope.resultListClient.push(value.abbreviationName);
                });
            }, function errorCallback(response) {
                console.log('Error: docuemntCtrl allClient')
            });


        DocumentWzService.allTrader()
            .then(function successCallback(response) {
                $scope.trader = response.data;

                console.log(response.data);

                angular.forEach($scope.trader, function (value, key) {
                    $scope.resultListTrader.push(value.surname);
                });
            }, function errorCallback(response) {

            });

        $scope.createDocument = function () {
            var numberWZ = $scope.form.numberWZ;
            var subProcess = $scope.form.subProcess;
            var client = $scope.form.nameClients;
            var nameTrader = $scope.form.myTrader;
            var date = new Date($scope.form.date);

            DocumentWzService.addWZ(numberWZ, subProcess, client, nameTrader, date)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Dodano dokument',
                    'Dodano nowy dokument WZ: ' + numberWZ +
                    ' / ' + subProcess + ' do bazy danych.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });

                    $scope.reloadRoute();

                }, function errorCallback(response) {
                    $scope.errorMessage = '';
                    if (angular.equals(response.data.errorCode, 'DOCUMENT_ALREADY_EXISTS')) {

                        $scope.errorMessage = 'Wybrany numer WZ: ' + numberWZ +
                            ' / ' + subProcess + ' istnieje w bazie danych.';
                    } else {
                        $scope.errorMessage = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';
                    }

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        $scope.errorMessage,
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });
        };


        $scope.findByNumber = function () {
            $scope.documentSearch = [];
            var numberWZ = $scope.form.numberDocument;
            var subPro = $scope.form.subProcess;

            DocumentWzService.findDocumentByNumberWZ(numberWZ, subPro)
                .then(function successCallback(response) {
                    $scope.errorCodeSearchDocument = false;
                    $scope.documentSearch.push(response.data);

                }, function errorCallback(response) {
                    if (angular.equals(response.data.errorCode, 'DOCUMENT_NOT_FOUND')) {
                        $scope.errorCodeSearchDocument = true;
                    } else {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                    }
                });
        };


        $scope.findByClient = function () {
            $scope.documentSearch = [];
            var client = '';

            if (this.nameClient != null) {
                client = this.nameClient.title;
            }

            DocumentWzService.findByClientName(client)
                .then(function successCallback(response) {
                    $scope.documentSearch = response.data;
                    $scope.responseDocument($scope.documentSearch, true);

                }, function errorCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Sprawdź, czy wybrałeś poprawnie nazwę klienta lub,' +
                        ' czy  połączenie z internetem. Jeżeli to nie pomoże, skontaktuj się z administratorem.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });
        };


        $scope.findByClientNumber = function () {
            $scope.documentSearch = [];
            var numberClient = $scope.form.numberClient;
            DocumentWzService.findByClientNr(numberClient)
                .then(function successCallback(response) {
                    $scope.documentSearch = response.data;
                    $scope.responseDocument($scope.documentSearch, true);

                }, function errorCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Sprawdź, czy wybrałeś poprawnie numer klienta lub,' +
                        ' czy posiadasz aktywne  połączenie z internetem. Jeżeli to nie pomoże, skontaktuj się z administratorem.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });
        };


        $scope.findByTrader = function () {
            $scope.documentSearch = [];

            var traderName = '';
            if (this.nameTrader != null) {
                traderName = this.nameTrader.title;
            }

            DocumentWzService.findByTrader(traderName)
                .then(function successCallback(response) {
                    $scope.documentSearch = response.data;
                    $scope.responseDocument($scope.documentSearch, false);


                }, function errorCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Sprawdź, czy wybrałeś poprawnie handlowca lub,' +
                        ' czy posiadasz aktywne  połączenie z internetem. Jeżeli to nie pomoże, skontaktuj się z administratorem.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });
        };


        $scope.findByNameTeam = function () {
            $scope.documentSearch = [];
            var nameTeam = $scope.form.nameTeam;
            DocumentWzService.findByNameTeam(nameTeam)
                .then(function successCallback(response) {
                    $scope.documentSearch = response.data;
                    $scope.responseDocument($scope.documentSearch, false);

                }, function errorCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Sprawdź, czy wybrałeś poprawnie TOK lub,' +
                        ' czy posiadasz aktywne  połączenie z internetem. Jeżeli to nie pomoże, skontaktuj się z administratorem.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });
        };


        $scope.clickToDelete = function (document) {
            var modalInstance = $scope.openModal('modalQuestion.html',
                'Usuwanie dokumentu',
                'Czy usunąć dokument: ' + document.numberWZ + ' / ' + document.subProcess,
                document);

            modalInstance.result.then(function (documentToDelete) {
                DocumentWzService.deleteDocument(documentToDelete.numberWZ, documentToDelete.subProcess)
                    .then(function successCallback(response) {

                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            'Dokument: ' + documentToDelete.numberWZ + ' / ' + documentToDelete.subProcess + ' został usunięty pomyślnie.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                        $scope.reloadRoute();

                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Doukment nie został usunięty. Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                    });
            });
        };


        $scope.correctBy = function (document) {
            var modalInstance = $scope.openModal('modalQuestion.html',
                'Korekta',
                'Czy skorygować dokument: ' + document.numberWZ + ' / ' + document.subProcess + ' ?',
                document);

            modalInstance.result.then(function (documentToCorrect) {
                DocumentWzService.correctWZ(documentToCorrect.numberWZ, documentToCorrect.subProcess)
                    .then(function successCallback(response) {

                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            'Dokument: ' + documentToCorrect.numberWZ + ' / ' + documentToCorrect.subProcess + ' został skorygowany pomyślnie.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });

                        $scope.reloadRoute();
                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Doukment nie został skorygowany. Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                    });

            });
        };


        $scope.openModal = function (template, title, responseModalBody, entity) {
            $rootScope.responseModalBody = responseModalBody;
            $rootScope.titleModal = title;
            return $uibModal.open({
                templateUrl: template,
                controller: 'ModalInstanceCtrlRole',
                controllerAs: '$ctrl',
                resolve: {
                    title: function () {
                        return title;
                    },
                    responseModalBody: function () {
                        return responseModalBody;
                    },
                    entity: function () {
                        return entity;
                    }
                }
            });
        };


        $scope.responseDocument = function (documentSearch, orCheck) {
            $scope.checkDocuments = documentSearch;
            $scope.orCheck = orCheck;
            if ($scope.checkDocuments.length > 5 && $scope.orCheck) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Uwaga',
                    'Ten klient posiada zbyt dużą ilość nieodebranych dokumentów.' +
                    ' Prosimy o niezwłoczny kontakt z klientem.',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });
            }

            if (!angular.equals($scope.checkDocuments.length, 0)) {
                $scope.errorCodeSearchDocument = false;
            } else {
                $scope.errorCodeSearchDocument = true;
            }
        };

    }
]);