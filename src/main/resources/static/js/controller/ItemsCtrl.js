/**
 * Created by Promar on 26.11.2016.
 */
app.controller('ItemsOperation', ['$scope', '$rootScope', '$http', '$window', '$route', '$timeout', 'ItemsService', 'HOST', 'ItemsService', function ($scope, $rootScope, $http, $window, $route, $timeout, ItemsService, HOST) {

    $scope.form = {};
    $scope.listItems = '';
    $rootScope.listClient = '';
    $scope.resultListClient = [];
    $scope.resultListTrader = [];
    $scope.load = true;
    $scope.filterNameClient = false;
    $scope.filterNameTrader = false;
    $scope.filterNameTeam = false;
    $scope.filterProvider = false;
    $scope.filterDate = false;
    $scope.filterNumberPro = false;
    $scope.filterKBN = false;
    $scope.filterBusinessSector = false;
    $rootScope.itemsLength = 0;
    $rootScope.text = new Date();

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.changeStatusItem = function () {

        $http({
            method: 'POST',
            url: HOST + '/item_change_status',
            data: {
                "id" : $scope.editData.items.id
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                ngDialog.open({
                    template: 'changeStatus',
                    controller: 'ItemsOperation',
                    className: 'ngdialog-theme-default'
                });

            }).error(function (data) {
            ngDialog.open({
                template: 'changeStatusError',
                controller: 'ItemsOperation',
                className: 'ngdialog-theme-default'
            });
        });
    };

    $scope.myRf = function () {
        ItemsService.myRf();
    };

    $scope.showNumber = function() {
        $scope.filterNumberPro = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showClient = function() {
        $scope.filterNameClient = true;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showTrader = function() {
        $scope.filterNameTrader = true;
        $scope.filterNameClient = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showTeam = function() {
        $scope.filterNameTeam = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showProvider = function() {
        $scope.filterProvider = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showDate = function() {
        $scope.filterDate = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showPro= function() {
        $scope.filterNumberPro = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showKBN= function() {
        $scope.filterKBN = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showBusiness= function() {
        $scope.filterBusinessSector = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
    };

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 1000);


    $scope.reloadRoute = function () {
        $route.reload();
    };

    $http({
        method: 'GET',
        url: HOST + '/findAll_items',
        headers: {'Content-type': 'application/json'}
    })
        .success(function (data) {
            $scope.listItems = data;

            $rootScope.itemsLength = data.length;

        }).error(function (data) {
        $rootScope.listClient = 'Nie udało się pobrać listy handlowców.'
    });

    $scope.runService = function () {
        ItemsService.findAllItems();
    };


    //******************************************************************************************************************

    $scope.editData = [];
    $scope.editItem = [];
    $scope.editOff = true;
    $scope.editOn = false;

    $scope.stopEdit = function () {
        $scope.editOff = true;
        $scope.editOn = false;
    };

    $scope.showEdit = function () {
        $scope.editOff = false;
        $scope.editOn = true;




        $scope.id = $scope.editData.items.id;

        $http({
            method: 'POST',
            url: HOST + '/findItemBy_ID',
            data:{
                "id": $scope.id
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $scope.editItem = data;


            }).error(function (data) {
            $rootScope.listClient = 'Nie udało się pobrać listy handlowców.'
        });


    };

    $scope.offEditNumber = true;
    $scope.editNumber = false;
    $scope.modelPieces = [];
    $scope.startPieces = 0;

    $scope.editItemPieces = function () {
        $scope.offEditNumber = false;
        $scope.editNumber = true;
    };

    $scope.save = function () {
        $scope.offEditNumber = true;
        $scope.editNumber = false;
        $scope.startPieces =  $scope.modelPieces.op;
    };

    $scope.return = function () {
        $scope.offEditNumber = true;
        $scope.editNumber = false;
    };

    $scope.saveOnServer = function () {

        $scope.modelPieces.op+='.000';


        if($scope.modelPieces.op == $scope.editData.items.pieces){

            $http({
                method: 'DELETE',
                url: HOST + '/delete_items',
                data: {
                    "id": $scope.editData.items.id

                },
                headers: {'Content-type': 'application/json'}
            })
                .success(function (data) {

                        $rootScope.successChangePieces = 'Odpisałeś towar z procesu: ' + $scope.editData.items.numberPro;
                        $rootScope.successChangePieces2 = 'KBN: '+ $scope.editData.items.kbn +'  Ilość: wszystkie dostępne sztuki.'
                        ngDialog.open({
                            template: 'updatePieces',
                            controller: 'ItemsOperation',
                            className: 'ngdialog-theme-default'
                        });

                    }
                ).error(function (data) {
                console.log('nie dodano');
            });


        }else{

            $http({
                method: 'POST',
                url: HOST + '/update_items',
                data: {
                    "id": $scope.editData.items.id,
                    "pieces": $scope.modelPieces.op

                },
                headers: {'Content-type': 'application/json'}
            })
                .success(function (data) {

                    $rootScope.successChangePieces = 'Odpisałeś towar z procesu: ' + $scope.editData.items.numberPro;
                    $rootScope.successChangePieces2 = 'KBN: '+ $scope.editData.items.kbn +'  Ilość: '+ $scope.modelPieces.op + ' sztuk.'
                    ngDialog.open({
                        template: 'updatePieces',
                        controller: 'ItemsOperation',
                        className: 'ngdialog-theme-default'
                    });

                    }
                ).error(function (data) {
                console.log('nie dodano');
            });
        }

    };
        $scope.number = 1;
}]);