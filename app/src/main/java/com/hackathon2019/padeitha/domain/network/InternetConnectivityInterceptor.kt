package com.codigo.comfort

import android.content.Context
import android.net.ConnectivityManager
import com.nyinyi.mymovie.data.api.inject.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class InternetConnectivityInterceptor(context: Context) : Interceptor {
    private val applicationContext: Context = context.applicationContext

    private val isConnected: Boolean
        get() {
            val cm = applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!isConnected) {
            throw NetworkException("No Internet connection")
        }
        return chain.proceed(originalRequest)
    }
}