/**
 * Created by Promar on 26.11.2016.
 */
app.controller('ItemsCtrl', ['$scope', '$rootScope', '$http', '$route', '$timeout', '$uibModal', 'ItemsService', 'HOST',
    function ($scope, $rootScope, $http, $route, $timeout, $uibModal, ItemsService, HOST) {

        $scope.form = {};
        $scope.listItems = '';
        $rootScope.listClient = '';
        $scope.resultListClient = [];
        $scope.resultListTrader = [];
        $scope.load = true;
        $rootScope.itemsLength = 0;
        $rootScope.text = new Date();

        $scope.reloadRoute = function () {
            $route.reload();
        };

        $timeout(function () {
            $scope.showInfo = true;
            $scope.load = false;
        }, 4500);

        $scope.changeStatusItem = function (item) {
           ItemsService.statusItem(item.id)
               .then(function successCallback(response) {
                   var modalInstance = $scope.openModal('updateResponseFromServer.html',
                       'Sukces',
                      '"'+ item.contentItem+'" został dodany do towarów zalegających.',
                       {});

                   modalInstance.result.then(function (modifiedAccount) {
                   });
                   $scope.myItems();
                   $scope.reloadRoute();
               }, function errorCallback(response) {
                   var modalInstance = $scope.openModal('updateResponseFromServer.html',
                       'Błąd',
                       '"'+ item.contentItem+'" nie został dodany do towarów zalegających.' +
                       'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                       {});

                   modalInstance.result.then(function (modifiedAccount) {
                   });
               });

        };


            ItemsService.myRf()
                .then(function successCallback(response) {
                    $scope.resultListRf = response.data;

                }, function errorCallback(response) {
                    var modalInstance = $scope.openModal('updateResponseFromServer.html',
                        'Błąd',
                        'Nie udało się pobrać Twojej listy rezerwacji fizycznych.' +
                        ' Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                        {});

                    modalInstance.result.then(function (modifiedAccount) {
                    });
                });




       ItemsService.findAllItems()
           .then(function successCallback(response) {
               $scope.listItems = response.data;

           }, function errorCallback(response) {
               var modalInstance = $scope.openModal('updateResponseFromServer.html',
                   'Błąd',
                   'Nie udało się pobrać  listy rezerwacji fizycznych.' +
                   ' Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                   {});

               modalInstance.result.then(function (modifiedAccount) {
               });
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
                url: HOST + '/rf/findItemBy_ID',
                data: {
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
            $scope.startPieces = $scope.modelPieces.op;
        };

        $scope.return = function () {
            $scope.offEditNumber = true;
            $scope.editNumber = false;
        };

        $scope.saveOnServer = function (item) {

            $http({
                method: 'POST',
                url: HOST + 'rf/update_items',
                data: {
                    "id": $scope.editData.items.id,
                    "pieces": $scope.modelPieces.op
                }, headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {

                $rootScope.titleModal = 'Odpisywanie towaru';
                $rootScope.responseModalBody = 'Odpisałeś pomyślnie towar ze zlecnia:' + item.numberPro + " / " +
                    item.subPro;

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl2',
                    controllerAs: '$ctrl'
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $scope.reloadRoute();

            }, function errorCallback(response) {
                $rootScope.titleModal = 'Błąd edycji';

                if (angular.equals(response.data.errorCode, 'NUMBER_ALREADY_EXISTS')) {
                    $rootScope.responseModalBody = 'Handlowiec o podanym numerzy istnieje już w bazie danych.';

                } else if (angular.equals(response.data.errorCode, 'TEAM_NOT_FOUND_TEAM')) {
                    $rootScope.responseModalBody = 'Podany TOK nie istnieje.';
                } else {
                    $rootScope.responseModalBody = 'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.';
                }

                var modalInstance = $uibModal.open({
                    templateUrl: 'updateResponseFromServer.html',
                    controller: 'ModalInstanceCtrl',
                    controllerAs: '$ctrl',
                    resolve: {
                        entity: function () {
                            return account;
                        }
                    }
                });

                modalInstance.result.then(function (selectedItem) {
                }, function () {
                    console.log('Anulowano');
                });
                $scope.reloadRoute();
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

    }]);