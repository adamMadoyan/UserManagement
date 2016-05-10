'use strict';

/**
 * Login controller
 */

UserManagement.app.controllers.controller('home', ["$scope", function ($scope) {
    console.info('home controller started');
    $scope.title = "User Management";
}]);