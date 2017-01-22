/**
 * Created by Promar on 03.11.2016.
 */

app.service('UserAccountService', function ($rootScope, $route, $http, $uibModal, HOST) {


        $rootScope.reloadRoute = function () {
            $route.reload();
        };

        this.doneActiveAccount = function (username) {

            $http({
                method: 'PATCH',
                url: HOST + '/myAccount/make_active_account',
                data: {
                    "username": username
                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {
                $rootScope.titleModal = 'Aktywacja konta: ';
                $rootScope.responseModalBody = 'Konto: '+ username +' zostało aktywowane. ';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $rootScope.reloadRoute();

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd atkywacji';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
            });
        };


        this.doneBlockAccount = function (username) {

            $http({
                method: 'PATCH',
                url: HOST + '/myAccount/block_account',
                data: {
                    "username": username
                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {
                $rootScope.titleModal = 'Blokada konta: ' + username;
                $rootScope.responseModalBody = 'Konto zostało zablokowane. Użytkownik nie będzie mógł korzystać z WzB24 ' +
                    'do momentu ponownego aktywowania konta.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $rootScope.reloadRoute();

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd blokowania';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });

            });
        };


        this.doneDeleteAccount = function (username) {

            $http({
                method: 'DELETE',
                url: HOST + '/myAccount//remove',
                data: {
                    "username": username
                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {
                $rootScope.titleModal = 'Usunięcie konta: ' + username;
                $rootScope.responseModalBody = 'Konto zostało usunięte trwale z aplikacji.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $rootScope.reloadRoute();

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd usuwania';
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return username;
                        }
                    }
                });
            });
        };
}
);