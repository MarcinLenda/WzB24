/**
 * Created by Promar on 10.09.2016.
 */
'use strict';


var app = angular.module('myApp', [
    'ngRoute', 'ngResource', 'tableSort', 'ngMaterial', 'ngMessages', 'timer', 'config', 'angucomplete-alt',
     'angularFileUpload', 'ui.bootstrap'


])
    .constant("APPLICATION_ERROR_CODES", {
        // General error codes
        DOCUMENT_NOT_FOUND: "Nie znaleziono żadnego wyniku !",
        ACCOUNT_USERNAME_ALREADY_EXISTS: "USERNAME_ALREADY_EXISTS"

    })
    .factory('httpInterceptor', [ '$q', '$rootScope', '$location', 'APPLICATION_ERROR_CODES',
        function ($q, $rootScope, $location, APPLICATION_ERROR_CODES) {
        return {
            request: function (config) {
                return config || $q.when(config)
            },
            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (rejection) {
                if (rejection.status === 401) {
                    //here I preserve login page
                    if( $location.absUrl() == 'http://wzb24.pl/#/login'){
                        $location.url('/login');

                    } else if ($location.absUrl() != 'http://wzb24.pl/#/after_register' &&
                        $location.absUrl() != 'http://wzb24.pl/#/login') {
                        $rootScope.authenticated = false;
                        $rootScope.userRoles = false;
                        $location.url('/main');
                        $rootScope.$broadcast('error');
                    }

                    return $q.reject(rejection);
                } else if (rejection.status === 403) {
                    $location.url('/access_denied');
                } else {
                    if(rejection.status >= 400) {
                        var errorCode = rejection.data.errorCode;
                        var msg = APPLICATION_ERROR_CODES[errorCode];
                        if(APPLICATION_ERROR_CODES.ACCOUNT_USERNAME_ALREADY_EXISTS == errorCode){
                            $rootScope.userAlreadyExists = true;
                            $rootScope.reponseError = true;
                        }
                        $rootScope.errorMessage = msg;
                    }
                }

                return $q.reject(rejection);
            }
        }
    }])
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'UserAccount'
            })
            .when('/main', {
                templateUrl: 'views/main.html',
                controller: 'UserAccount'
            })
            .when('/home', {
                templateUrl: 'views/home.html',
                controller: 'UserAccount'
            })
            .when('/search', {
                templateUrl: 'views/documentWZ.html',
                controller: 'DocumentWzCtrl'
            })
            .when('/addDocument', {
                templateUrl: 'views/admin/add/add_document.html',
                controller: 'DocumentWzCtrl'
            })
            .when('/admin', {
                templateUrl: 'views/admin/admin.html',
                controller: 'AdminController'
            })
            .when('/account', {
                templateUrl: 'views/account/account.html',
                controller: 'UserAccount'
            })
            .when('/show_documents', {
                templateUrl: 'views/admin/show_all/show_documents.html',
                controller: 'DocumentWzCtrl'
            })
            .when('/add_client', {
                templateUrl: 'views/admin/add/add_client.html',
                controller: 'ClientOperation'
            })
            .when('/add_trader', {
                templateUrl: 'views/admin/add/add_trader.html',
                controller: 'TraderOperation'
            })
            .when('/correct_document', {
                templateUrl: 'views/admin/show_all/correct_document.html',
                controller: 'DocumentWzCtrl'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl',
                controllerAs: 'controller'
            })
            .when('/register', {
                templateUrl: 'views/register/register.html',
                controller: 'RegisterCtrl'
            })
            .when('/accept_account', {
                templateUrl: 'views/admin/account/accept_account.html',
                controller: 'MainAccountCtrl'
            })
            .when('/block_account', {
                templateUrl: 'views/admin/account/account_management.html',
                controller: 'MainAccountCtrl'
            })
            .when('/user_document', {
                templateUrl: 'views/user_account/all_document_user.html',
                controller: 'UserAccount'
            })
            .when('/change_password', {
                templateUrl: 'views/user_account/change_password.html',
                controller: 'UserAccount'
            })
            .when('/after_register', {
                templateUrl: 'views/register/after_register.html',
                controller: 'UserAccount'
            })
            .when('/trader', {
                templateUrl: 'views/admin/add/trader.html',
                controller: 'UserAccount'
            })
            .when('/all_traders', {
                templateUrl: 'views/admin/show_all/all_traders.html',
                controller: 'TraderOperation'
            })
            .when('/client', {
                templateUrl: 'views/admin/add/client.html',
                controller: 'ClientOperation'
            })
            .when('/all_client', {
                templateUrl: 'views/admin/show_all/all_client.html',
                controller: 'ClientOperation'
            })
            .when('/history', {
                templateUrl: 'views/admin/history/history_menu.html',
                controller: 'HistoryCtrl'
            })
            .when('/all_delete_document', {
                templateUrl: 'views/admin/history/all_delete_document.html',
                controller: 'HistoryCtrl'
            })
            .when('/all_corrects_document', {
                templateUrl: 'views/admin/history/all_corrects_document.html',
                controller: 'HistoryCtrl'
            })
            .when('/all_logged_user', {
                templateUrl: 'views/admin/history/all_logged_in_user.html',
                controller: 'HistoryCtrl'
            })
            .when('/items', {
                templateUrl: 'views/items/items_menu.html',
                controller: ''
            }).when('/all_items', {
                templateUrl: 'views/items/items.html',
                controller: 'ItemsCtrl'
            })
            .when('/access_denied', {
                templateUrl: 'views/access_denied.html',
                controller: 'ItemsCtrl'
            }).when('/update_items', {
                templateUrl: 'views/items/update_items.html',
                controller: 'UploadController'
            })
            .when('/all_rf', {
                templateUrl: 'views/user_account/all_rf_user.html',
                controller: 'ItemsCtrl'
            })
            .when('/an', {
                templateUrl: 'views/an/an_menu.html',
                controller: 'OfferAnCtrl'
            })
            .when('/add_an', {
                templateUrl: 'views/an/add/add_an.html',
                controller: 'OfferAnCtrl'
            })
            .when('/waiting_an', {
                templateUrl: 'views/an/waiting_room/waiting_an.html',
                controller: 'OfferAnCtrl'
            })
            .when('/confirm_an', {
                templateUrl: 'views/an/confirm_offer/confirm_an.html',
                controller: 'OfferAnCtrl'
            })

            .otherwise({redirectTo: '/'});

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('httpInterceptor');


    });
