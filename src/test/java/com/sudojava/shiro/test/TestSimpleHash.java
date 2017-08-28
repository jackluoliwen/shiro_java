package com.sudojava.shiro.test;

import com.sudojava.shiro.cryptography.CryptoUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class TestSimpleHash {

    @Test
    public void testMethod() {
        String username = "admin";
        String result1 = CryptoUtils.cryptoObject(username, "md5", 4);
        System.out.println(result1);

//        String result2 = CryptoUtils.cryptoObject(username, "md2", 4);
//        System.out.println(result2);
//        String result3 = CryptoUtils.cryptoObject(username, "sha1", 4);
//        System.out.println(result3);

    }
}
