/**
 * Created by Promar on 06.11.2016.
 */



app.controller('RegisterCtrl', function ($scope, $http, $location, HOST) {


    $scope.registerResponse = {};

    $scope.error = [];
    $scope.showError = [];
    $scope.reponseError = false;
    $scope.userAlreadyExists = false;
    $scope.numberAlreadyExists = false;
    $scope.wrongPass = false;
    $scope.numberStart0 = false;

    $scope.registerUser = function () {

        if($scope.register.number.indexOf(0) == -1 && $scope.register.password == $scope.register.passwordConfirm) {

            $http({
                method: 'POST',
                url: HOST + '/myAccount/create_account',
                data: {
                    "username": $scope.register.email,
                    "password": $scope.register.password,
                    "confirmPassword": $scope.register.passwordConfirm,
                    "name": $scope.register.name,
                    "surname": $scope.register.surname,
                    "numberUser": $scope.register.number

                },
                headers: {'Content-type': 'application/json'}
            }).then(function successCallback(response) {

                $location.path("/after_register");



            }, function errorCallback(response) {
                if ('USERNAME_ALREADY_EXISTS' == response.data.errorCode) {
                    $scope.userAlreadyExists = true;
                    $scope.numberAlreadyExists = false;
                    $scope.reponseError = true;
                    $scope.numberStart0 = false;
                    $scope.wrongPass = false;
                }

                if ('NUMBER_EMPLOYEE_EXISTS' == response.data.errorCode) {
                    $scope.userAlreadyExists = false;
                    $scope.numberAlreadyExists = true;
                    $scope.reponseError = true;
                    $scope.numberStart0 = false;
                    $scope.wrongPass = false;
                }

            });
        }else if($scope.register.number.indexOf(0) == 0){
            $scope.wrongPass = false;
            $scope.numberStart0 = true;
        }else{
            $scope.wrongPass = true;
            $scope.numberStart0 = false;
        }
    }
});
