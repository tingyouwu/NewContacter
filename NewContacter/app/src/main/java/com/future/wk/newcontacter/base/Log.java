package com.future.wk.newcontacter.base;

/**
 * Created by samsung on 2017/5/3.
 */

public class Log {
    private static int sLevel = Log.VERBOSE;

    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;

    //DEBUG
    public static void d(String tag, String msg){
        if(sLevel <= DEBUG){
            android.util.Log.d(tag, msg);
        }
    }
    public static void d(String tag, String msg, Throwable tr){
        if(sLevel <= DEBUG){
            android.util.Log.d(tag, msg, tr);
        }
    }

    //INFO
    public static void i(String tag, String msg){
        if(sLevel <= INFO){
            android.util.Log.i(tag, msg);
        }
    }
    public static void i(String tag, String msg, Throwable tr){
        if(sLevel <= INFO){
            android.util.Log.i(tag, msg, tr);
        }
    }

    //WARN
    public static void w(String tag, String msg){
        if(sLevel <= WARN){
            android.util.Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg, Throwable tr){
        if(sLevel <= WARN){
            android.util.Log.i(tag, msg, tr);
        }
    }

    //ERROR
    public static void e(String tag, String msg){
        if(sLevel <= ERROR){
            android.util.Log.i(tag, msg);
        }
    }
    public static void e(String tag, String msg, Throwable tr){
        if(sLevel <= ERROR){
            android.util.Log.i(tag, msg, tr);
        }
    }

}
