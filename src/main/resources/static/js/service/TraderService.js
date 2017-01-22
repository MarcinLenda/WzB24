/**
 * Created by Promar on 03.11.2016.
 */

app.service('TraderService', function ($rootScope, $http, $uibModal, HOST) {

    this.addTrader = function (name, surname, nameTeam, numberTrader) {

        $http({
            method: 'POST',
            url: HOST + '/save_trader',
            data: {
                "name": name,
                "surname": surname,
                "nameTeam": nameTeam,
                "numberTrader": numberTrader
            },
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {
            $rootScope.titleModal = 'Dodano handlowca ';
            $rootScope.responseModalBody = 'Handlowiec: ' + name + ' został dodany do bazy danych. ';

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

            console.log(response.status);

            $rootScope.titleModal = 'Błąd dodawania';
            if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                $rootScope.responseModalBody = 'Handlowiec o numerze "' + numberTrader + '" istnieje. Podany klient istnieje już w bazie danych.';
            } else if (angular.equals(response.status, 400)) {
                $rootScope.responseModalBody = 'Sprawdź czy wypełniłeś poprawnie wszystkie pola.';
            } else {
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


    this.deleteTrader = function (numberTrader, surname) {

        $http({
            method: 'DELETE',
            url: HOST + '/delete_trader',
            data: {
                "surname": surname,
                "numberTrader": numberTrader

            },
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {
            $rootScope.titleModal = 'Usunięto handlowca ';
            $rootScope.responseModalBody = 'Handlowiec o numerze: "'+ numberTrader +'" został usunięty z bazy danych. ';

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

            $rootScope.titleModal = 'Błąd usuwania';
            $rootScope.responseModalBody = 'Nie usunięto handlowca';
            $rootScope.responseModalBody = '';
            if(angular.equals(response.status, 500)){
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

});
