/**
 * Created by Promar on 05.11.2016.
 */


app.controller('LoginCtrl', ['$rootScope', '$http', '$location', '$route', '$scope','$timeout','AuthenticatedService', '$window',
    function ($rootScope, $http, $location, $route, $scope,$timeout, AuthenticatedService, $window) {

        var self = this;
        this.credentials = {};
        $rootScope.loginError = false;
        $scope.showInfo = false;
        $scope.load = true;

        $timeout(function () {
            $scope.showInfo = true;
            $scope.load = false;
        }, 1500);

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticated = function (credentials, callback) {
            AuthenticatedService.authenticatedUser(credentials, callback);
        };


        self.login = function () {

            authenticated(self.credentials, function (authenticated) {
                if (authenticated) {
                    $location.path('/home');
                    self.error = false;
                    $rootScope.authenticated = true;

                } else {
                    $scope.errorForm = "Błędne dane!";
                    $location.path("/perform_login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function () {


            $http.post('/logout', {})

                .success(function () {
                $location.path('/login');
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;
                    $rootScope._username = '';


            }).error(function (data) {
                $location.path('/login');
                $rootScope.authenticated = false;
                $rootScope.userRoles = false;
                $rootScope.userInfo = false;
                $rootScope._username = '';
            });
        }

    }])
    .config(function ($mdThemingProvider) {


        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('yellow')
            .dark();

    });
