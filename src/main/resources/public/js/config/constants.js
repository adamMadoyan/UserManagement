'use strict';

UserManagement.app.constant('Config', {

    BASE_URL: 'http://localhost:9000',
    CLIENT_ID: 'user_management',
    CLIENT_SECRET: '8e43ecf6-57f4-4522-8f3e-08b22f356e7d',
    GRANT_PATH: '/oauth/token',
    REVOKE_TOKEN: '/oauth/revoke',

    USER_KEY : 'user',

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
            isAuth: true
        },
        new: {
            name: 'new',
            title: 'new',
            url: '/new',
            controller: 'register',
            templateUrl: "/partials/pages/register.html",
            isAuth: false
        }
    }
});