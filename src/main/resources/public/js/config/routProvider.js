'use strict';

UserManagement.app.config(function($stateProvider, $urlRouterProvider) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/");
    //
    // Now set up the states
    //for(var stateName in UserManagement.app.constants.routes) {
    //    var routParams = UserManagement.app.constants.routes[stateName];
    //    $stateProvider.state(stateName, routParams);
    //}
});