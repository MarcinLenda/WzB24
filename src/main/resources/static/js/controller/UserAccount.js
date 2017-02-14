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

                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Sukces',
                    'Twoje hasło zostało zmienione. Przy próbie następnego logowania użyj ' +
                    'nowego hasła.',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });
                $location.path("/account");

            }, function errorCallback(response) {

                if ('WRONG_PASSWORD' == response.data.errorCode) {
                    $scope.wrongPass = true;
                }

                var modalInstance = $scope.openModal('updateResponseFromServer.html',
                    'Błąd',
                    'Twoje hasło nie zostało zmienione. Skontaktuj się z administratorem.',
                    {});

                modalInstance.result.then(function (modifiedAccount) {
                });

            });
        } else {
            $scope.diffPass = true;
        }
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
});