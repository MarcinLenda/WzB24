/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', '$route', '$rootScope', 'TraderService','HOST','$uibModal', function ($scope, $http, $route, $rootScope, TraderService,HOST, $uibModal) {

    $scope.form = {};
    $scope.traders = [];
    $rootScope.edit = {};
    $scope.names = ['STA', 'STB', 'STC',
        'STE', 'STG', 'STX'];

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
        }else if($scope.nameTeam == 'STG') {
            nameTeam = 'STG';
        }else{
            nameTeam = 'STX';
        }

        TraderService.addTrader(name, surname, nameTeam, numberTrader);
    };


    $scope.deleteTrader = function (account) {
        $rootScope.titleModal = 'Usuwanie handlowca ';
        $rootScope.responseModalBody = 'Czy chcesz usunąć handlowca: "' + account.name + '" ?';


        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl2',
            controllerAs: '$ctrl'
        });

        modalInstance.result.then(function (selectedItem) {
            TraderService.deleteTrader(account.numberTrader, account.surname);
        }, function () {
            console.log('Anulowano');
        });
    };


    $scope.editNameTrader = function (account) {
        $rootScope.titleModal = 'Edycja imienia handlowca';
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
            $scope.editTrader(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editSurnameTrader = function (account) {
        $rootScope.titleModal = 'Edycja nazwiska handlowca';
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
            selectedItem.surname = $rootScope.edit.update;
            $scope.editTrader(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNumberTrader = function (account) {
        $rootScope.titleModal = 'Edycja numeru handlowca';
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
            selectedItem.newNumberTrader = $rootScope.edit.update;
            $scope.editTrader(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNameTeamTrader = function (account) {
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
            $scope.editTrader(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editTrader = function (account) {

        $http({
            method: 'POST',
            url: HOST + '/edit_trader',
            data: account,
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.titleModal = 'Edycja handlowca';
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
                $rootScope.responseModalBody = 'Handlowiec o podanym numerzy istnieje już w bazie danych.';

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