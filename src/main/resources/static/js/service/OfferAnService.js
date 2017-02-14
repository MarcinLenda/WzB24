/**
 * Created by Promar on 09.02.2017.
 */
app.service('OfferAnService', function ($http) {

    var self = this;


    self.newOffer = function (nameAN, city, adress, client, priority) {
        return $http({
            method: 'POST',
            url:'/an/save_an',
            data: {
                "nameOffer": nameAN,
                "cityName": city,
                "adress": adress,
                "client": client,
                "priority":priority
            },
            headers: {'Content-type': 'application/json'}
        });
    };


    self.allWaitingOfferAn = function () {
        return $http({
            method: 'GET',
            url:'/an/all_waiting_an',
            headers: {'Content-type': 'application/json'}
        });
    };


    self.allConfirmOfferAn = function () {
        return $http({
            method: 'GET',
            url:'/an/all_confirm_an',
            headers: {'Content-type': 'application/json'}
        });
    };


    self.confirmAn = function (offer) {
        return $http({
            method: 'POST',
            url:'/an/confirm_an',
            data:offer,
            headers: {'Content-type': 'application/json'}
        });
    };

    self.changeStatusAn = function (offer) {
        return $http({
            method: 'POST',
            url:'/an/change_status_an',
            data:offer,
            headers: {'Content-type': 'application/json'}
        });
    }

});