package com.org.securityservice.utils;

public class Utils {

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T {
        throw (T) exception;
    }

    public static void throwException(Throwable exception){
        Utils.<RuntimeException>throwException(exception, null);
    }
}
