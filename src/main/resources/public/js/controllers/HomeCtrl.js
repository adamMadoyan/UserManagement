'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('home',
    ['$rootScope', '$scope', 'UserService', '$state', 'Config', 'storage',
        function ($rootScope, $scope, UserService, $state, Config, storage) {
            console.info('home controller started');

            if (storage.get(Config.USER_KEY) == undefined) {
                UserService.getUserByEmailPassword($rootScope.data.username, $rootScope.data.password, function (response) {
                    if (response == 'error') {
                        console.error(response);
                    } else {
                        $scope.user = response;
                        storage.put(Config.USER_KEY, response);
                    }
                });
            } else {
                $scope.user = storage.get(Config.USER_KEY);
            }

        }
    ]
);