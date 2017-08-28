package com.sudojava.shiro.authorization;

import com.sudojava.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Authorization {

    private static final transient Logger log = LoggerFactory.getLogger(Authorization.class);

    private Factory<SecurityManager> factory;

    private SecurityManager manager;

    public Authorization(String shiro_ini) {
        //获取SecurityManager工厂，加载shiro.ini文件
        factory = new IniSecurityManagerFactory(shiro_ini);
        //得到SecurityManager实例，并绑定给SecurityUtils
        manager = factory.getInstance();
    }


    /**
     * 模拟用户登录过程
     *
     * @param user
     * @return
     */
    public boolean login(User user) {
        boolean flag = false;
        SecurityUtils.setSecurityManager(manager);
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);//记住用户登录信息

            try {
                currentUser.login(token);//执行登录操作，进行身份验证
                flag = true;
            } catch (UnknownAccountException e) {
                log.info("未知用户账号异常");
            } catch (IncorrectCredentialsException e) {
                log.info("凭证不正确");
            } catch (ExcessiveAttemptsException e) {
                log.info("尝试认证次数多于系统指定次数");
            } catch (AuthenticationException e) {
                //所有认证异常的父类
                log.info("其他未知错误!");
            }

        }
        currentUser.logout();//用户登出操作
        return flag;
    }


}
