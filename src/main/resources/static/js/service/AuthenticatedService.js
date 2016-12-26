/**
 * Created by Promar on 06.11.2016.
 */
app.service('AuthenticatedService', function ($rootScope, $http, ngDialog, HOST) {

    var self = this;
    $rootScope._username = '';
    $rootScope.userRoles = false;
    $rootScope.logout = false;
    $rootScope.authenticated = false;
    self.credentials = {};
    $rootScope.loginError = false;



    function serializeData(credentials) {
        return $.param({
            "username" : credentials.username,
            "password" : credentials.password
        });
    }

    (function() {

        $http({
            method: 'GET',
            url: HOST + '/success'
        }).then(function successCallback(response) {
            var data = response.data;
            if(data.name) {
                $rootScope.authenticated = true;
                $rootScope._username = data.username;

                $http({
                    method: 'GET',
                    url: HOST + '/myAccount/role'
                }).then(function successCallback(response) {
                    $rootScope.userRoles = response.data;

                }, function errorCallback(response) {
                    $rootScope.userRoles = false;

                });
            }

        }, function errorCallback(response) {
            $rootScope.authenticated = false;

        });
    })();









    this.authenticatedUser = function(credentials, callback) {

        // var headers = credentials ? {
        //     authorization : "Basic "
        //     + btoa(credentials.username + ":"
        //         + credentials.password)
        // } : {};

        var headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };

        var data = serializeData(credentials);

        $http.post(HOST + '/perform_login', data, {
            headers : headers
        }).then(function(response) {
            var data = response.data;
            if (data.name) {

                $rootScope.authenticated = true;
                $rootScope._username = data.username;
                $rootScope.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;

                $http({
                    method: 'GET',
                    url: HOST + '/myAccount/role'
                }).then(function successCallback(response) {
                    $rootScope.userRoles = response.data;
                }, function errorCallback(response) {
                    $rootScope.userRoles = false;
                });

        } else {
            self.authenticated = false;
            self.admin = false;
        }
            callback && callback(true);
        }, function(err) {
            self.authenticated = false;
            callback && callback(false);

            ngDialog.open({
                template: 'errorLogin',
                controller: 'LoginCtrl',
                className: 'ngdialog-theme-default'
            });

        });
    };
});
