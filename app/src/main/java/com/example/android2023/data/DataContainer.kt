package com.example.android2023.data


import com.example.android2023.data.interceptor.ApiKeyInterceptor
import com.example.android2023.data.interceptor.LanguageInterceptor
import com.example.android2023.data.interceptor.MetricInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//не работает, хз почему todo
//private const val BASE_URL = BuildConfig.API_BASE_URL

object DataContainer {

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(MetricInterceptor())
            .addInterceptor(LanguageInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val weatherApi = retrofit.create(WeatherApi::class.java)
}
