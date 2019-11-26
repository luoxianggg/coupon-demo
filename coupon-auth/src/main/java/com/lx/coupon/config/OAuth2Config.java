package com.lx.coupon.config;

import com.lx.coupon.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * @author Rob Winch
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserDetailsService userServiceDetail;
    @Autowired
    private UserServiceDetail userServiceDetail;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
               // .realm("oauth2-resources") // code授权添加
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") // allow check token
                        .allowFormAuthenticationForClients();//允许表单认证
    } 	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
         .tokenStore(new RedisTokenStore(redisConnectionFactory))
        // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // 要使用refresh_token的话，需要额外配置userDetailsService
         	endpoints.userDetailsService(userServiceDetail);
      }
        	@Override
         public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        		clients.inMemory().withClient("demoApp")
                        .secret(passwordEncoder.encode("demoAppSecret")) //对客户端密码加密
                    //    .redirectUris("http://baidu.com")// code授权添加
                 		.authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
                 			.scopes("all").resourceIds("oauth2-resource").accessTokenValiditySeconds(1200)
                 		.refreshTokenValiditySeconds(50000);
                }

}
