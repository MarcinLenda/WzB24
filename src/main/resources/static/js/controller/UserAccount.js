/**
 * Created by Promar on 10.11.2016.
 */

app.controller('UserAccount', function ($scope, $http, $rootScope, $location, $timeout, DocumentWzService, $uibModal, MainService) {

    $scope.username = $rootScope._username;
    $scope.infoUsers= {};
    $scope.showInfo = false;
    $scope.load = true;

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 1000);


    MainService.userInfo()
        .then(function successCallback(response) {
            $scope.infoUsers = response.data;
            $rootScope.nameUser = $scope.infoUsers.name;

            DocumentWzService.findByTrader($scope.infoUsers.surname)
                .then(function successCallback(response) {
                    $scope.documents = response.data;
                }, function errorCallback(response) {
                    console.log('Error: user all document');
                });
        }, function errorCallback(response) {
            console.log('Error: user info');
        });


    $scope.changePassword = function () {

        if ($scope.password.new == $scope.password.newConfirm) {

            $http({
                method: 'POST',
                url:'/myAccount/change_password',
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