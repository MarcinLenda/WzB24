app.service('AdminService', function ($http, HOST) {

    var self = this;

    self.findCorrect = function () {
        return $http({
            method: 'GET',
            url: HOST + '/find_correct',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.findNotActiveAccount = function () {
        return $http({
            method: 'GET',
            url: HOST + '/myAccount/find_notactive_account',
            headers: {'Content-type': 'application/json'}
        });
    };

});