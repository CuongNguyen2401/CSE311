package com.fastfood.config;

public class ConfigInfor {
    private static String ctxRealPath="";

    public static String getCtxRealPath() {
        return ctxRealPath;
    }

    public static void setCtxRealPath(String ctxRealPath) {
    	ConfigInfor.ctxRealPath = ctxRealPath;
    }
    
}