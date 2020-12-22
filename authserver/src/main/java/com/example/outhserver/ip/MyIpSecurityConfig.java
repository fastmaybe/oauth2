package com.example.outhserver.ip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: liulang
 * @Date: 2020/8/21 18:20
 */
@Configuration
@EnableWebSecurity
public class MyIpSecurityConfig extends WebSecurityConfigurerAdapter {

//    //ip认证者配置
//    @Bean
//    IpAuthenticationProvider ipAuthenticationProvider() {
//        return new IpAuthenticationProvider();
//    }
//
//    //配置封装ipAuthenticationToken的过滤器
//    IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
//        IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IpAuthenticationProcessingFilter();
//        //为过滤器添加认证器
//        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
//        //重写认证失败时的跳转页面
//        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
//        return ipAuthenticationProcessingFilter;
//    }

    //配置登录端点
//    @Bean
//    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
//        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint
//                ("/login");
//        return loginUrlAuthenticationEntryPoint;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
                // 设置 /login 无需权限访问
//                .antMatchers("/login").permitAll()
//                 设置其它请求，需要认证后访问
//                .anyRequest().authenticated()
//                        .and()
//                .exceptionHandling()
//                .accessDeniedPage("/ipLogin")
//                .authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
        ;
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/Login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/ipLogin")
//                .authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
//        ;

        //注册IpAuthenticationProcessingFilter  注意放置的顺序 这很关键
//        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }



    /**
     * 需要配置这个支持password模式 support password grant type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //使用内存
                .inMemoryAuthentication()
                //不适用密码编码器
                .passwordEncoder(passwordEncoder())
                //配置一个用户
                .withUser("admin").password("admin").roles("USER");

        //支持ip
//        auth.authenticationProvider(ipAuthenticationProvider());
    }

}
