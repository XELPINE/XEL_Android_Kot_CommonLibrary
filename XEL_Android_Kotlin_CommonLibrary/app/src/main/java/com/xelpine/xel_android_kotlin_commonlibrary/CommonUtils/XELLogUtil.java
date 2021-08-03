package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.util.Log;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;


public class XELLogUtil {

    public static void e(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message);
        }
    }

    public static void e_network(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ==== " + message);
        }
    }

    public static void e_function(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ===== " + message);
        }
    }

    public static void e_functionLog(String tag, String message, Exception e) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ===== " + message, e);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

            Log.e(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message, e);
        }
    }

    public static void d(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.d(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message);
        }
    }

    public static void d_function(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.d(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ===== " + message);
        }
    }

    public static void i(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.i(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message);
        }
    }

    public static void i_function(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.i(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ===== " + message);
        }
    }

    public static void i_network(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.i(tag, "[" + className + "." + methodName + "():" + lineNumber + "] ==== " + message);
        }
    }

    public static void v(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.v(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message);
        }
    }

    public static void w(String tag, String message) {
        if (XELGlobalDefine.L_LOG_ENABLED) {
            String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
            Log.w(tag, "[" + className + "." + methodName + "():" + lineNumber + "] " + message);
        }
    }
}
