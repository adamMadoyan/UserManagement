'use strict';

UserManagement.app.config(
    ['OAuthProvider', 'OAuthTokenProvider', 'Config',
        function (OAuthProvider, OAuthTokenProvider, Config) {
            OAuthProvider.configure({
                baseUrl: Config.BASE_URL,
                clientId: Config.CLIENT_ID,
                clientSecret: Config.CLIENT_SECRET,
                grantPath: Config.GRANT_PATH,
                revokePath: Config.REVOKE_TOKEN
            });
            OAuthTokenProvider.configure({
                name: 'token',
                options: {
                    secure: false
                }
            });
        }])
    .run(function ($rootScope, $window, $state, OAuth, OAuthToken, Config) {

        $rootScope.$on('oauth:error', function (event, rejection) {

            console.info(event);
            console.info(rejection);

            // Ignore `invalid_grant` error - should be catched on `LoginController`.
            if ('invalid_grant' === rejection.data.error) {
                return;
            }

            // Refresh token when a `invalid_token` error occurs.

            //if ('invalid_token' === rejection.data.error) {
            //    return OAuth.getRefreshToken();
            //}

            if ('invalid_token' === rejection.data.error) {
                return OAuthToken.removeToken();
            }

            // Redirect to `/login` with the `error_reason`.
            return $window.location.href = '/';
        });

        $rootScope.$on('$stateChangeStart',
            function (event, toState, toParams, fromState, fromParams, options) {
                //event.preventDefault();
                if (toState.isAuth) {
                    if (!OAuth.isAuthenticated()) {
                        $state.go(Config.routes.login.name);
                        event.preventDefault();
                    }
                } else {
                    if (OAuth.isAuthenticated()) {
                        $state.go(Config.routes.home.name);
                        event.preventDefault();
                    }
                }
            });
    }
);