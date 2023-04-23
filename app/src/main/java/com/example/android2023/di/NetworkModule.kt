package com.example.android2023.di

import com.example.android2023.data.Constants
import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.data.interceptor.ApiKeyInterceptor
import com.example.android2023.data.interceptor.LanguageInterceptor
import com.example.android2023.data.interceptor.MetricInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun provideHttpClient(
        @Named("api_key") apiKeyInterceptor: Interceptor,
        @Named("metric") metricInterceptor: Interceptor,
        @Named("language") languageInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(metricInterceptor)
        .addInterceptor(languageInterceptor)
        .build()

    @Provides
    @Named("api_key")
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    @Named("metric")
    fun provideMetricInterceptor(): Interceptor = MetricInterceptor()

    @Provides
    @Named("language")
    fun provideLanguageInterceptor(): Interceptor = LanguageInterceptor()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideWeatherApi(
        retrofit: Retrofit
    ): WeatherApi = retrofit.create(WeatherApi::class.java)
}
