package com.wjs.seata4.config.dbsource;

public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(String key) {
        contextHolder.set(key);
    }

    public static String getDatabaseType() {
        return contextHolder.get();
    }
}
