package com.datly.newsapp.util

import android.app.Activity

interface ScreenUtil {

    fun isTablet(activity: Activity): Boolean
    fun enableFullScreenMode(activity: Activity, enable: Boolean = false)
}