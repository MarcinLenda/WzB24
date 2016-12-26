/**
 * Created by Promar on 01.11.2016.
 */

app.controller('ShowAllDocuments', function ($scope, $http, $rootScope, $route, $location, documentWZ, ngDialog, HOST) {

    $scope.documents = [];
    $scope.editData = {};


    $scope.reloadRoute = function () {
        $route.reload();
    };

    $http({
        method: 'GET',
        url: HOST + '/showAllDocuments',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.documents = data;

    }).error(function (data) {
        console.log('Nie udało się pobrać WZ');

    });

    $scope.clickToDelete = function () {
        $rootScope.numberWZtoDelete = $scope.editData.documents.numberWZ;
        $rootScope.subProDelete = $scope.editData.documents.subProcess;

        ngDialog.open({
            template: 'showDelete',
            controller: 'ShowAllDocuments',
            className: 'ngdialog-theme-default'
        });

    };

    $scope.deleteDocument = function () {

        $http({
            method: 'DELETE',
            url: HOST + '/deleteDocument',
            data: {
                "numberWZ": $rootScope.numberWZtoDelete,
                "subPro": $rootScope.subProDelete
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = [];
            ngDialog.open({
                template: 'allDocumentDelete',
                controller: 'ShowAllDocuments',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {
            ngDialog.open({
                template: 'errorFindDocument',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    }
});