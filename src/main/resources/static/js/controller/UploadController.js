/**
 * Created by Promar on 11.12.2016.
 */

app.controller('UploadController', function ($scope, $rootScope, $route, FileUploader, HOST, $http, $uibModal) {

    $rootScope.lastDateUpdate = '';
    $rootScope.dateUpdate = $rootScope.lastDateUpdate;


    $scope.uploader = new FileUploader(
        {
            url: 'http://localhost:8080/upload'
        }
    );

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.update = function () {

        $http({
            method: 'GET',
            url: HOST + '/save_items',
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.lastDateUpdate = new Date();
            $rootScope.titleModal = 'Aktualizacja RF';
            $rootScope.responseModalBody = 'Przebiegła pomyślnie.';
            $rootScope.dateUpdate = $rootScope.lastDateUpdate;
            var modalInstance = $uibModal.open({
                templateUrl: 'updateResponseFromServer.html',
                controller: 'ModalInstanceCtrl2',
                controllerAs: '$ctrl'
            });

            modalInstance.result.then(function (selectedItem) {
            }, function () {
                console.log('Anulowano');
            });

        }, function errorCallback(response) {
            $rootScope.titleModal = 'Błąd aktualizacji';
            $rootScope.responseModalBody = 'Nie udało się zaktualizować bazy danych.Sprawdź połączenie z ' +
                'internetem lub skontaktuj się z administratorem.';
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