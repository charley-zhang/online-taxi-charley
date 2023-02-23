package com.charley.internalcommon.util;

public class SseprefixUtils {

    public static final String SPERATOR = "$";

    public static String generatorSseKey(Long userId, String identity){
        return userId+SPERATOR+identity;
    }
}
