package com.check.common.util;

/**
 * @author zzc
 */
public class LogUtils {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    public static String getErrorLog(String ip, String url, String code, String message) {
        return LINE_SEPARATOR +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR +
                "+  IP: " + ip + LINE_SEPARATOR +
                "+  URL: " + url + LINE_SEPARATOR +
                "+  CODE: " + code + LINE_SEPARATOR +
                "+  MESSAGE: " + message + LINE_SEPARATOR +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                LINE_SEPARATOR;
    }

    public static String getInfoLog(String ip, String url, String code, String message, String userName) {
        return LINE_SEPARATOR +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR +
                "+  USERNAME: " + userName + LINE_SEPARATOR +
                "+  IP: " + ip + LINE_SEPARATOR +
                "+  URL: " + url + LINE_SEPARATOR +
                "+  CODE: " + code + LINE_SEPARATOR +
                "+  MESSAGE: " + message + LINE_SEPARATOR +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                LINE_SEPARATOR;
    }

}
