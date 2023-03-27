package com.example.android2023.di


import com.example.android2023.data.Constants
import com.example.android2023.data.WeatherRepositoryImpl
import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.data.interceptor.ApiKeyInterceptor
import com.example.android2023.data.interceptor.LanguageInterceptor
import com.example.android2023.data.interceptor.MetricInterceptor
import com.example.android2023.domain.usecase.GetNearCitiesUseCase
import com.example.android2023.domain.usecase.GetWeatherByIdUseCase
import com.example.android2023.domain.usecase.GetWeatherByNameUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//object DataContainer {
//
//    private val httpClient by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(ApiKeyInterceptor())
//            .addInterceptor(MetricInterceptor())
//            .addInterceptor(LanguageInterceptor())
//            .build()
//    }
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(Constants.BASE_URL)
//            .build()
//    }
//
//    private val weatherApi = retrofit.create(WeatherApi::class.java)
//
//    private val weatherRepository = WeatherRepositoryImpl(weatherApi)
//
//    val getWeatherByIdUseCase: GetWeatherByIdUseCase
//        get() = GetWeatherByIdUseCase(weatherRepository)
//
//    val getWeatherByNameUseCase: GetWeatherByNameUseCase
//        get() = GetWeatherByNameUseCase(weatherRepository)
//
//    val getNearCitiesUseCase: GetNearCitiesUseCase
//        get() = GetNearCitiesUseCase(weatherRepository)
//
//
//}
