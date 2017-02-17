/**
 * Created by Promar on 09.02.2017.
 */
app.controller('OfferAnCtrl', ['$scope','$rootScope', 'OfferAnService','$uibModal', function ($scope, $rootScope, OfferAnService,$uibModal) {


    $scope.createNewOffer = function (nameAN, city, adress, client, priority) {

        OfferAnService.newOffer(nameAN, city, adress, client, priority);
    };


    OfferAnService.allWaitingOfferAn()
        .then(function successCallback(response) {
            $scope.waitingOffer = response.data;

        }, function errorCallback(response) {
            console.log('Error: offer an = all waiting offer');
        });


    OfferAnService.allConfirmOfferAn()
        .then(function successCallback(response) {
            $scope.confirmOffer = response.data;

        }, function errorCallback(response) {
            console.log('Error: offer an = all confirm offer');
        });


    $scope.confirmAn = function (offer, trader) {
        offer.whoCreate = trader;
        OfferAnService.confirmAn(offer)
            .then(function successCallback(response) {
               console.log('sukces');
            }, function errorCallback(response) {
                console.log('Error: offer an = confirm offer');
            });
    };

    $scope.editDataOffer = function (offerIn) {

        var modalInstance = $scope.openModal('modalOfferAn.html',
            'Aktualizacja danych'
            , '',
            {});
        modalInstance.result.then(function (offer) {
            console.log(offer.numberOffer);
            offerIn.numberOffer = offer.numberOffer;
            offerIn.value = offer.valueOffer;
            offerIn.status = offer.status;
            console.log(offerIn);
            OfferAnService.changeStatusAn(offerIn)
                .then(function successCallback(response) {
                    console.log('sukces');
                }, function errorCallback(response) {
                    console.log('Error: offer an = confirm offer');
                });

        });
    };


    $scope.openModal = function (template, title, responseModalBody, entity) {
        $rootScope.responseModalBody = responseModalBody;
        $rootScope.titleModal = title;

        $rootScope.numberOffer = entity.numberOffer;
        $rootScope.valueOffer = entity.value;
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