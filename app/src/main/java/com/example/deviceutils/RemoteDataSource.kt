package com.example.deviceutils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource{

    companion object
    {

        var _url : String = ""

        @JvmName("getUrl1")
        fun getUrl(): String {
            return _url
        }

        @JvmName("setUrl1")
        fun setUrl(url: String) {
            _url = url
        }
    }

    fun <Api> buildApi(
        api: Class<Api>,
        token: String,
        timeOut: Long = 30
    ): Api {

        var u = getUrl()

        return Retrofit.Builder()
            .baseUrl(u)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "$token")
                        }.build())
                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.callTimeout(timeOut, TimeUnit.MINUTES)
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(api)
    }

}