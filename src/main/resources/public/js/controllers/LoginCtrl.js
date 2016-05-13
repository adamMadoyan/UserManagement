'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('login',
    ['$rootScope', '$scope', '$state', 'OauthService', 'Config', '$timeout', 'storage', 'UserService',
        function ($rootScope, $scope, $state, OauthService, Config, $timeout, storage, UserService) {

            $rootScope.data = {
                username: 'admin@mail.ru',
                password: 'admin'
            };

            $scope.login = function () {
                OauthService.getAccessToken($rootScope.data, function () {
                    $timeout(function () {
                        $state.go(Config.routes.home.name);
                    }, 1500);

                });
            };
        }
    ]
);