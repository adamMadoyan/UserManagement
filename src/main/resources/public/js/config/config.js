'use strict';

UserManagement.app.config(['OAuthProvider', 'OAuthTokenProvider', 'Config', function (OAuthProvider, OAuthTokenProvider, Config) {
        OAuthProvider.configure({
            baseUrl: Config.BASE_URL,
            clientId: 'user_management',
            clientSecret: '8e43ecf6-57f4-4522-8f3e-08b22f356e7d',
            grantPath: '/oauth/token'
        });
        OAuthTokenProvider.configure({
            name: 'token',
            options: {
                secure: true
            }
        });
    }])
    .run(['$rootScope', '$window', 'OAuth', function ($rootScope, $window, OAuth) {
            $rootScope.$on('oauth:error', function (event, rejection) {
                // Ignore `invalid_grant` error - should be catched on `LoginController`.
                if ('invalid_grant' === rejection.data.error) {
                    return;
                }

                // Refresh token when a `invalid_token` error occurs.
                if ('invalid_token' === rejection.data.error) {
                    return OAuth.getRefreshToken();
                }

                // Redirect to `/login` with the `error_reason`.
                return $window.location.href = '/';
            });
        }]
    );