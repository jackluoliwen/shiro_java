package com.sudojava.shiro.authorization;

import com.sudojava.shiro.domain.User;
import org.junit.Before;
import org.junit.Test;

public class TestAuthorization {
    private Authorization authorization;
    @Before
    public void init(){
        authorization = new Authorization("classpath:shiro.ini");
    }
    @Test
    public void login(){
        User user = new User();
        user.setPassword("guest");
        user.setUsername("guest");
       boolean flag =  authorization.login(user);
       if (flag){
           System.out.println("登录成功");
       }else{
           System.out.println("登录失败");
       }
    }
}
