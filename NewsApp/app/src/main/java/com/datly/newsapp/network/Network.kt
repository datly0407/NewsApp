package com.datly.newsapp.network

import android.app.Activity

interface Network {

    fun isNetworkConnected(activity: Activity): NetworkStatus
}