package com.hackathon2019.padeitha

import android.app.Application
import android.util.Log
import com.hackathon2019.padeitha.domain.di.applicationModule
import com.hackathon2019.padeitha.domain.di.homeModule
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class PadeithaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { throwable ->
            run {
                Log.d("RX error : ", "Rx error : ${throwable.localizedMessage}")
            }
        } // nothing or some logging

        startKoin {
            androidContext(this@PadeithaApplication)
            androidFileProperties()
            modules(
                listOf(
                    applicationModule,
                    homeModule
                )
            )
        }
    }
}