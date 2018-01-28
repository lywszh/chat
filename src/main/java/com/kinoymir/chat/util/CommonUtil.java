package com.kinoymir.chat.util;

import com.kinoymir.chat.common.ChatRuntimeException;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CommonUtil {

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new ChatRuntimeException("加密错误");
        }
    }
}
