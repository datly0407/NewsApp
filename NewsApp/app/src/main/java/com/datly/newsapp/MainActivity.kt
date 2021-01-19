package com.datly.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datly.newsapp.util.ScreenUtil
import com.datly.newsapp.util.ScreenUtilImpl
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val screenUtil: ScreenUtil by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenUtil.enableFullScreenMode(this, true)
    }
}