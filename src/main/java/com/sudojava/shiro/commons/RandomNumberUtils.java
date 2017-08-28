package com.sudojava.shiro.commons;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

public class RandomNumberUtils {
    //随机数的代码生成器
    private static RandomNumberGenerator generator;

    static {
        generator = new SecureRandomNumberGenerator();
    }

    /**
     * 获取工具类
     * @return
     */
    public static ByteSource getByteSource(){
        if (generator!=null){
            return  generator.nextBytes();
        }
        return  null;
    }
}
