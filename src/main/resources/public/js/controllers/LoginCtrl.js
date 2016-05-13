'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('login',
    ['$rootScope', '$scope', '$state', 'OauthService', 'Config', '$timeout', 'storage', 'UserService',
        function ($rootScope, $scope, $state, OauthService, Config, $timeout, storage, UserService) {

            $rootScope.isLoading = false;

            $rootScope.data = {
                username: 'admin@mail.ru',
                password: 'admin'
            };

            $scope.login = function () {
                $rootScope.isLoading = true;
                OauthService.getAccessToken($rootScope.data, function () {
                    $timeout(function () {
                        $rootScope.isLoading = false;
                        $state.go(Config.routes.home.name);
                    }, 1500);

                });
            };
        }
    ]
);