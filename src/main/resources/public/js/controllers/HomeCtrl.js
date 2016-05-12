'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('home', ['$scope', 'OAuthToken', 'OAuth', function ($scope, OAuthToken, OAuth) {
    console.info('home controller started');

    console.info(OAuthToken.getAccessToken());

    console.info(OAuth.isAuthenticated());


}]);