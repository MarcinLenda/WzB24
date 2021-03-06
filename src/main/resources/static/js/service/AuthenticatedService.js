/**
 * Created by Promar on 06.11.2016.
 */
app.service('AuthenticatedService', function ($rootScope, $http, HOST) {

    var self = this;
    $rootScope._username = '';
    $rootScope.logout = false;
    $rootScope.authenticated = false;
    self.credentials = {};
    $rootScope.loginError = false;



    function serializeData(credentials) {
        return $.param({
            "username": credentials.username,
            "password": credentials.password
        });
    }

    (function () {

        $http({
            method: 'GET',
            url: HOST + '/success'
        }).then(function successCallback(response) {
            var data = response.data;

            $rootScope.authenticated = true;
            $rootScope._username = data.username;

            $rootScope.roleSuperAdmin = angular.equals(data.role, 'SUPER_ADMIN');
            $rootScope.roleAdmin = angular.equals(data.role, 'ADMIN');
            $rootScope.roleModerator = angular.equals(data.role, 'MODERATOR');

            if ($rootScope.roleSuperAdmin || $rootScope.roleAdmin || $rootScope.roleModerator) {
                $rootScope.admin = true;
            }

            $rootScope.roleSuperUser = angular.equals(data.role, 'SUPER_USER');
            $rootScope.roleUser = angular.equals(data.role, 'USER');


        }, function errorCallback(response) {
            $rootScope.authenticated = false;

        });
    })();


    self.authenticatedUser = function (credentials, callback) {

        var headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };

        var data = serializeData(credentials);

        $http.post(HOST + '/perform_login', data, {
            headers: headers
        }).then(function (response) {
            var data = response.data;

            $rootScope.authenticated = true;
            $rootScope._username = data.username;

            $rootScope.roleSuperAdmin = angular.equals(data.role, 'SUPER_ADMIN');
            $rootScope.roleAdmin = angular.equals(data.role, 'ADMIN');
            $rootScope.roleModerator = angular.equals(data.role, 'MODERATOR');

            if ($rootScope.roleSuperAdmin || $rootScope.roleAdmin || $rootScope.roleModerator) {
                $rootScope.admin = true;
            }

            $rootScope.roleSuperUser = angular.equals(data.role, 'SUPER_USER');
            $rootScope.roleUser = angular.equals(data.role, 'USER');

            callback && callback(true);
        }, function (err) {
            self.authenticated = false;
            callback && callback(false);

        });
    };

    self.logOut = function () {
        return $http.post('/logout', {});
    };
});
