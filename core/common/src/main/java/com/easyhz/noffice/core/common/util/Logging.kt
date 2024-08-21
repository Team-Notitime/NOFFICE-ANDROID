package com.easyhz.noffice.core.common.util

import android.util.Log

fun errorLogging(tag: String, message: String, error: Throwable) {
    Log.e(tag, "$message - $error")
}