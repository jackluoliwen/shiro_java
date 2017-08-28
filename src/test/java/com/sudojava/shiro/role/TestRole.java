package com.sudojava.shiro.role;

import com.sudojava.shiro.domain.User;
import org.junit.Before;
import org.junit.Test;

public class TestRole {

    private LoginRole loginRole;

    @Before
    public void  init(){
        loginRole = new LoginRole("classpath:shiro.ini");
    }

    @Test
    public void test(){
        User user = new User();
        user.setUsername("root");
        user.setPassword("secret");
        boolean flag = loginRole.login(user);
        System.out.println(flag);
    }
}
