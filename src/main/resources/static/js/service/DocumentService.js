/**
 * Created by Promar on 28.10.2016.
 */

app.service('documentWZ', function ($rootScope, $http, ngDialog, HOST) {

    var self = this;
    $rootScope.responseFromServer = '';
    $rootScope.showInfo = false;
    $rootScope.notFindByTrader = false;
    $rootScope.notFindByNumberWZ = false;
    $rootScope.notFindByClienName = false;
    $rootScope.notFindByClientNumber = false;
    $rootScope.notFindByNameTeam = false;

    this.addWZ = function (numberWZ, subProcess, client, traderName,
                           date) {

        console.log('w service'+ date);
        $http({
            method: 'POST',
            url: HOST + '/saveDocument',
            data: {
                "numberWZ": numberWZ,
                "subProcess": subProcess,
                "client": client,
                "traderName": traderName,
                "date": date
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.successDocument = data.Success;
                $rootScope.errorDocument = data.Error;
                $rootScope.responseFromServer = '';

                if ($rootScope.errorDocument == 'ExistsDocument') {
                    $rootScope.successDocument = '';
                    $rootScope.errorDocument = '';
                    ngDialog.open({
                        template: 'errorAdd',
                        controller: 'DocumentOperation',
                        className: 'ngdialog-theme-default'
                    });
                }
                else {
                    $rootScope.responseFromServer = "Dodałeś dokument WZ do bazy danych.";
                    ngDialog.open({
                        template: 'addDocument',
                        controller: 'DocumentOperation',
                        className: 'ngdialog-theme-default'
                    });
                }


            }).error(function (data) {
            $rootScope.responseFromServer = data.message;
            ngDialog.open({
                template: 'errorAddDocument',
                controller: 'DocumentOperation',
                className: 'ngdialog-theme-default'
            });
        });
    };

    this.findDocumentByNumberWZ = function (numberWZ, subPro) {
        $rootScope.showInfo = false;
        $rootScope.documents = [];
        $http({
            method: 'POST',
            url: HOST + '/findByNumber',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            $rootScope.notFindByTrader = false;
            $rootScope.notFindByClienName = false;
            $rootScope.notFindByClientNumber = false;
            $rootScope.notFindByNameTeam = false;


            if (data.length == 0) {
                $rootScope.notFindByNumberWZ = true;
            } else {
                $rootScope.documents.push(data);
                $rootScope.notFindByNumberWZ = false;
            }


        }).error(function (data) {
            $rootScope.notFindByNumberWZ = true;

        });
    };

    this.findByClientName = function (client) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: HOST + '/findByClient',
            data: {
                "abbreviationName":client
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;
            $rootScope.notFindByTrader = false;
            $rootScope.notFindByNumberWZ = false;
            $rootScope.notFindByClientNumber = false;
            $rootScope.notFindByNameTeam = false;
            if (data.length == 0) {
                $rootScope.notFindByClienName = true;
            } else {
                $rootScope.notFindByClienName = false;
            }

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    };

    this.findByClientNr = function (clientNumber) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: HOST + '/findByClientNumber',
            data: {

                "findClientNumber": clientNumber
            },

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;
            $rootScope.notFindByTrader = false;
            $rootScope.notFindByNumberWZ = false;
            $rootScope.notFindByClienName = false;
            $rootScope.notFindByNameTeam = false;

            if (data.length == 0) {
                $rootScope.notFindByClientNumber = true;
            } else {
                $rootScope.notFindByClientNumber = false;
            }

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    };

    this.findByTrader = function (traderName) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: HOST + '/findByTraderName',
            data: traderName,

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;
            $rootScope.notFindByNumberWZ = false;
            $rootScope.notFindByClienName = false;
            $rootScope.notFindByClientNumber = false;
            $rootScope.notFindByNameTeam = false;


            if ($rootScope.documents.length == 0) {
                $rootScope.notFindByTrader = true;
            } else {
                $rootScope.notFindByTrader = false;
            }

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    };

    this.findByNameTeam = function (nameTeam) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: HOST + '/find_nameteam',
            data: nameTeam,
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;
            $rootScope.notFindByTrader = false;
            $rootScope.notFindByNumberWZ = false;
            $rootScope.notFindByClienName = false;
            $rootScope.notFindByClientNumber = false;

            if (data.length == 0) {
                $rootScope.notFindByNameTeam = true;
            } else {
                $rootScope.notFindByNameTeam = false;
            }

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    };


    this.deleteDocument = function (numberWZ, subProcess) {
        $http({
            method: 'DELETE',
            url: HOST + '/deleteDocument',
            data: {
                "numberWZ": numberWZ,
                "subPro": subProcess
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = [];
            ngDialog.open({
                template: 'deleteCorrect',
                controller: 'AdminController',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    };

    this.correctWZ = function (numberWZ, subPro) {
        $rootScope.documents = [];
        $http({
            method: 'PATCH',
            url: HOST + '/by_correct',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            ngDialog.open({
                template: 'SuccessCorrect',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });


        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });
        });
    };

    this.correctWZAll = function () {
        $rootScope.documents = [];

        $http({
            method: 'GET',
            url: HOST + '/find_correct',

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });

    };


});