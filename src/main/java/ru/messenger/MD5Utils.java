package ru.messenger;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String encryption(String str) {
        return DigestUtils.md5Hex(str);
    }

}
