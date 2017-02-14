/**
 * Created by Promar on 03.11.2016.
 */

app.service('TraderService', function ($rootScope, $http, $uibModal, HOST) {

    var self = this;

    self.allTraders = function () {
        return $http({
            method: 'GET',
            url: HOST + '/all_trader',
            headers: {'Content-type': 'application/json'},
        });
    };

    self.addTrader = function (name, surname, nameTeam, numberTrader) {
        return $http({
            method: 'POST',
            url: HOST + '/save_trader',
            data: {
                "name": name,
                "surname": surname,
                "nameTeam": nameTeam,
                "numberTrader": numberTrader
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.deleteTrader = function (numberTrader, surname) {
        return $http({
            method: 'DELETE',
            url: HOST + '/delete_trader',
            data: {
                "surname": surname,
                "numberTrader": numberTrader
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.editTraderData = function (trader) {
        return  $http({
            method: 'POST',
            url: HOST + '/edit_trader',
            data: trader,
            headers: {'Content-type': 'application/json'}
        });
    };

});
