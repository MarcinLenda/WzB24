/**
 * Created by Promar on 07.11.2016.
 */
app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', '$route',  'HOST', '$uibModal', 'AccountService',
    function ($scope, $http, $rootScope, $route, HOST, $uibModal, AccountService) {

        $scope.notActiveAccounts = [];
        $scope.activeAccounts = [];


        $rootScope.reloadRoute = function () {
            $route.reload();
        };


        AccountService.showAllActiveUser()
            .then(function successCallback(response) {
                $scope.activeAccounts = response.data;
            }, function errorCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd'
                    , 'Nie udało się pobrać listy aktywnych użytkowników. Sprawdź połączenie z internetem' +
                    'lub skontaktuj się z administratorem.',
                    {});
                modalInstance.result.then(function (entity) {
                });
            });


        AccountService.showAllNotActiveUser()
            .then(function successCallback(response) {
                $scope.notActiveAccounts = response.data;
            }, function errorCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd'
                    , 'Nie udało się pobrać listy  użytkowników. Sprawdź połączenie z internetem' +
                    'lub skontaktuj się z administratorem.',
                    {});
                modalInstance.result.then(function (entity) {
                });
            });


        $scope.editName = function (account) {
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja imienia',
                'Czy chcesz: ' + account.username + ' zmienić uprawnienia?',
                {});

            modalInstance.result.then(function (entity) {
                account.name = entity.value;
                $scope.editDataAccount(account);
            }, function () {
                console.log('Anulowano');
            });
        };


        $scope.editSurname = function (account) {
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja nazwiska'
                , '',
                {});

            modalInstance.result.then(function (entity) {
                account.surname = entity.value;
                $scope.editDataAccount(account);
            });
        };


        $scope.editNumber = function (account) {
            var modalInstance = $scope.openModal('updateDataAccount.html',
                'Edycja numeru pracownika'
                , '',
                {});

            modalInstance.result.then(function (entity) {
                account.numberUser = entity.value;
                $scope.editDataAccount(account);
            });
        };


        $scope.editDataAccount = function (entity) {
            AccountService.updateAccount(entity)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Sukces'
                        , 'Wartość pola użytkownika "' + entity.username + '" została zautkualizowana pomyślnie.',
                        {});

                    modalInstance.result.then(function (entity) {
                    });
                    $scope.reloadRoute();

                }, function errorCallback(response) {

                    var error = '';
                    if (angular.equals(response.data.errorCode, 'NUMBER_EMPLOYEE_EXISTS')) {
                        error = 'Podany numer pracownika jest już zajęty.';
                    } else {
                        error = 'Sprawdź połączenie z internetem lub skontaktuj się administratorem.';
                    }
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd'
                        , error,
                        {});
                    modalInstance.result.then(function (entity) {
                    });
                    $scope.reloadRoute();
                });
        };


        $scope.resetPassword = function (account) {
            var modalInstance = $scope.openModal('modalQuestion.html',
                'Restart hasła'
                , 'Czy zrestartować hasło dla: ' + account.username + ' ?',
                account);

            modalInstance.result.then(function (entity) {
                $scope.resetPasswordConfirm(entity);
            });
        };


        $scope.resetPasswordConfirm = function (account) {
            AccountService.resetPassword(account)
                .then(function successCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Sukces'
                        , 'Nowe hasło zostało ustawione na takie jak login.',
                        account);

                    modalInstance.result.then(function (entity) {
                    });
                    $scope.reloadRoute();

                }, function errorCallback(response) {

                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd'
                        , 'Hasło nie zostało zrestartowane. Sprawdź połączenie z internetem' +
                        'lub skontaktuj się z administratorem.',
                        {});
                    modalInstance.result.then(function (entity) {
                    });
                    $scope.reloadRoute();
                });
        };


        $scope.roleUser = function (account) {
            var modalInstance = $scope.openModal('modalRole.html',
                'Uprawnienia',
                'Czy chcesz: ' + account.username + ' zmienić uprawnienia?',
                {});

            modalInstance.result.then(function (entity) {
                account.role = entity.role;
                $scope.callBackRoleUser(account);
            });
        };


        $scope.callBackRoleUser = function (account) {
            AccountService.updateRole(account).then(function successCallback(response) {

                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Uprawnienia',
                    'Uprawnienia dla użytkownika: ' + account.username + ' zostały zmienione.',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });

                $scope.reloadRoute();

            }, function errorCallback(response) {
                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd',
                    'Uprawnienia nie zostały zmienione. Sprawdź połączenie z internetem' +
                    'lub skontaktuj się z administratorem.',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });
            });
        };


        $scope.blockAccountUser = function (account) {

            var modalInstance = $scope.openModal('modalQuestion.html',
                'Blokowanie konta',
                'Czy zablokować konto użytkowika: ' + account.username + '?',
                account);

            modalInstance.result.then(function (modifiedAccount) {
                AccountService.blockedUserAccount(modifiedAccount.username)
                    .then(function successCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Zablokowano',
                            'Konto: ' + modifiedAccount.username + ' zostało zablokowane.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });

                        $rootScope.reloadRoute();

                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Konto: ' + +modifiedAccount.username + ' nie zostało zablokowane. ' +
                            'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });

                    });
            });
        };


        $scope.deleteAccount = function (account) {

            var modalInstance = $scope.openModal('modalQuestion.html',
                'Usuwanie konta',
                'Czy usunąć konto użytkowika: ' + account.username + '?'
                + ' Operacja ta spowoduje stałe usunięcie konta z aplikacji.',
                account);

            modalInstance.result.then(function (modifiedAccount) {
                AccountService.deleteUserAccount(modifiedAccount.username)
                    .then(function successCallback(response) {

                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            'Konto: ' +modifiedAccount.username + ' zostało usunięte z WzB24',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                        $rootScope.reloadRoute();

                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Konto: ' + modifiedAccount.username + ' nie zostało usunięte.' +
                            ' Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });

                    });
            });

        };


        $scope.activeAccount = function (account) {
            var entity = angular.copy(account);
            var modalInstance = $scope.openModal('modalQuestion.html',
                'Aktywacja konta:', 'Czy aktywować konto użytkowika: ' + account.username + '?', entity);

            modalInstance.result.then(function (selectedItem) {
                AccountService.activationUserAccount(selectedItem.username)
                    .then(function successCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Aktywacja konta',
                            'Konto: ' + selectedItem.username + ' zostało aktywowane.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
                        $rootScope.reloadRoute();

                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Konto: ' + selectedItem.username + ' nie zostało aktywowane. Sprawdź połączenie z internetem' +
                            'lub skontaktuj się z administratorem.',
                            {});

                        modalInstance.result.then(function (modifiedAccount) {
                        });
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


app.controller('ModalInstanceCtrlRole', function ($uibModalInstance, title, responseModalBody, entity) {
    var $ctrl = this;
    // $ctrl.title = title;
    // $ctrl.responseModalBody = responseModalBody;
    $ctrl.entity = entity;

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.entity);
    };
    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});