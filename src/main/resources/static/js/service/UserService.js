/**
 * Created by Promar on 03.11.2016.
 */

app.service('UserAccountService', function ($rootScope, $http, ngDialog, HOST) {



    this.doneActiveAccount = function (username) {
        $rootScope.documents = [];
        $http({
            method: 'PATCH',
            url: HOST + '/myAccount/make_active_account',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            ngDialog.open({
                template: 'successActiveAccount',
                controller: 'MainAccountCtrl',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {

        });
    };

    this.doneBlockAccount = function (username) {
        $rootScope.documents = [];
        $http({
            method: 'PATCH',
            url: HOST + '/myAccount/block_account',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            ngDialog.open({
                template: 'successBlock',
                controller: 'MainAccountCtrl',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {

        });
    };

    this.doneDeleteAccount = function (username) {
        $rootScope.documents = [];
        $http({
            method: 'DELETE',
            url: HOST + '/myAccount//remove',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            ngDialog.open({
                template: 'successDelete',
                controller: 'MainAccountCtrl',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {

        });
    };

});