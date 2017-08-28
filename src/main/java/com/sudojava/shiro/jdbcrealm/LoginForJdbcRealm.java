package com.sudojava.shiro.jdbcrealm;

import com.sudojava.shiro.authorization.Authorization;
import com.sudojava.shiro.domain.User;
import com.sudojava.shiro.basic.BasicShiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginForJdbcRealm  extends BasicShiro<User> {

    private static final Logger log = LoggerFactory.getLogger(Authorization.class);

    public LoginForJdbcRealm(String shiro_ini) {
        super(shiro_ini);
    }

    @Override
    public boolean login(User user) {
        SecurityUtils.setSecurityManager(getManager());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token  =
                    new UsernamePasswordToken(user.getUsername(),user.getPassword());
            try {
                subject.login(token);
                token.setRememberMe(true);
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
                return true;
            } catch (UnknownAccountException e) {
               log.info(e.getMessage()+"账号名有误");
            }
        }
        return false;
    }
}
