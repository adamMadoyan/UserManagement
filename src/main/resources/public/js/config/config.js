'use strict';

UserManagement.app.constants({

    routes: {
        login: {
            name: 'login',
            title: 'login',
            url: '/login',
            templateUrl: "/partials/pages/login.html",
            controller: 'login',
            isAuth: false
        },
        home: {
            name: 'home',
            title: 'home',
            url: '/home',
            controller: 'home',
            templateUrl: "/partials/pages/home.html",
            isAuth: false
        }
    }

});