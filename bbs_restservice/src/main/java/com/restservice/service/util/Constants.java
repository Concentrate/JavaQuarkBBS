package com.restservice.service.util;

import java.io.File;

public class Constants {

    public static final int POST_AWARD = 5;

    public static final String TOP_TYPE = "top";
    public static final String GOOD_TYPE = "good";

    public static final long HOUR_IN_MILLISEC = 3600 * 1000;


    public static final int NEW_USER_RECENT_HOUR = 7 * 24;
    public static final int NEW_POSTS_HOUR = 3 * 24;


    public static final int USER_RECENT_POSTS_HOUR = 5 * 24;
    public static final int USER_RECENT_POSTS_LIMIT = 100;

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
