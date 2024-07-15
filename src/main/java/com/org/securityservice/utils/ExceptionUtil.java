package com.org.securityservice.utils;

public class ExceptionUtil {

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T {
        throw (T) exception;
    }

    public static void throwException(Throwable exception){
        ExceptionUtil.<RuntimeException>throwException(exception, null);
    }
}
