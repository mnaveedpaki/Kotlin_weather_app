package com.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    //check if internet available or not
    const val BASE_URL : String = "http://api.openweathermap.org/data/"
    const val APP_ID : String = "d2c1332e3ef914969eac3b1041fdc314"
    const val PREFERENCE_NAME : String = "weatherAppPreferences"
    const val WEATHER_RESPONSE_DATA : String = "weather_response_data"
    const val MATRIC_UNIT : String = "matric"

    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val network = connectivityManager.activeNetwork ?: return false
                val activeNetwork = connectivityManager.getNetworkCapabilities(network)
            return when{
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}