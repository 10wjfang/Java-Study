package com.fang;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Base64测试
 *
 * @author fwj
 * @date 2019-03-14 16:07
 **/
public class Base64Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = Base64.encode("我爱编程".getBytes("gb2312"));
        System.out.println("encode = " + encode);
        byte[] decode = Base64.decode(encode);
        String str = new String(decode, "gb2312");
        System.out.println("str = " + str);
    }
}
