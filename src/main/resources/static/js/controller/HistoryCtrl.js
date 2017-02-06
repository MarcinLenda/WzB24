/**
 * Created by Promar on 22.11.2016.
 */

app.controller('HistoryCtrl', ['$scope', '$http', 'HistoryService',
    function ($scope, $http, HistoryService) {

    $scope.deleteDocument = [];
    $scope.correctsDocument = [];
    $scope.loggedInUser = [];

        HistoryService.historyAllDeleteDocument()
            .then(function successCallback(response) {
                $scope.deleteDocument = response.data;
            }, function errorCallback(response) {
                console.log('Error: history delete document');
            });

         HistoryService.historyAllDeleteDocument()
             .then(function successCallback(response) {
                 $scope.correctsDocument = response.data;
             }, function errorCallback(response) {
                 console.log('Error: history correct document');
             });


       HistoryService.historyAllLoggedUser()
           .then(function successCallback(response) {
               $scope.loggedInUser = response.data;
           }, function errorCallback(response) {
               console.log('Error: history logged users');
           });
    }]);