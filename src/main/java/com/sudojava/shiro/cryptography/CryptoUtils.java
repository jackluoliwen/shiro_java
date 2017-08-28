package com.sudojava.shiro.cryptography;

import com.sudojava.shiro.commons.RandomNumberUtils;
import org.apache.shiro.crypto.hash.*;

public class CryptoUtils {


    protected static Object getSalt() {
        return RandomNumberUtils.getByteSource().toHex();
    }

    public static String cryptoObject(String source,String algorithm,int hashIterations) {
        switch (algorithm) {
            case "md5": {

                Md5Hash md5Hash = new Md5Hash(source,getSalt(),hashIterations);
                return  md5Hash.toHex();
            }
            case "md2":
            {
                Md2Hash md2Hash = new Md2Hash(source,getSalt(),hashIterations);
                return md2Hash.toHex();
            }
            case "sha1":
            {
                Sha1Hash sha1Hash = new Sha1Hash(source,getSalt(),hashIterations);
                return  sha1Hash.toHex();
            }
            case "sha256":
            {
                Sha256Hash sha256Hash = new Sha256Hash(source,getSalt(),hashIterations);
                return  sha256Hash.toHex();
            }
            default:
                return null;
        }

    }


}
