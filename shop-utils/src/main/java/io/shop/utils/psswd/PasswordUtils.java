package io.shop.utils.psswd;

import io.shop.utils.md5.MD5Hash;

public class PasswordUtils {

    public static String getPassword(String password){
        if (password == null || password.trim().isEmpty()) return password;
        for (int i = 0; i < 5; i++){
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}

