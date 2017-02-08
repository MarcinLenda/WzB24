/**
 * Created by Promar on 08.02.2017.
 */
app.service('UploadService', function ($http) {

    var self = this;

    self.update = function () {
        console.log('ctrl service');
        return $http({
            method: 'GET',
            url:'/rf/save_items',
            headers: {'Content-type': 'application/json'}
        });
    };


});