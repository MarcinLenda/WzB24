/**
 * Created by Promar on 11.12.2016.
 */

app.controller('UploadController', function($scope, $rootScope, $route, FileUploader, HOST, $http) {

    $rootScope.lastDateUpdate = '';
    $rootScope.dateUpdate = $rootScope.lastDateUpdate;



    $scope.uploader = new FileUploader(
        {
            url: 'http://localhost:8080/upload'
        }
    );

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.update = function () {

        $http({
            method: 'GET',
            url: HOST + '/save_items',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $rootScope.lastDateUpdate = new Date();
                console.log($rootScope.lastDateUpdate);
                $rootScope.dateUpdate = $rootScope.lastDateUpdate;
                ngDialog.open({
                    template: 'updateSuccess',
                    controller: 'ShowAllDocuments',
                    className: 'ngdialog-theme-default'
                });
                }
            ).error(function (data) {
            console.log('nie dodano');
        });
    };
});