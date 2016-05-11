'use strict';

UserManagement.app = angular.module('UserManagement', [
    'ui.router',
    'angular-oauth2',
    'UserManagement.app.controllers',
    'UserManagement.app.services',
    'UserManagement.app.directives',
    'UserManagement.app.filters'
]);