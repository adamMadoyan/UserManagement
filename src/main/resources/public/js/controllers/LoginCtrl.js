'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('login',
    ['$scope', '$state', 'OauthService', 'Config', '$timeout',
        function ($scope, $state, OauthService, Config, $timeout) {

            $scope.user = {username: "username", password: 'password'};

            $scope.login = function () {
                OauthService.getAccessToken($scope.user, function () {

                    $timeout(function () {
                        $scope.go()
                    }, 3000);
                    console.info("next");

                });
            };

            $scope.go = function () {
                console.info('state');
                $state.go(Config.routes.home.name);
            }

        }
    ]
);