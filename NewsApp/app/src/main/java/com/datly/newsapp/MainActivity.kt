package com.datly.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datly.newsapp.util.ScreenUtilImpl


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScreenUtilImpl.getInstance().enableFullScreenMode(this, true)
    }
}