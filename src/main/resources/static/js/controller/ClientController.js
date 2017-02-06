/**
 * Created by Promar on 03.11.2016.
 */

app.controller('ClientOperation', ['$scope', '$rootScope', '$http', '$route', '$uibModal', 'ClientService', 'HOST',
    function ($scope, $rootScope, $http, $route, $uibModal, ClientService, HOST) {

        $scope.form = {};
        $scope.clients = [];
        $rootScope.edit = {};
        $scope.names = ["STA", "STB", "STC",
            "STE", "STG", "STX"];

        $scope.reloadRoute = function () {
            $route.reload();
        };

        ClientService.allClient()
            .then(function successCallback(response) {
                $scope.clients = response.data;
            }, function errorCallback(response) {
                console.log('Error: all_client');
            });


        $scope.createAccountClient = function () {
            var nameClient = $scope.form.nameClient;
            var numberClient = $scope.form.numberClient;
            var abbreviationNameClient = $scope.form.abbreviationName;
            var nameTeam = $scope.nameTeam;

            ClientService.addClient(nameClient, numberClient, nameTeam, abbreviationNameClient)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Dodano klienta',
                        'Klient: "' + nameClient + '" został dodany do bazy danych. ',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });

                    $rootScope.reloadRoute();

                }, function errorCallback(response) {

                    if (angular.equals(response.data.errorCode, 'CLIENT_ALREADY_EXISTS')) {
                        $scope.errorMessage = 'Klient "' + nameClient + '" ' +
                            'istnieje. Podany skrót lub numer klienta istnieje już w bazie danych.';
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

        $scope.deleteClient = function (account) {
            var modalInstance = $scope.openModal('modalQuestion.html',
                'Usuwanie klienta',
                'Czy chcesz usunąć klienta: "' + account.name + '" ?',
                {});

            modalInstance.result.then(function (modifiedAccount) {
                ClientService.deleteClient(account.numberClient, account.name)
                    .then(function successCallback(response) {

                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            'Klient: "' + account.name + '" został usnięty z bazy danych. ',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                        $rootScope.reloadRoute();

                    }, function errorCallback(response) {

                        if (angular.equals(response.data.errorCode, 'CLIENT_HAS_DOCUMENT')) {
                            $scope.errorMessage = 'Do podanego klienta istnieją przypisane dokumentu WZ w bazie danych.' +
                                ' Usunięcie klienta jest niemożliwe. ';
                        } else {

                            $scope.errorMessage = 'Klient nie został usunięty. Sprawdź połączenie z internetem lub ' +
                                'skontaktuj się administratorem.';
                        }

                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            $scope.errorMessage,
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                        $rootScope.reloadRoute();

                    });
            });
        };

        $scope.editNameClient = function (account) {
            var clientCopy = angular.copy(account);
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja nazwy klienta',
                '',
                {});

            modalInstance.result.then(function (modifiedClient) {
                clientCopy.name = modifiedClient.value;
                $scope.editClient(clientCopy);
            });
        };

        $scope.editNumberClient = function (account) {
            var clientCopy = angular.copy(account);
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja numeru klienta',
                '',
                {});

            modalInstance.result.then(function (modifiedClient) {
                clientCopy.newNumberClient = modifiedClient.value;
                $scope.editClient(clientCopy);
            });
        };

        $scope.editNameTeamClient = function (account) {
            var clientCopy = angular.copy(account);
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja TOK-u',
                '',
                {});

            modalInstance.result.then(function (modifiedClient) {
                clientCopy.nameTeam = modifiedClient.value;
                $scope.editClient(clientCopy);
            });
        };

        $scope.editClient = function (account) {

            ClientService.editClient(account)
                .then(function successCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Edycja klienta',
                        'Wartość pola klienta "' + account.name + '" została zautkualizowana pomyślnie.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });

                    $scope.reloadRoute();

                }, function errorCallback(response) {

                    if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                        $scope.errorMessage = 'Podany numer klienta jest już zajęty.';

                    } else if (angular.equals(response.data.errorCode, 'TEAM_NOT_FOUND_TEAM')) {
                        $scope.errorMessage = 'Podany TOK nie istnieje.';
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

    }]);