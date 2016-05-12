'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('login',
    ['$scope', '$state', 'OauthService', 'Config', '$timeout',
        function ($scope, $state, OauthService, Config, $timeout) {

            $scope.user = {
                username: '',
                password: ''
            };

            $scope.login = function () {
                OauthService.getAccessToken($scope.user, function () {

                    $timeout(function () {
                        $state.go(Config.routes.home.name);
                    }, 1000);

                });
            };
        }
    ]
);