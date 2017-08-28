package com.sudojava.shiro.realm;

import com.sudojava.shiro.authorization.Authorization;
import com.sudojava.shiro.domain.User;
import com.sudojava.shiro.basic.BasicShiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends BasicShiro<User> {

    private static final  Logger log = LoggerFactory.getLogger(Authorization.class);


    public Login(String shiro_ini) {
        super(shiro_ini);
    }

    @Override
    public boolean login(User user) {
        SecurityUtils.setSecurityManager(getManager());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            token.setRememberMe(true);
            try {
               subject.login(token);
               return  true;
            }catch (UnknownAccountException e){
                log.info("账号不匹配");
            }
        }
        return false;
    }
}
