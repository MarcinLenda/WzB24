/**
 * Created by Promar on 03.11.2016.
 */

app.service('ClientService', function ($rootScope, $http, ngDialog, HOST) {

    $rootScope.responseFromServer = '';

    this.addClient = function (name, numberClient, nameTeam, abbreviationNameClient) {

        $http({
            method: 'POST',
            url: HOST + '/save_client',
            data: {
                "name": name,
                "numberClient": numberClient,
                "nameTeam": nameTeam,
                "abbreviationName": abbreviationNameClient
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.success = data.Success;
                $rootScope.error = data.Error;
                $rootScope.responseList = data;


                    angular.forEach($rootScope.responseList, function (value, key) {

                      $rootScope.name = value.name;

                    });

                if ($rootScope.error == 'ExistsClient') {
                    $rootScope.success = '';
                    $rootScope.error =  '';
                    ngDialog.open({
                        template: 'errorClient',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });

                } else if ($rootScope.error == 'ExistsClientNr') {
                    $rootScope.success = '';
                    $rootScope.error =  '';
                    ngDialog.open({
                        template: 'errorClientNumber',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });
                } else if ($rootScope.error == 'NumberLength'){
                    $rootScope.success = '';
                    $rootScope.error =  '';
                    ngDialog.open({
                        template: 'errorClientNumberLength',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });
                } else if($rootScope.error == 'ExistsAbbreviation'){
                    $rootScope.success = '';
                    $rootScope.error =  '';
                    ngDialog.open({
                        template: 'errorExistsAbbreviation',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });
                } else {
                    $rootScope.success = '';
                    $rootScope.error =  '';
                    $rootScope.responseFromServer = 'Dodałeś pomyślnie klienta o naziwe: ' + $rootScope.name;
                    ngDialog.open({
                        template: 'addClient',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });
                }


            }).error(function (data) {

            ngDialog.open({
                template: 'errorAddClient',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });
    };

    this.deleteClient = function (name, numberClient) {

        $http({
            method: 'DELETE',
            url: HOST + '/delete_client',
            data:
            {
                "name":name,
                "number": numberClient
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                    ngDialog.open({
                        template: 'DeleteClientInfo',
                        controller: 'ClientOperation',
                        className: 'ngdialog-theme-default'
                    });
                }
            ).error(function (data) {

            ngDialog.open({
                template: 'errorAddTrader',
                controller: 'ClientOperation',
                className: 'ngdialog-theme-default'
            });

        });

    };
});