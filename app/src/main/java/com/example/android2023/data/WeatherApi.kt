package com.example.android2023.data

import com.example.android2023.data.response.citiesResponse.CitiesResponse
import com.example.android2023.data.response.weatherResponse.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
    ) : WeatherResponse

    @GET("weather")
    suspend fun getWeather(
        @Query("id") cityId : Int,
    ) : WeatherResponse

    @GET("find")
    suspend fun getNearCities(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ): CitiesResponse
}
