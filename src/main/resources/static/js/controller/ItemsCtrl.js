/**
 * Created by Promar on 26.11.2016.
 */
app.controller('ItemsCtrl', ['$scope', '$rootScope', '$http', '$route', '$timeout', '$uibModal', 'ItemsService',
    function ($scope, $rootScope, $http, $route, $timeout, $uibModal, ItemsService) {



        $scope.reloadRoute = function () {
            $route.reload();
        };

        $scope.load = true;

        $timeout(function () {
            $scope.showInfo = true;
            $scope.load = false;
        }, 4500);


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


        $scope.rfDetails = function (item) {
            var modalInstance = $scope.openModal('modalRF.html',
                'Edycja pozycji',
                '',
                item);

            modalInstance.result.then(function (item) {
                ItemsService.statusItem(item.id)
                    .then(function successCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            '"' + item.contentItem + '" został dodany do towarów zalegających.',
                            {});


                        $scope.reloadRoute();
                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            '"' + item.contentItem + '" nie został dodany do towarów zalegających.' +
                            'Sprawdź połączenie z internetem lub skontaktuj się z administratorem.',
                            {});

                    });
            });
        };

        $scope.rfDetailsInfo = function (item) {
            var modalInstance = $scope.openModal('modalRFInfo.html',
                'Info pozycja',
                '',
                item);

            modalInstance.result.then(function (item) {
                ItemsService.updateInfoItem(item)
                    .then(function successCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Sukces',
                            'Pozycja: ' + item.contentItem + ' została odpisana.',
                            item);
                        $scope.reloadRoute();

                    }, function errorCallback(response) {
                        var modalInstance = $scope.openModal('updateResponseFromServer.html',
                            'Błąd',
                            'Pozycja: ' + item.contentItem + ' nie została odpisana.',
                            item);
                        $scope.reloadRoute();
                    });

                $scope.reloadRoute();
            }, function errorCallback(response) {

            });
        };


        $scope.openModal = function (template, title, responseModalBody, entity) {
            $rootScope.responseModalBody = responseModalBody;
            $rootScope.titleModal = title;
            $rootScope.itemTrader = entity;

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

