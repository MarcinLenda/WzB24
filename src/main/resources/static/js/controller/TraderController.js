/**
 * Created by Promar on 03.11.2016.
 */

app.controller('TraderOperation', ['$scope', '$http', '$route', '$rootScope', 'TraderService', 'HOST', '$uibModal', function ($scope, $http, $route, $rootScope, TraderService, HOST, $uibModal) {

    $scope.form = {};
    $scope.traders = [];
    $rootScope.edit = {};
    $scope.names = ['STA', 'STB', 'STC',
        'STE', 'STG', 'STX'];

    $scope.reloadRoute = function () {
        $route.reload();
    };


    TraderService.allTraders()
        .then(function successCallback(response) {
            $scope.traders = response.data;
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
        } else if ($scope.nameTeam == 'STE') {
            nameTeam = 'STE';
        } else if ($scope.nameTeam == 'STG') {
            nameTeam = 'STG';
        } else {
            nameTeam = 'STX';
        }


        TraderService.addTrader(name, surname, nameTeam, numberTrader)
            .then(function successCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Dodano handlowca',
                    'Handlowiec: "' + name + ' ' + surname + '" został dodany do bazy danych. ',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });

                $rootScope.reloadRoute();

            }, function errorCallback(response) {
                if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                    $scope.errorMessage = 'Handlowiec: "' + name + ' ' + surname + '" ' +
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


    $scope.deleteTrader = function (trader) {
        var modalInstance = $scope.openModal('modalQuestion.html',
            'Usuwanie handlowca',
            'Czy chcesz usunąć handlowca: "' + trader.name + ' ' + trader.surname + '" ?',
            trader);

        modalInstance.result.then(function (trader) {
            TraderService.deleteTrader(trader.numberTrader, trader.surname)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Sukces',
                        'Handlowiec: "' + trader.name + ' ' + trader.surname + '" został usnięty z bazy danych. ',
                        {});
                    $rootScope.reloadRoute();

                }, function errorCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Handlowiec: "' + trader.name + ' ' + trader.surname + '" nie został usnięty z bazy danych. ' +
                        'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                        {});
                });
        });
    };


    $scope.editNameTrader = function (trader) {
        var traderCopy = angular.copy(trader);
        var modalInstance = $scope.openModal('updateDataAccount.html',
            'Edycja imienia handlowca',
            '',
            {});

        modalInstance.result.then(function (modifiedTrader) {
            traderCopy.name = modifiedTrader.value;
            $scope.editTrader(traderCopy);
        });
    };

    $scope.editSurnameTrader = function (trader) {
        var traderCopy = angular.copy(trader);
        var modalInstance = $scope.openModal('updateDataAccount.html',
            'Edycja nazwiska handlowca',
            '',
            {});

        modalInstance.result.then(function (modifiedTrader) {
            traderCopy.surname = modifiedTrader.value;
            $scope.editTrader(traderCopy);
        });
    };

    $scope.editNumberTrader = function (trader) {
        var traderCopy = angular.copy(trader);
        var modalInstance = $scope.openModal('updateDataAccount.html',
            'Edycja numeru handlowca',
            '',
            {});

        modalInstance.result.then(function (modifiedTrader) {
            traderCopy.newNumberTrader = modifiedTrader.value;
            $scope.editTrader(traderCopy);
        });
    };

    $scope.editNameTeamTrader = function (trader) {
        var traderCopy = angular.copy(trader);
        var modalInstance = $scope.openModal('updateDataAccount.html',
            'Edycja TOK-u handlowca',
            '',
            {});

        modalInstance.result.then(function (modifiedTrader) {
            traderCopy.nameTeam = modifiedTrader.value;
            $scope.editTrader(traderCopy);
        });
    };

    $scope.editTrader = function (trader) {

        TraderService.editTraderData(trader)
            .then(function successCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Sukces',
                    'Wartość pola : "' + trader.name + ' ' + trader.surname + '" została zautkualizowana pomyślnie.',
                    {});
                $scope.reloadRoute();

            }, function errorCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd',
                    'Nie udało się zaktualizować pola. Sprawdź połączenie z internetem lub ' +
                    'skontaktuj się z adminsitratorem.',
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