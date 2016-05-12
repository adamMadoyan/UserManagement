'use strict';

/**
 * Main controller
 */

UserManagement.app.controllers.controller('main',
    ['$scope', 'OauthService', '$state', 'Config', '$timeout',
        function ($scope, OauthService, $state, Config, $timeout) {
            console.info('main controller started');

            $scope.logout = function () {
                OauthService.logout(function() {
                    $timeout(function () {
                        $state.go(Config.routes.login.name);
                    }, 100);
                });
            };

        }
    ]
);