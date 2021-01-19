package com.datly.newsapp.util

import android.app.Activity
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import kotlin.math.pow
import kotlin.math.sqrt

class ScreenUtilImpl: ScreenUtil {

    companion object {

        const val SMALLEST_TABLET_SCREEN_SIZE = 7.0
        @Synchronized
        fun getInstance(): ScreenUtil = ScreenUtilImpl()
    }

    override fun isTablet(activity: Activity): Boolean {
        val display = activity.windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)

        val screenWidth = displayMetrics.widthPixels / displayMetrics.densityDpi.toDouble()
        val screenHeight = displayMetrics.heightPixels / displayMetrics.densityDpi.toDouble()
        val screenDiagonal = sqrt(screenWidth.pow(2) + screenHeight.pow(2))
        return screenDiagonal >= SMALLEST_TABLET_SCREEN_SIZE
    }

    override fun enableFullScreenMode(activity: Activity, enable: Boolean) {
        when(enable) {
            true -> {
                activity.window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    statusBarColor = Color.TRANSPARENT
                }
            }

            else -> {}
        }
    }
}