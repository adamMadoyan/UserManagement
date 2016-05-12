'use strict';

UserManagement.app.services.service('UserService',
    ['$http', 'Config',
        function ($http, Config) {

            this.create = function (user, callback) {
                $http({
                    method: 'POST',
                    url: Config.BASE_URL + '/users',
                    data: user,
                    headers: {'Content-Type': 'application/json'}
                }).
                    then(function (response) {
                        callback(response);
                    }, function (response) {
                        callback(response);
                    });
            };

            this.isEmailExist = function (email, callback) {
                $http({
                    method: 'POST',
                    url: Config.BASE_URL + '/users/exist/' + email,
                    headers: {'Content-Type': 'application/json'}
                }).
                    then(function (response) {
                        callback(response);
                    }, function (response) {
                        callback(response);
                    });
            }

        }
    ]
);