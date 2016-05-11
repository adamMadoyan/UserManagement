'use strict';

UserManagement.app.services.service('OauthService', ['$http', 'OAuth', function ($http, OAuth) {
    var encoded = btoa("user_management:8e43ecf6-57f4-4522-8f3e-08b22f356e7d");
    var options = {
        headers: {
            "Authorization": "Basic " + encoded,
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    this.getAccessToken = function (user, callback) {
        var promise = OAuth.getAccessToken(user, options);
        console.info(promise);
        console.info(promise.status);
        console.info(promise.value);
        callback(promise);
    }


}]);