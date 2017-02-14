/**
 * Created by Promar on 03.11.2016.
 */

app.service('ClientService', function ($http, HOST) {

    var self = this;


    self.allClient = function () {
        return $http({
            method: 'GET',
            url: HOST + '/client/all_client',
            headers: {'Content-type': 'application/json'},
        });
    };


    self.addClient = function (name, numberClient, nameTeam, abbreviationNameClient) {
        return $http({
            method: 'POST',
            url: HOST + '/client/save_client',
            data: {
                "name": name,
                "numberClient": numberClient,
                "nameTeam": nameTeam,
                "abbreviationName": abbreviationNameClient
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.deleteClient = function (numberClient, name) {
        return $http({
            method: 'DELETE',
            url: HOST + '/client/delete_client',
            data: {
                "name": name,
                "numberClient": numberClient
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.editClient = function (account) {
        return $http({
            method: 'POST',
            url: HOST + '/client/edit_client',
            data: account,
            headers: {'Content-type': 'application/json'}
        });
    };
});