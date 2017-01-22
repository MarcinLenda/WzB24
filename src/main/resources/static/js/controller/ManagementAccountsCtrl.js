/**
 * Created by Promar on 07.11.2016.
 */


app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', '$route', 'UserAccountService', 'HOST', '$uibModal', function ($scope, $http, $rootScope, $route, UserAccountService, HOST, $uibModal) {

    $scope.notActiveAccounts = [];
    $scope.activeAccounts = [];

    $rootScope.editName = '';
    $rootScope.edit = {};
    $scope.user = '';

    $rootScope.reloadRoute = function () {
        $route.reload();
    };


        $http({
            method: 'GET',
            url: HOST + '/myAccount/active_account',

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $scope.activeAccounts = data;

        }).error(function (data) {
            console.log('Nie udało pobrać się użytkowników.');

        });


        $http({
            method: 'GET',
            url: HOST + '/myAccount/find_notactive_account',

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $scope.notActiveAccounts = data;

        }).error(function (data) {
            console.log('Nie udało pobrać się użytkowników.');

        });


    $scope.activeAccount = function () {

        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Active',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

        $rootScope.username = $scope.editData.accounts.username;
        $rootScope.name = $scope.edit.Name;
        $rootScope.surname = $scope.editData.accounts.surname;
        $rootScope.nameTeam = $scope.editData.accounts.nameTeam;
        $rootScope.number = $scope.editData.accounts.numberUser;
        $rootScope.newUsername = $scope.editData.accounts.newUsername;

    };


    $scope.editName = function (account) {
        $rootScope.titleModal = 'Edycja imienia:';
        $rootScope.edit.update = '';

        var modalInstance = $uibModal.open({
            templateUrl: 'updateDataAccount.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                items: function () {
                    return $scope.items;
                },
                entity: function () {

                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            selectedItem.name = $rootScope.edit.update;
            $scope.editDataAccount(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editSurname = function (account) {
        $rootScope.titleModal = 'Edycja nazwiska:';
        $rootScope.edit.update = '';

        var modalInstance = $uibModal.open({
            templateUrl: 'updateDataAccount.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                items: function () {
                    return $scope.items;
                },
                entity: function () {

                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            selectedItem.surname = $rootScope.edit.update;
            $scope.editDataAccount(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.editNumber = function (account) {
        $rootScope.titleModal = 'Edycja numeru pracownika:';
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
            selectedItem.numberUser = $rootScope.edit.update;
            $scope.editDataAccount(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };



    $scope.resetPassword = function (account) {
        $rootScope.titleModal = 'Restartowanie hasła';
        $rootScope.responseModalBody = 'Czy zrestartować hasło dla: '+ account.username+' ?';

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.resetPasswordConfirm(selectedItem);
        }, function () {
            console.log('Anulowano');
        });

    };

    $scope.resetPasswordConfirm = function (account) {
        $http({
            method: 'POST',
            url: HOST + '/myAccount/reset_password',
            data: account,
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.titleModal = 'Udało się zaktualizować dane';
            $rootScope.responseModalBody = 'Wartość pola została zautkualizowana pomyślnie.';

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
            $rootScope.titleModal = 'Błąd restartowanie hasła';
            $rootScope.responseModalBody = 'Hasło nie zostało zrestartowane. Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

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

    $scope.editDataAccount = function (entity) {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/edit_date',
            data: entity,
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.titleModal = 'Edycja użytkownika';
            $rootScope.responseModalBody = 'Wartość pola klienta "'+entity.username+'" została zautkualizowana pomyślnie.';

            var modalInstance = $uibModal.open({
                templateUrl: 'updateResponseFromServer.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                resolve: {
                    entity: function () {
                        return entity;
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

            if(angular.equals(response.data.errorCode, 'NUMBER_EMPLOYEE_EXISTS')) {
                $rootScope.responseModalBody = 'Podany numer pracownika jest już zajęty.';
            }else{
                $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';
            }
            var modalInstance = $uibModal.open({
                templateUrl: 'updateResponseFromServer.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                resolve: {
                    entity: function () {
                        return entity;
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


    $scope.giveRoleAdmin = function (account) {
        $rootScope.titleModal = 'Uprawnienia';
        $rootScope.responseModalBody = 'Czy chcesz: '+ account.username +' zmienić uprawnienia?';
        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.confirmGiveRoleAdmin(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.confirmGiveRoleAdmin = function (account) {
        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_admin',
            data: account.username,

            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {

            $rootScope.titleModal = 'Uprawnienia';
            $rootScope.responseModalBody = 'Użytkownik: '+account.username + ' dostał uprawnienia ADMIN.';

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
            $rootScope.titleModal = 'Uprawnienia błąd!';
            $rootScope.responseModalBody = 'Użytkownik: '+account.username + ' nie udało się nadać praw ADMIN.' +
                'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

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
        });
    };
    $scope.giveRoleUser = function (account) {
        $rootScope.username = account.username;
        $rootScope.titleModal = 'Uprawnienia';
        $rootScope.responseModalBody = 'Czy chcesz: '+ account.username +' zmienić uprawnienia?';

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.confirmRoleUser(selectedItem);
        }, function () {
            console.log('Anulowano');
        });
    };

    $scope.confirmRoleUser = function (account) {
        console.log('daje usera');
        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_user',
            data: account.username,
            headers: {'Content-type': 'application/json'}
        }).then(function successCallback(response) {
            $rootScope.titleModal = 'Uprawnienia';
            $rootScope.responseModalBody = 'Użytkownik: '+account.username + ' dostał uprawnienia USER.'+
                'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';

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
            $rootScope.titleModal = 'Uprawnienia błąd!';
            $rootScope.responseModalBody = 'Użytkownik: '+account.username + ' nie udało się nadać praw USER.' +
                'Sprawdź połączenie z internetem lub skontaktuj się administratorem.'
            ;

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
        });
    };



    $scope.blockAccountUser = function (account) {
       $rootScope.titleModal = 'Blokada konta:';
       $rootScope.responseModalBody = 'Czy zablokować konto użytkowika: '+account.username + '?';

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });
        modalInstance.result.then(function (selectedItem) {
            UserAccountService.doneBlockAccount(selectedItem.username);

        }, function () {
            console.log('Anulowano');
        });

    };

    $scope.deleteAccount = function (account) {
        $rootScope.titleModal = 'Usuwanie konta:';
        $rootScope.responseModalBody = 'Czy usunąć konto użytkowika: '+account.username + '?'
        +' Operacja ta spowoduje stałe usunięcie konta.';

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });
        modalInstance.result.then(function (selectedItem) {
            UserAccountService.doneDeleteAccount(selectedItem.username);
        }, function () {
            console.log('Anulowano');
        });

    };

    $scope.activeThisAccount = function (account) {
        $rootScope.titleModal = 'Aktywacja konta:';
        $rootScope.responseModalBody = 'Czy aktywować konto użytkowika: '+account.username + '?';

        var modalInstance = $uibModal.open({
            templateUrl: 'modalQuestion.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            resolve: {
                entity: function () {
                    return account;
                }
            }
        });
        modalInstance.result.then(function (selectedItem) {
            UserAccountService.doneActiveAccount(selectedItem.username);
        }, function () {
            console.log('Anulowano');
        });

    };


}]);

app.controller('ModalInstanceCtrl', function ($uibModalInstance, entity, $rootScope) {
    var $ctrl = this;

    if(angular.equals(entity.role, 'ADMIN')){
        $rootScope.orUser = false;
        $rootScope.orAdmin = true;

    }else{

        $rootScope.orUser = true;
        $rootScope.orAdmin = false;
    }

    $ctrl.ok = function () {
        $uibModalInstance.close(entity);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
