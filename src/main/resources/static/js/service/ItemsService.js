app.service('ItemsService', function ($rootScope, $http, HOST) {

    var self = this;


    self.statusItem = function (id) {
        return  $http({
            method: 'POST',
            url: HOST + '/rf/item_change_status',
            data: {
                "id": id
            },
            headers: {'Content-type': 'application/json'}
        });
    };

    self.findAllItems = function () {
        return $http({
            method: 'GET',
            url: HOST + '/rf/findAll_items',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.myRf = function () {
        return $http({
            method: 'GET',
            url: HOST + '/rf/findItemBy_nameTrader',
            headers: {'Content-type': 'application/json'}
        });
    };


    self.allItems = function () {
        return  $http({
            method: 'GET',
            url: HOST + '/rf/findAll_items',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.updateInfoItem = function (item) {
        return $http({
            method: 'POST',
            url: HOST + '/rf/update_items',
            data: {
                "id": item.id
            }, headers: {'Content-type': 'application/json'}
        });
    };

});
