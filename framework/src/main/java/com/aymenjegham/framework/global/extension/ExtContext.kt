package com.aymenjegham.framework.global.extension

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context?.isNetworkAvailable(): Boolean =
    this?.let {
        val connectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val netCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } ?: run {
        return false
    }