package com.sudojava.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * 可以使用Realm获取数据库的基本信息，
 * 包括User表、权限表、和角色表
 */
public class LoginRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //取出用户名和密码
        String username = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
