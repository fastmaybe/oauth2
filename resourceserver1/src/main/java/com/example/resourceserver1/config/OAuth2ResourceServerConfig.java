package com.example.resourceserver1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @Author: liulang
 * @Date: 2020/7/27 12:40
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置 /login 无需权限访问
                .antMatchers("/login").permitAll()
                // 设置其它请求，需要认证后访问
                .anyRequest().authenticated()

        ;
    }

//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices(){
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8101/oauth/check_token");
//        tokenServices.setClientId("clientapp");
//        tokenServices.setClientSecret("112233");
//       return tokenServices;
//    }
}
