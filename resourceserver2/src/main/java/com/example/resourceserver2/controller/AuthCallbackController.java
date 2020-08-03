package com.example.resourceserver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liulang
 * @Date: 2020/7/29 15:28
 */
//@RestController
//@RequestMapping("api")
public class AuthCallbackController {

    @Autowired
    private OAuth2ClientProperties oauth2ClientProperties;

    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    @RequestMapping("callback")
    public OAuth2AccessToken callback(@RequestParam String code, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //创建 AuthorizationCodeResourceDetails 对象
        AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setClientId(oauth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oauth2ClientProperties.getClientSecret());
        resourceDetails.setAccessTokenUri(accessTokenUri);

        // 创建 OAuth2RestTemplate 对象
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAuthorizationCode(code); // 设置 code
        restTemplate.getOAuth2ClientContext().getAccessTokenRequest().setPreservedState("http://localhost:8102/api/callback"); // 通过这个方式，设置 redirect_uri 参数
        restTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
        response.sendRedirect("http://localhost:8102/api/example/hello");
        return restTemplate.getAccessToken();
    }
}
