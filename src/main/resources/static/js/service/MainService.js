/**
 * Created by Promar on 08.02.2017.
 */
app.service('MainService', function ($http) {

    var self = this;

    self.howManyDocuments = function () {
        return $http({
            method: 'GET',
            url:'/howManyDocument',
            headers: {'Content-type': 'application/json'}
        });
    };


    self.howManyTraders = function () {
        return $http({
            method: 'GET',
            url:'/howManyTraders',
            headers: {'Content-type': 'application/json'}
        })
    };


    self.howManyClients = function () {
        return $http({
            method: 'GET',
            url:'/howManyClient',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.userInfo = function () {
        return   $http({
            method: 'GET',
            url: '/myAccount/user_info',
            headers: {'Content-type': 'application/json'}
        });
    };
});