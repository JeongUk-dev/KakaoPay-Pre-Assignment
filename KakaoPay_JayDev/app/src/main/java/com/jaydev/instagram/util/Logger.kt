package com.jaydev.instagram.util

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log

object Logger {
    private val TAG = Logger::class.java.simpleName

    @JvmField
    var isDebuggable = false

    @JvmStatic
    fun setDebuggable(application: Application) {
        val pm = application.packageManager

        isDebuggable = try {
            val appInfo = pm.getApplicationInfo(application.packageName, 0)
            0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    @JvmStatic
    fun d(message: String) {
        if (isDebuggable) {
            Log.d(TAG, buildLog(message))
        }
    }

    @JvmStatic
    fun i(message: String) {
        if (isDebuggable) {
            Log.i(TAG, buildLog(message))
        }
    }

    @JvmStatic
    fun v(message: String) {
        if (isDebuggable) {
            Log.v(TAG, buildLog(message))
        }
    }

    @JvmStatic
    fun w(message: String) {
        if (isDebuggable) {
            Log.w(TAG, buildLog(message))
        }
    }

    @JvmStatic
    fun e(message: String) {
        if (isDebuggable) {
            Log.e(TAG, buildLog(message))
        }
    }

    private fun buildLog(message: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        return "[${ste.fileName}::${ste.lineNumber}::${ste.methodName}] $message"
    }
}
