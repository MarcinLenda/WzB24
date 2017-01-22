/**
 * Created by Promar on 03.11.2016.
 */

app.controller('ClientOperation', ['$scope', '$rootScope', '$http', '$route', '$uibModal', 'ClientService', 'HOST', function ($scope, $rootScope, $http, $route, $uibModal, ClientService, HOST) {

    $scope.form = {};
    $scope.clients = [];
    $rootScope.edit = {};
    $scope.names = ["STA", "STB", "STC",
        "STE", "STG", "STX"];

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
        } else if ($scope.nameTeam == 'STG') {
            nameTeam = 'STG';
        } else {
            nameTeam = 'STX'
        }

        ClientService.addClient(nameClient, numberClient, nameTeam, abbreviationNameClient);
    };

    $scope.deleteClient = function (account) {
        $rootScope.titleModal = 'Usuwanie klienta ';
        $rootScope.responseModalBody = 'Czy chcesz usunąć klienta: "' + account.name + '" ?';


        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl2',
            controllerAs: '$ctrl'
        });

        modalInstance.result.then(function (selectedItem) {
            ClientService.deleteClient(account.numberClient, account.name);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNameClient = function (account) {
        $rootScope.titleModal = 'Edycja nazwy klienta';
        $rootScope.edit.update = '';

        var modalInstance = $uibModal.open({
            templateUrl: 'updateDataAccount.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            selectedItem.name = $rootScope.edit.update;
            $scope.editClient(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNumberClient = function (account) {
        $rootScope.titleModal = 'Edycja numeru klienta';
        $rootScope.edit.update = '';

        var modalInstance = $uibModal.open({
            templateUrl: 'updateDataAccount.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            selectedItem.newNumberClient = $rootScope.edit.update;
            $scope.editClient(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNameTeamClient = function (account) {
        $rootScope.titleModal = 'Edycja TOK-u';
        $rootScope.edit.update = '';

        var modalInstance = $uibModal.open({
            templateUrl: 'updateDataAccount.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            selectedItem.nameTeam = $rootScope.edit.update;
            $scope.editClient(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editClient = function (account) {

        $http({
            method: 'POST',
            url: HOST + '/edit_client',
            data: account,
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.titleModal = 'Edycja klienta';
            $rootScope.responseModalBody = 'Wartość pola klienta "'+account.name+'" została zautkualizowana pomyślnie.';

            var modalInstance = $uibModal.open({
                templateUrl: 'updateResponseFromServer.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                resolve: {
                    entity: function () {
                        return account;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
            }, function () {
                console.log('Anulowano');
            });
            $scope.reloadRoute();

        }, function errorCallback(response) {
            $rootScope.titleModal = 'Błąd edycji';

            if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                $rootScope.responseModalBody = 'Podany numer klienta jest już zajęty.';

            } else if (angular.equals(response.data.errorCode, 'TEAM_NOT_FOUND_TEAM')) {
                $rootScope.responseModalBody = 'Podany TOK nie istnieje.';
            } else {
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';
            }

            var modalInstance = $uibModal.open({
                templateUrl: 'updateResponseFromServer.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                resolve: {
                    entity: function () {
                        return account;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
            }, function () {
                console.log('Anulowano');
            });
            $scope.reloadRoute();
        });
    };

}]);