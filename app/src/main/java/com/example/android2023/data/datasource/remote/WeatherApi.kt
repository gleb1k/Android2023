package com.example.android2023.data.datasource.remote

import com.example.android2023.data.datasource.remote.response.CitiesResponse
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherByName(
        @Query("q") city: String,
    ) : WeatherResponse

    @GET("weather")
    suspend fun getWeatherById(
        @Query("id") cityId : Int,
    ) : WeatherResponse

    //todo вынести в отдельный класс CitiesApi?
    //я подумал что ради одного метода создавать целый класс не стоит,
    //да и это не отдельная апишка, а все таже, просто возвращает другой объект
    @GET("find")
    suspend fun getNearCities(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ): CitiesResponse
}
