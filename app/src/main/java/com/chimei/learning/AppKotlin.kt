package com.chimei.learning

import android.app.Application
import com.chimei.learning.network.Network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppKotlin : Application() {

    override fun onCreate() {
        super.onCreate()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        network = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Network::class.java)
    }

    companion object {
        private lateinit var network: Network

        fun getNetwork(): Network {
            return network
        }
    }
}