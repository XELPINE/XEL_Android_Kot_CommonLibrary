package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.util.Log
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import java.lang.Exception

class XELLogUtil {
    companion object {
        fun e(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.e(tag, "[$className.$methodName():$lineNumber] $message")
            }
        }

        fun e_network(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.e(tag, "[$className.$methodName():$lineNumber] ==== $message")
            }
        }

        fun e_function(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.e(tag, "[$className.$methodName():$lineNumber] ===== $message")
            }
        }

        fun e_functionLog(tag: String?, message: String, e: Exception?) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.e(tag, "[$className.$methodName():$lineNumber] ===== $message", e)
            }
        }

        fun e(tag: String?, message: String, e: Exception?) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.e(tag, "[$className.$methodName():$lineNumber] $message", e)
            }
        }

        fun d(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.d(tag, "[$className.$methodName():$lineNumber] $message")
            }
        }

        fun d_function(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.d(tag, "[$className.$methodName():$lineNumber] ===== $message")
            }
        }

        fun i(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.i(tag, "[$className.$methodName():$lineNumber] $message")
            }
        }

        fun i_function(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.i(tag, "[$className.$methodName():$lineNumber] ===== $message")
            }
        }

        fun i_network(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.i(tag, "[$className.$methodName():$lineNumber] ==== $message")
            }
        }

        fun v(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.v(tag, "[$className.$methodName():$lineNumber] $message")
            }
        }

        fun w(tag: String?, message: String) {
            if (XELGlobalDefine.L_LOG_ENABLED) {
                val fullClassName = Thread.currentThread().stackTrace[3].className
                val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
                val methodName = Thread.currentThread().stackTrace[3].methodName
                val lineNumber = Thread.currentThread().stackTrace[3].lineNumber
                Log.w(tag, "[$className.$methodName():$lineNumber] $message")
            }
        }
    }
}
