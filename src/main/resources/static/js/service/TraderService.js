/**
 * Created by Promar on 03.11.2016.
 */

app.service('TraderService', function ($rootScope, $http, ngDialog, HOST) {

    this.deleteTrader = function (surname, numberTrader) {

        $http({
            method: 'DELETE',
            url: HOST + '/delete_trader',
            data: {
                "surname": surname,
                "numberTrader": numberTrader

            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                    ngDialog.open({
                        template: 'DeleteTraderInfo',
                        controller: 'TraderOperation',
                        className: 'ngdialog-theme-default'
                    });
            }
            ).error(function (data) {

            ngDialog.open({
                template: 'errorAddTrader',
                controller: 'TraderOperation',
                className: 'ngdialog-theme-default'
            });

        });


    };


    this.addTrader = function (name, surname, nameTeam, numberTrader) {

        $http({
            method: 'POST',
            url: HOST + '/save_trader',
            data: {
                "name": name,
                "surname": surname,
                "nameTeam": nameTeam,
                "numberTrader": numberTrader
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.success = data.Success;
                $rootScope.error = data.Error;
                $rootScope.responseList = data;

                    angular.forEach($rootScope.responseList, function (value, key) {
                        $rootScope.name = value.name;
                        $rootScope.surname = value.surname;

                    });

                    if ($rootScope.error == 'ExistsTrader') {
                        $rootScope.success = '';
                        $rootScope.error = '';
                        ngDialog.open({
                            template: 'errorTrader',
                            controller: 'ClientOperation',
                            className: 'ngdialog-theme-default'
                        });
                    } else {
                        $rootScope.success = '';
                        $rootScope.error = '';
                        $rootScope.responseFromServer = 'Dodałeś pomyślnie Handlowca: ' + $rootScope.name +' '+
                            $rootScope.surname ;
                        ngDialog.open({
                            template: 'addTrader',
                            controller: 'TraderOperation',
                            className: 'ngdialog-theme-default'
                        });
                    }
                }
            ).error(function (data) {

            ngDialog.open({
                template: 'errorAddTrader',
                controller: 'TraderOperation',
                className: 'ngdialog-theme-default'
            });

        });
    }
    ;

})
;
