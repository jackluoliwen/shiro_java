package com.sudojava.shiro.realm;

import com.sudojava.shiro.domain.User;
import org.junit.Before;
import org.junit.Test;

public class TestRealm {
    private Login login;
    @Before
    public void init(){
        login = new Login("classpath:shiro_realm.ini");
    }

    @Test
    public void login(){
        User user = new User();
        user.setUsername("zhang");
        user.setPassword("123");
        boolean flag = login.login(user);
        System.out.println(flag);
    }

}
