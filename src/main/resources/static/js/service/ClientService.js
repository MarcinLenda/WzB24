/**
 * Created by Promar on 03.11.2016.
 */

app.service('ClientService', function ($rootScope, $http, $route, $uibModal, HOST) {



    $rootScope.reloadRoute = function () {
        $route.reload();
    };

    this.addClient = function (name, numberClient, nameTeam, abbreviationNameClient) {

        $http({
        method: 'POST',
        url: HOST + '/save_client',
        data: {
            "name": name,
            "numberClient": numberClient,
            "nameTeam": nameTeam,
            "abbreviationName": abbreviationNameClient
        },
        headers: {'Content-type': 'application/json'}
    })
        .then(function successCallback(response) {
                $rootScope.titleModal = 'Dodano klienta ';
                $rootScope.responseModalBody = 'Klient: '+ name +' został dodany do bazy danych. ';

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

                $rootScope.titleModal = 'Błąd dodawania';
                if(angular.equals(response.data.errorCode, 'CLIENT_ALREADY_EXISTS')) {
                    $rootScope.responseModalBody = 'Klient "'+name+'" istnieje. Podany skrót lub numer klienta istnieje już w bazie danych.';
                }else{
                    $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub spróbuj później.';
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

    this.deleteClient = function (numberClient ,name) {

        $http({
            method: 'DELETE',
            url: HOST + '/delete_client',
            data:
            {
                "name":name,
                "numberClient": numberClient
            },
            headers: {'Content-type': 'application/json'}
        })
            .then(function successCallback(response) {
                $rootScope.titleModal = 'Usunięto klienta ';
                $rootScope.responseModalBody = 'Klient: "'+ name +'" został usnięty z bazy danych. ';

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

                if(angular.equals(response.data.errorCode, 'CLIENT_HAS_DOCUMENT')) {
                        $rootScope.titleModal = 'Błąd usuwania ';
                        $rootScope.responseModalBody = 'Do podanego klienta istnieją przypisane dokumentu WZ w bazie danych.' +
                            ' Usunięcie klienta jest niemożliwe. ';
                }else{
                    $rootScope.titleModal = 'Błąd usuwania ';
                    $rootScope.responseModalBody = 'Klient nie został usunięty. Sprawdź połączenie z internetem lub' +
                        'skontaktuj się administratorem.';
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
                $rootScope.reloadRoute();

            });
    };
});