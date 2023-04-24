package com.example.android2023.data.datasource.remote

import com.example.android2023.data.datasource.remote.response.CitiesResponse
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherByName(
        @Query("q") city: String,
    ): Single<WeatherResponse>

    @GET("weather")
    fun getWeatherById(
        @Query("id") cityId: Int,
    ): Single<WeatherResponse>

    @GET("find")
    fun getNearCities(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ): Single<CitiesResponse>
}
