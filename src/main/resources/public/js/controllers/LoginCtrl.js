'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('login',
    ['$scope', '$state', 'OauthService', 'Config',
        function ($scope, $state, OauthService, Config) {

            $scope.user = {username : "username", password:'password'};

            $scope.login = function() {
                OauthService.getAccessToken($scope.user, function(promise) {
                    //console.info(promise.status);
                    //console.info(promise.value.data);
                    //console.info(promise.value.status);
                    //$state.go(Config.routes.home.name);
                });
            };

            $scope.login();

        }
    ]
);