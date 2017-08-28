package com.sudojava.shiro.jdbc;

import com.sudojava.shiro.domain.User;
import com.sudojava.shiro.jdbcrealm.LoginForJdbcRealm;
import org.junit.Before;
import org.junit.Test;

public class TestForJdbc {

    private LoginForJdbcRealm realm;

    @Before
    public void setup(){
        realm = new LoginForJdbcRealm("classpath:shiro-jdbc-realm.ini");
    }

    @Test
    public void login(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("admin");
        boolean flag = realm.login(user);
        System.out.println(flag);
    }
}
