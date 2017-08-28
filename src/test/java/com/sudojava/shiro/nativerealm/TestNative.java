package com.sudojava.shiro.nativerealm;

import com.sudojava.shiro.domain.User;
import com.sudojava.shiro.nativeRealm.NativeLogin;
import org.junit.Before;
import org.junit.Test;

public class TestNative {

    private NativeLogin nativeLogin;

    @Before
    public void setup(){
        nativeLogin = new NativeLogin();
    }

    @Test
    public void loginUser(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("admin");
        boolean flag = nativeLogin.login(user);
        System.out.println(flag);
    }
}
