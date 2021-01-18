package com.datly.newsapp.util

import android.app.Activity

interface ScreenUtil {

    fun enableFullScreenMode(activity: Activity, enable: Boolean = false)
}