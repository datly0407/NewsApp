package com.datly.newsapp.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.datly.newsapp.network.Network

class NetworkImpl: Network {

    override fun isNetworkConnected(activity: Activity): NetworkStatus {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val capabilities = connectivityManager
                                    .getNetworkCapabilities(connectivityManager.activeNetwork)

        return when(capabilities != null) {
            true -> NetworkStatus.CONNECTED
            else -> NetworkStatus.NO_CONNECTION
        }
    }
}