package com.example.outhserver.ip;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liulang
 * @Date: 2020/8/21 18:16
 */
public class IpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    //使用/ipVerify该端点进行ip认证
    IpAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String host = httpServletRequest.getRemoteHost();
        return getAuthenticationManager().authenticate(new IpAuthenticationToken(host));
    }
}
