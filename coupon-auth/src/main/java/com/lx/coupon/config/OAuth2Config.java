package com.lx.coupon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${signingKey}")
    private String signingKey;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.inMemory()
                .withClient("config-server") //创建了一个client名为config-server的客户端
                .secret("myserect")
                .authorizedGrantTypes("refresh_token", "password","client_credentials")//配置验证类型
                .scopes("webclient")//配置客户端域为“ui”
                /*.and()
                .withClient("service-hi")
                .secret("myserect")
                .authorizedGrantTypes("client_credentials", "refresh_token","password")
                .scopes("server")*/;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.authenticationManager(authenticationManager) //WebSecurity配置好的
                .userDetailsService(userDetailsService)//读取用户的验证信息
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //配置获取Token的策略
        oauthServer
                .tokenKeyAccess("permitAll()") //对获取Token的请求不再拦截
                .checkTokenAccess("isAuthenticated()"); //验证获取Token的验证信息

    }
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("myserect");
        return converter;
    }
}
