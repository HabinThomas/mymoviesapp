package com.example.mysamplemovieapp.network


import com.example.mysamplemovieapp.BuildConfig
import com.example.mysamplemovieapp.MainActivity
import com.example.mysamplemovieapp.helper.Constants
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ApiInstance {
    companion object {
        private const val cacheSize = 10 * 1024 * 1024 // 10 MB
        private val httpCacheDirectory = File(MainActivity.cachePath, "http-cache")
        private val cache = Cache(httpCacheDirectory, cacheSize.toLong())


        private val networkCacheInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

        private val retrofitClient: Retrofit.Builder by lazy {
            val levelType: HttpLoggingInterceptor.Level =
                if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                    HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            val logging = HttpLoggingInterceptor()
            logging.setLevel(levelType)

            val okhttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)

            Retrofit.Builder()
                .baseUrl(Constants.APIBaseUrl)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())

        }

        val apiInterface: ApiInterface by lazy {
            retrofitClient
                .build()
                .create(ApiInterface::class.java)

        }

    }
}