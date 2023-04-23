package com.example.android2023.domain

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.presentation.recyclerview.models.CityItem
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getWeatherById(id: Int): Single<WeatherResponse>

    fun getWeatherByName(q: String): Single<WeatherResponse>

    fun getNearCities(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Single<List<CityItem>>

}
