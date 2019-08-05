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


    public static boolean isEmpty(String t) {
        return org.springframework.util.StringUtils.isEmpty(t);
    }

    public static int parseStringToNum(String tmp) {
        int result = -1;
        try {
            result = Integer.valueOf(tmp);
        } catch (Exception e) {

        }
        return result;
    }


    public static boolean parseStringInValuedCollection(String tmp, int... values) {
        int result = parseStringToNum(tmp);
        for (int i : values) {
            if (result == i) {
                return true;
            }
        }
        return false;
    }
}
