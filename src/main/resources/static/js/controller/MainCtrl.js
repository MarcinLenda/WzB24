/**
 * Created by Promar on 11.10.2016.
 */

app.controller('MainController',['$scope','MainService', function ($scope, MainService) {

    $scope.howManyDocument = '0';
    $scope.howManyTraders = '0';
    $scope.howManyClient = '0';


    MainService.howManyDocuments()
        .then(function successCallback(response) {
            $scope.howManyDocument = response.data;
        }, function errorCallback(response) {
            console.log('Error: how many documents');
        });


    MainService.howManyTraders()
        .then(function successCallback(response) {
            $scope.howManyTraders = response.data;
        }, function errorCallback(response) {
            console.log('Error: how many traders');
        });


        MainService.howManyClients()
            .then(function successCallback(response) {
                $scope.howManyClient = response.data;
            }, function errorCallback(response) {
                console.log('Error: how many clients');
            });

}]);