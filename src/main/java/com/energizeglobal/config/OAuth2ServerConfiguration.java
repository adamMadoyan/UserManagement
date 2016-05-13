package com.energizeglobal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class OAuth2ServerConfiguration {


    private static final String RESOURCE_ID = "restservice";

    private static final String CLIENT_APP = "user_management";

    private static final String APP_SECRET = "8e43ecf6-57f4-4522-8f3e-08b22f356e7d";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }


        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/",
                            "/public/**",
                            "/resources/**",
                            "/resources/public/**",
                            "/partials/**",
                            "/js/**",
                            "/css/**",
                            "/webjars/**",
                            "/favicon.ico",
                            "/users",
                            "/users/exist/*",
                            "/oauth/revoke-token"
                    )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .logout()
                    .logoutUrl("/oauth/revoke")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/oauth/revoke"))
//                    .clearAuthentication(true)
                    .deleteCookies("token")
//                    .invalidateHttpSession(true)
                    .permitAll()
            ;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {


        private TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient(CLIENT_APP)
                    .authorizedGrantTypes("password")
                    .authorities("USER")
                    .scopes("read", "write")
                    .resourceIds(RESOURCE_ID)
                    .accessTokenValiditySeconds(-1)
//                    .autoApprove(true)
                    .secret(APP_SECRET);
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore);
            return tokenServices;
        }
    }
}
