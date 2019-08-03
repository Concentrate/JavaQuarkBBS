package com.restservice.service.util;

public class StringUtils {

    public static boolean equal(String t1, String t2) {
        if (t1 == null) {
            return t2 == null;
        } else {
            return t1.equals(t2);
        }
    }

    public static boolean equal(Object object, String toCompare) {
        if (object == null) {
            return toCompare == null;
        } else {
            return String.valueOf(object).equals(toCompare);
        }
    }
}
