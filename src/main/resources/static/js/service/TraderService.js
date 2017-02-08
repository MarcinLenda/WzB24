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


    this.deleteTrader = function (numberTrader, surname) {
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

    $scope.openModal = function (template, title, responseModalBody, entity) {
        $rootScope.responseModalBody = responseModalBody;
        $rootScope.titleModal = title;
        return $uibModal.open({
            templateUrl: template,
            controller: 'ModalInstanceCtrlRole',
            controllerAs: '$ctrl',
            resolve: {
                title: function () {
                    return title;
                },
                responseModalBody: function () {
                    return responseModalBody;
                },
                entity: function () {
                    return entity;
                }
            }
        });
    };

});
