/**
 * Created by Promar on 03.12.2016.
 */

app.controller('Statistics', ['$scope', '$http', '$timeout', '$rootScope',
    'TraderService','HOST','ngDialog',
    function ($scope, $http, $timeout, $rootScope, TraderService,HOST) {

    $rootScope.text = 'Rezerwacja fizyczne ';

    $scope.test4 = function () {

    var file = $scope.param.file;
    console.log(file);

    };


    $scope.statistics = '';
    $scope.last30Days = '';
    $scope.last60Days = '';
    $scope.last90Days = '';
    $scope.last180Days = '';
    $scope.lastYear = '';
    $scope.nameTeam = '';
    $scope.allSum = '1';
    $scope.allPieces = '1';


    $scope.sta = function () {
        $rootScope.statisticsText = 'Statystyki toku STA';
        $scope.nameTeam = 'STA';
        $scope.statistics = '';
        $scope.last30Days = '';
        $scope.last60Days = '';
        $scope.last90Days = '';
        $scope.last180Days = '';
        $scope.lastYear = '';
    };

    $scope.stb = function () {
        $rootScope.statisticsText = 'Statystyki toku STB';
        $scope.nameTeam = 'STB';
    };

    $scope.stc = function () {
        $rootScope.statisticsText = 'Statystyki toku STC';
        $scope.nameTeam = 'STC';

    };

    $scope.ste = function () {
        $rootScope.statisticsText = 'Statystyki toku STE';
        $scope.nameTeam = 'STE';
    };


    $scope.getStat = function () {
        $scope.chart = null;
        console.log("Wchodze do getStat");
        $http({
            method: 'POST',
            url: HOST + '/statistics',
            data:{
                "nameTeam": $scope.nameTeam
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $scope.statistics = data;
            console.log('odpowiada');

            $rootScope.last30Days = $scope.statistics.last30Days;
            $rootScope.last60Days = $scope.statistics.last60Days;
            $rootScope.last90Days = $scope.statistics.last90Days;
            $rootScope.last180Days = $scope.statistics.last180Days;
            $rootScope.lastYear = $scope.statistics.lastYear;
            $rootScope.allSum = $scope.statistics.allSum;
            $rootScope.allPieces = $scope.statistics.allPieces;
            console.log("test"  + $rootScope.allSum );


        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });
    };

        $scope.test = $rootScope.allSum;
        $scope.test2 = $rootScope.allPieces;

        $scope.test3 = $rootScope.lastYear;

        $scope.myDataSource = {
            chart: {
                caption: $scope.statistics.last30Days,
                subCaption: "",
                numberPrefix: "zł.",
                theme: "ocean"
            },
            data:[{
                label: "OSTATNIE 30 DNI",
                value: $rootScope.last30Days
            },
                {
                    label: "OSTATNIE 2 msc",
                    value: $rootScope.last60Days
                },
                {
                    label: "OSTATNIE 3 msc",
                    value: $rootScope.last90Days
                },
                {
                    label: "OSTATNIE 6 msc",
                    value: $rootScope.last180Days
                },
                {
                    label: "OSTATNI ROK",
                    value: $scope.test3
                }]
        };

        $scope.myData = {
            chart: {
                caption: "Ostatni rok",
                subcaption: "Wartości RF ",
                startingangle: "120",
                showlabels: "0",
                showlegend: "1",
                enablemultislicing: "0",
                slicingdistance: "15",
                showpercentvalues: "1",
                showpercentintooltip: "0",
                plottooltext: "Age group : $label Total visit : $datavalue",
                theme: "fint"
            },
            data: [
                {
                    label: "STA",
                    value: "1250400"
                },
                {
                    label: "STB",
                    value: "1463300"
                },
                {
                    label: "STC",
                    value: "1050700"
                },
                {
                    label: "STE",
                    value: "491000"
                }
            ]
        };

    $scope.updateMyChartData = function () {

        $scope.myDataSource.data[0].value = $rootScope.last30Days;
        $scope.myDataSource.data[1].value = $rootScope.last60Days;
        $scope.myDataSource.data[2].value = $rootScope.last90Days;
        $scope.myDataSource.data[3].value = $rootScope.last180Days;
        $scope.myDataSource.data[4].value = $rootScope.lastYear;
    };

        app.directive('file', function(){
            return {
                scope: {
                    file: '='
                },
                link: function(scope, el, attrs){
                    el.bind('change', function(event){
                        var files = event.target.files;
                        var file = files[0];
                        scope.file = file ? file.name : undefined;
                        scope.$apply();
                    });
                }
            };
        });

}]);


app.directive('file', function(){
    return {
        scope: {
            file: '='
        },
        link: function(scope, el, attrs){
            el.bind('change', function(event){
                var files = event.target.files;
                var file = files[0];
                scope.file = file ? file.name : undefined;
                scope.$apply();
            });
        }
    };
});