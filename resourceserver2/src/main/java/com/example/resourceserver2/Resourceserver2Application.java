package com.example.resourceserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @Author: liulang
 * @Date: 2020/7/27 12:30
 */
@SpringBootApplication
@EnableOAuth2Sso
@EnableWebSecurity
public class Resourceserver2Application {

    public static void main(String[] args) {
        SpringApplication.run(Resourceserver2Application.class,args);
    }
}
