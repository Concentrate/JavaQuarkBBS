package com.quark.rest.utils;

import java.io.File;

/**
 * @Author LHR
 * Create By 2017/8/22
 */
public class Constants {

    public static final String RELEATIVE_UPLOAD_PATH = "images/upload";
    //Upload常
    public static String UPLOAD_PATH = "";
    public static final String STATIC_URL = "http://127.0.0.1/" + RELEATIVE_UPLOAD_PATH;

    static {
        UPLOAD_PATH = System.getProperty("user.home");
        if (UPLOAD_PATH.endsWith(File.separator)) {
            UPLOAD_PATH = UPLOAD_PATH + File.separator;
        }
        UPLOAD_PATH = UPLOAD_PATH + RELEATIVE_UPLOAD_PATH;


    }

}
