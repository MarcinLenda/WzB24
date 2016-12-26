/**
 * Created by Promar on 07.11.2016.
 */


app.controller('MainAccountCtrl', ['$scope', '$http', '$rootScope', '$route', 'UserAccountService', 'ngDialog', 'HOST', function ($scope, $http, $rootScope, $route, UserAccountService, ngDialog, HOST) {

    $scope.notActiveAccounts = [];
    $scope.editData = {};
    $scope.activeAccounts = [];
    $rootScope.editName = '';
    $scope.edit = {};
    $scope.user = '';


    $scope.reloadRoute = function () {
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

    };

    $scope.editName = function () {
        ngDialog.open({
            template: 'EditName',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
        $rootScope.username = $scope.editData.accounts.username;
    };

    $scope.editSurname = function () {
        ngDialog.open({
            template: 'EditSurname',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
        $rootScope.username = $scope.editData.accounts.username;
    };

    $scope.editNumber = function () {
        ngDialog.open({
            template: 'EditNumber',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
        $rootScope.username = $scope.editData.accounts.username;
    };

    $scope.editTeam = function () {
        ngDialog.open({
            template: 'EditTeam',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
        $rootScope.username = $scope.editData.accounts.username;
    };

    $scope.editMail = function () {
        ngDialog.open({
            template: 'EditEmail',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
        $rootScope.username = $scope.editData.accounts.username;
    };

    $scope.editDataAccount = function () {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/find_user',
            data:{
                    "username": $rootScope.username
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $scope.user = data;
        }).error(function (data) {
            console.log('Nie udało pobrać się użytkownika.');

        });

        var username = $rootScope.username;
        var name = '';
        var surname = '';
        var nameTeam = '';
        var number = '';
        var newUsername = '';


        if($scope.edit.Name != null) {
            name = $scope.edit.Name;
        }

        if($scope.edit.Surname != null){
            surname = $scope.edit.Surname;
        }

        if($scope.edit.Number != null){
            number = $scope.edit.Number;
        }

        if($scope.edit.Mail != null){
           username = $scope.edit.Mail;
        }

        if($scope.edit.NameTeam != null){
            nameTeam = $scope.edit.NameTeam;
        }

        if($scope.edit.Mail != null){
            newUsername = $scope.edit.Mail;
        }

        $http({
            method: 'POST',
            url: HOST + '/myAccount/edit_date',
            data:{
                "username": username,
                "newUsername": newUsername,
                "name": name,
                "surname": surname,
                "numberUser": number,
                "nameTeam": nameTeam

            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.response = data.Success;

            if($rootScope.response != '') {
                ngDialog.open({
                    template: 'response',
                    controller: 'MainAccountCtrl',
                    className: 'ngdialog-theme-default'
                });
            }
                $rootScope.response2 = data.Error;

            $route.reload();

        }).error(function (data) {
            $rootScope.response = 'Błąd z serwera.';

        });
    };


    $scope.blockAccount = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Block',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.deleteAccount = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Delete',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.deleteThisAccount = function () {
        UserAccountService.doneDeleteAccount($rootScope.username);
    };

    $scope.activeThisAccount = function () {

        UserAccountService.doneActiveAccount($scope.username);
    };

    $scope.giveRoleAdmin = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'Admin',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.giveRoleUser = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'USER',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.confirmGiveRoleAdmin = function () {

        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_admin',
            data: $rootScope.username,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                ngDialog.open({
                    template: 'SuccessAddRole',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
                });


            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });

    };

    $scope.confirmRoleUser = function () {
        $http({
            method: 'POST',
            url: HOST + '/myAccount/give_user',
            data: $rootScope.username,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                ngDialog.open({
                    template: 'SuccessAddRole',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
                });


            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });
    };

    $scope.giveRoleUser = function () {
        $rootScope.username = $scope.editData.accounts.username;

        ngDialog.open({
            template: 'User',
            controller: 'MainAccountCtrl',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.blockAccountUser = function () {

        UserAccountService.doneBlockAccount($scope.username);
    };


}]);