package com.example.outhserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Author: liulang
 * @Date: 2020/7/27 11:07
 * 配置 授权服务器
 *
 * 1 在类上添加 @EnableAuthorizationServer 注解，声明开启 OAuth 授权服务器的功能。
 * 2  AuthorizationServerConfigurerAdapter 类，进行 OAuth 授权服务器的配置。
 */


/**
 * 在客户端访问资源服务器时，会在请求中带上访问令牌。
 * 在资源服务器收到客户端的请求时，会使用请求中的访问令牌，找授权服务器确认该访问令牌的有效性。
 */
@Configuration
@EnableAuthorizationServer
public class MyOAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 用户认证 Manager
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    //配置授权服务器端点

    /**
     * #configure(AuthorizationServerEndpointsConfigurer endpoints) 方法，
     * 配置使用的 AuthenticationManager 实现用户认证的功能。其中，authenticationManager
     * 是由「2.1.2 SecurityConfig」创建，Spring Security 的配置类。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }


    /**  见  --》 resource 下 check_token.png
     *
     *#configure(AuthorizationServerSecurityConfigurer oauthServer) 方法，设置 /oauth/check_token 端点，通过认证后可访问。
     *
     * 友情提示：这里的认证，指的是使用 client-id + client-secret 进行的客户端认证，不要和用户认证混淆。
     *
     * 其中，/oauth/check_token 端点对应 CheckTokenEndpoint 类，用于校验访问令牌的有效性。
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("isAuthenticated()");
    }


    //   见  --》 resource 下 client.png
    //配置客户端详情信息，客户端详情信息在这里进行初始化，后续可以通过数据库来存储调取详情信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients
               .inMemory()
               .withClient("clientapp").secret("112233") // <4.2> Client 账号、密码。
               .authorizedGrantTypes("authorization_code","refresh_token") // <4.2> code模式
               .scopes("read_userinfo", "read_contacts","password") // <4.2> 可授权的 Scope
                .redirectUris("http://localhost:8102/client1/login")
                .autoApprove(true)

       .and()
               .withClient("clientapp2").secret("112233") // <4.2> Client 账号、密码。
               .authorizedGrantTypes("authorization_code","refresh_token","password") // <4.2> code模式
               .scopes("read_userinfo", "read_contacts") // <4.2> 可授权的 Scope
               .redirectUris("http://localhost:8103/client2/login")
               .autoApprove(true)

//                .and().withClient() // <4.3> 可以继续配置新的 Client
       ;
    }
}
