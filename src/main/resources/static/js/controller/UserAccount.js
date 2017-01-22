/**
 * Created by Promar on 10.11.2016.
 */

app.controller('UserAccount', function ($scope, $http, $rootScope, $location, $timeout, documentWZ, $uibModal, HOST) {

    $scope.username = $rootScope._username;
    $scope.infoUsers = '';
    $rootScope.nameUser = '';
    $scope.showInfo = false;
    $scope.load = true;

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 900);


    $scope.isViewLoading = false;
    $scope.$on('$routeChangeStart', function () {
        $scope.isViewLoading = true;

    });
    $scope.$on('$routeChangeSuccess', function () {
        $scope.isViewLoading = false;
    });
    $scope.$on('$routeChangeError', function () {
        $scope.isViewLoading = false;
    });


    $http({
        method: 'GET',
        url: HOST + '/myAccount/user_info',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.infoUsers = data;
            $rootScope.nameUser = $scope.infoUsers.name;


        }).error(function (data) {

    });


    $scope.findMyDocument = function () {

        documentWZ.findByTrader($scope.infoUsers.surname);

    };

    $scope.changePassword = function () {

        if ($scope.password.new == $scope.password.newConfirm) {

            $http({
                method: 'POST',
                url: HOST + '/myAccount/change_password',
                data: {
                    "oldPassword": $scope.password.old,
                    "newPassword": $scope.password.new,
                    "confirmNewPassword": $scope.password.newConfirm,
                    "username": $scope.username


                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {

                $rootScope.titleModal = 'Hasło zmienione';
                $rootScope.responseModalBody = 'Twoje hasło zostało zmienione. Przy próbie następnego logowania użyj ' +
                    'nowego hasła.';

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });

                $location.path("/account");


            }, function errorCallback(response) {

                if ('WRONG_PASSWORD' == response.data.errorCode) {
                    $scope.wrongPass = true;
                }
                $rootScope.titleModal = 'Błąd zmiany hasła';
                $rootScope.responseModalBody = 'Twoje hasło nie zostało zmienione. Skontaktuj się z administratorem.' +
                    'nowego hasła.';

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
        } else {
            $scope.diffPass = true;
        }
    };
});