/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', '$route', '$rootScope', 'TraderService','HOST','$uibModal', function ($scope, $http, $route, $rootScope, TraderService,HOST, $uibModal) {

    $scope.form = {};
    $scope.traders = [];
    $rootScope.edit = {};
    $scope.names = ['STA', 'STB', 'STC',
        'STE', 'STG', 'STX'];

    $scope.reloadRoute = function () {
        $route.reload();
    };


    TraderService.allTrader()
        .then(function successCallback(response) {
            $scope.traders = data;
        }, function errorCallback(response) {
            console.log('Error: all traders in TraderCtrl');
        });


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

        TraderService.addTrader(name, surname, nameTeam, numberTrader)
            .then(function successCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Dodano handlowca',
                    'Handlowiec: "'+name +' '+ surname + '" został dodany do bazy danych. ',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });

                $rootScope.reloadRoute();

            }, function errorCallback(response) {
                if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                    $scope.errorMessage = 'Handlowiec: "'+name +' '+ surname +'" ' +
                        'istnieje. Podane nazwisko lub numer handlowca istnieje już w bazie danych.';
                } else {
                    $scope.errorMessage = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';
                }

                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd',
                    $scope.errorMessage,
                    {});

            });

    };


    $scope.deleteTrader = function (account) {
        var modalInstance = $scope.openModal('modalQuestion.html',
            'Usuwanie handlowca',
            'Czy chcesz usunąć handlowca: "' + account.name +' '+account.surname+ '" ?',
            {});

        modalInstance.result.then(function (modifiedAccount) {
            TraderService.deleteTrader(account.numberTrader, account.surname)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Sukces',
                        'Handlowiec: "' + account.name +' '+account.surname + '" został usnięty z bazy danych. ',
                        {});
                    $rootScope.reloadRoute();

                }, function errorCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Handlowiec: "' + account.name +' '+account.surname + '" nie został usnięty z bazy danych. ' +
                        'Sprawdź połączenie z internetem lub skontaktuj się z handlowcem.',
                        {});
                });
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