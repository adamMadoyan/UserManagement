'use strict';

UserManagement.app = angular.module('UserManagement', [
    'ui.router',
    'UserManagement.app.controllers',
    'UserManagement.app.services',
    'UserManagement.app.directives',
    'UserManagement.app.filters'
]);