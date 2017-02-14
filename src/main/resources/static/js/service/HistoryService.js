app.service('HistoryService', function ($http, HOST) {

    var self = this;

    self.historyAllDeleteDocument = function () {
        return $http({
            method: 'GET',
            url: '/history/all_delete'
        });
    };


    self.historyAllCorrectDocument = function () {
        return $http({
            method: 'GET',
            url: 'history/all_corrects'
        });
    };


    self.historyAllLoggedUser = function () {
        return  $http({
            method: 'GET',
            url: 'history/all_logged'
        });
    };
});
