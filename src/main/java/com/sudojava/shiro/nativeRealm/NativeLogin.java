package com.sudojava.shiro.nativeRealm;

import com.sudojava.shiro.authorization.Authorization;
import com.sudojava.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeLogin {

    private static final Logger log = LoggerFactory.getLogger(Authorization.class);


    private RealmDataSource dataSource;

    public NativeLogin() {
        dataSource = new RealmDataSource();
    }

    public boolean login(User user) {
        boolean flag = false;
        SecurityUtils.setSecurityManager(dataSource.getSecurityManager());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);
            try {
                subject.login(token);
                System.out.println("login successfully");
                flag = true;
                if (subject.hasRole("manager")){
                    log.info("该用户拥有 manager 角色");
                    subject.checkPermission("delete");

                    if (subject.isPermitted("delete")){
                        log.info("该用户具有删除的权限");
                        subject.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    Thread.sleep(3000);
                                    log.info("删除资源中......");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }
            } catch (UnknownAccountException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
