'use strict';

UserManagement.app.config(function ($stateProvider, $urlRouterProvider) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/");
    //
    // Now set up the states
    //for(var stateName in UserManagement.app.constant.routes) {
    //    var routParams = UserManagement.app.constant.routes[stateName];
    //    $stateProvider.state(stateName, routParams);
    //}

    $stateProvider
        .state('login', {
            name: 'login',
            title: 'login',
            url: '/login',
            templateUrl: "/partials/pages/login.html",
            controller: 'login',
            isAuth: false
        })
        .state('home', {
            name: 'home',
            title: 'home',
            url: '/home',
            controller: 'home',
            templateUrl: "/partials/pages/home.html",
            isAuth: false
        });

});