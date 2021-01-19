package com.datly.newsapp.util

import android.app.Activity
import android.content.Context

interface ScreenUtil {

    fun isTablet(activity: Activity): Boolean
    fun enableFullScreenMode(activity: Activity, enable: Boolean = false)
}