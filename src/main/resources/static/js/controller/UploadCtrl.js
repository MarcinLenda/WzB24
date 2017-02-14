/**
 * Created by Promar on 11.12.2016.
 */

app.controller('UploadController', function ($scope, $rootScope, $route, FileUploader, HOST, $http, $uibModal, UploadService) {


    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.uploader = new FileUploader(
        {
            url: 'http://wzb24.pl/upload'
        }
    );


    $scope.update = function () {
        UploadService.update()
       .then(function successCallback(response) {


           var modalInstance = $scope.openModal('updateResponseFromServer.html',
               'Sukces',
               'Baza danych RF zostałą zaktualizowana.',
               {});

           modalInstance.result.then(function (entity) {

           });

        }, function errorCallback(response) {
           var modalInstance = $scope.openModal('updateResponseFromServer.html',
               'Błąd',
               'Baza danych RF nie zostałą zaktualizowana. Sprawdź połączenie z internetem ' +
               'lub skontaktuj się z administratorem.',
               {});

           modalInstance.result.then(function (entity) {

           });
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