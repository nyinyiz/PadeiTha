package com.hackathon2019.padeitha.domain.network

import com.codigo.comfort.InternetConnectivityInterceptor
import com.hackathon2019.padeitha.BuildConfig
import com.hackathon2019.padeitha.domain.services.FAQService
import com.hackathon2019.padeitha.domain.services.NewsService
import com.hackathon2019.padeitha.domain.services.PacksService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceInject {

    private fun provideRetrofit(
        baseUrl: String,
        internetConnectivityInterceptor: InternetConnectivityInterceptor
    ): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(internetConnectivityInterceptor)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.networkInterceptors().add(httpLoggingInterceptor)
        }

        val okHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun providePacksService(
        baseUrl: String,
        internetConnectivityInterceptor: InternetConnectivityInterceptor
    ): PacksService {
        return provideRetrofit(
            baseUrl,
            internetConnectivityInterceptor
        ).create(PacksService::class.java)
    }

    fun provideNewsService(
        baseUrl: String,
        internetConnectivityInterceptor: InternetConnectivityInterceptor
    ): NewsService {
        return provideRetrofit(
            baseUrl,
            internetConnectivityInterceptor
        ).create(NewsService::class.java)
    }

    fun provideFAQService(
        baseUrl: String,
        internetConnectivityInterceptor: InternetConnectivityInterceptor
    ): FAQService {
        return provideRetrofit(
            baseUrl,
            internetConnectivityInterceptor
        ).create(FAQService::class.java)
    }

}