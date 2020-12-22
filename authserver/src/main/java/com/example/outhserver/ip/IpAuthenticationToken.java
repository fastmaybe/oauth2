package com.example.outhserver.ip;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author: liulang
 * @Date: 2020/8/21 18:11
 */
public class IpAuthenticationToken extends AbstractAuthenticationToken {

    private String ip;

    public String getIp(){
        return this.ip;
    }
    public void setIp(String ip){
        this.ip = ip;
    }

    public IpAuthenticationToken(String ip){
        super(null);
        this.ip = ip;
        super.setAuthenticated(false);//注意这个构造方法是认证时使用的
    }
    public IpAuthenticationToken(String ip,Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.ip = ip;
        super.setAuthenticated(true);//注意这个构造方法是认证成功后使用的
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
