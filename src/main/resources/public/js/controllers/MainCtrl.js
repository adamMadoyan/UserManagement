'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('main', ['$scope', 'OAuthToken', function ($scope, OAuthToken) {
    console.info('main controller started');
    $scope.title = "User Management";

    //OAuthToken.removeToken();

}]);