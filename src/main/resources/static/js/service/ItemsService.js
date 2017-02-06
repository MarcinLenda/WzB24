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
        $http({
            method: 'GET',
            url: HOST + '/rf/findAll_items',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.listItems = data;
                angular.forEach($rootScope.listItems, function (value, key) {
                    console.log(key + ': ' + value.dateUpdate);
                });

            }).error(function (data) {
            $rootScope.listClient = 'Nie udało się pobrać listy handlowców.'
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


});
