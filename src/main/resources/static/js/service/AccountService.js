app.service('AccountService', function ($http, HOST) {

    var self = this;

    self.updateAccount = function (account) {
        return $http({
            method: 'POST',
            url: HOST + '/myAccount/edit_date',
            data: account,
            headers: {'Content-type': 'application/json'}
        });
    };

    self.resetPassword = function (account) {
        return $http({
            method: 'POST',
            url: HOST + '/myAccount/reset_password',
            data: account,
            headers: {'Content-type': 'application/json'}
        });
    };

    self.updateRole = function (account) {
        return $http({
            method: 'POST',
            url: HOST + '/myAccount/role',
            data: {
                'username': account.username,
                'roleLevel' : account.role
            },
            headers: {'Content-type': 'application/json'}
        });
    };

    self.showAllActiveUser = function () {
        return $http({
            method: 'GET',
            url: HOST + '/myAccount/active_account',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.showAllNotActiveUser = function () {
       return $http({
            method: 'GET',
            url: HOST + '/myAccount/find_notactive_account',
            headers: {'Content-type': 'application/json'}
        });
    };

    self.activationUserAccount = function (username) {

      return  $http({
            method: 'PATCH',
            url: HOST + '/myAccount/make_active_account',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
      });
    };

    self.blockedUserAccount = function (username) {
        return $http({
            method: 'PATCH',
            url: HOST + '/myAccount/block_account',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        });
    };

    self.deleteUserAccount = function (username) {
        return $http({
            method: 'DELETE',
            url: HOST + '/myAccount/remove',
            data: {
                "username": username
            },
            headers: {'Content-type': 'application/json'}
        });
    };
});

