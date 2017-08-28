package com.sudojava.shiro.basic;

import com.sudojava.shiro.authorization.Authorization;
import com.sudojava.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasicShiro<T> {



    private Factory<SecurityManager> factory;

    private SecurityManager manager;

    public BasicShiro(String shiro_ini) {
        //获取SecurityManager工厂，加载shiro.ini文件
        factory = new IniSecurityManagerFactory(shiro_ini);
        //得到SecurityManager实例，并绑定给SecurityUtils
        manager = factory.getInstance();
    }

    public Factory<SecurityManager> getFactory() {
        return factory;
    }

    public void setFactory(Factory<SecurityManager> factory) {
        this.factory = factory;
    }

    public SecurityManager getManager() {
        return manager;
    }

    public void setManager(SecurityManager manager) {
        this.manager = manager;
    }

    public abstract boolean login(T t);


}
