package com.sudojava.shiro.role;

import com.sudojava.shiro.authorization.Authorization;
import com.sudojava.shiro.basic.BasicShiro;
import com.sudojava.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRole extends BasicShiro<User> {

    private static final transient Logger log = LoggerFactory.getLogger(Authorization.class);


    public LoginRole(String shiro_ini) {
        super(shiro_ini);
    }

    @Override
    public boolean login(User user) {
        SecurityUtils.setSecurityManager(getManager());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);
            try {
                subject.login(token);
                if (subject.hasRole("admin")) {
                    log.info("该用户拥有admin角色身份");
                    if (subject.isPermitted("user:delete")) {
                        log.info("该用户具有删除选项功能");
                        subject.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                    log.info("执行删除操作");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }
                return true;
            } catch (UnknownAccountException e) {
                log.info("未知用户");
            }
        }
        return false;
    }
}
