package com.example.lieberson.developersblog.domain.services

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteApiService {
    private val URL_BASE = "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179/"
    private var retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)

    private fun getRewriteCacheControlInterceptor(isNetworkAvailable: Boolean): Interceptor {
        return Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())

            if (isNetworkAvailable) {
                val maxAge = 60 // read from cache for 1 minute
                originalResponse?.newBuilder()
                        ?.header("Cache-Control", "public, max-age=" + maxAge)
                        ?.build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build()
            }
        }
    }

    companion object {

        private val remoteApiService: RemoteApiService by lazy {
            RemoteApiService()
        }

        fun getInstance(): RemoteApiService {
            return remoteApiService
        }
    }
}