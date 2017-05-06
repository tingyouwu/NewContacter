package com.future.wk.newcontacter.base;

import android.content.Context;

import java.io.Serializable;
import java.lang.ref.WeakReference;


public class NCAppContext implements Serializable {

    public static final String DB_COMMON = "common";//公共数据库
    public static final int PAGESIZE = 20;//每页20条数据

    private WeakReference<Context> context;
    private static NCAppContext instance = null;

    public static synchronized NCAppContext getInstance() {
        if (instance == null) {
            instance = new NCAppContext();
        }
        return instance;
    }

    /**
     * 获取 系统上下文
     */
    public static Context getContext() {
        if (getInstance().context == null) {
            return null;
        }
        return getInstance().context.get();
    }

    /**
     * 设置 系统上下文
     */
    public static void setContext(Context contextx) {
        if (getInstance().context != null) {
            getInstance().context.clear();
        }
        getInstance().context = new WeakReference<Context>(contextx);
    }

    public String getDBname(String dbname) {
        return "NewContacter_" + dbname;
    }

}
