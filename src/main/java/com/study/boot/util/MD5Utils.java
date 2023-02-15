package com.study.boot.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {
    public static final String SALT = "-1.x";
    public static String MD5HAsh(String password){
        String retPassword = new Md5Hash(password,SALT,3).toHex();
        System.out.println(new Md5Hash(password,SALT,3).toString());
        return retPassword;
    }
}
