'use strict';

UserManagement.app.services.service('Authentication', ['$http', function($http) {

    this.test = function(callback) {
        $http({
            method: 'POST',
            url: 'http://localhost:9000/oauth/token',
            data: $.param({username: "key"}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).
        then(function(response) {
            console.info(response);
        }, function(response) {
            console.info('fail', response);
        });
    }


}]);