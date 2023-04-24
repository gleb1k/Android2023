package com.example.android2023.data

import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository
import com.example.android2023.presentation.recyclerview.models.CityItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override fun getWeatherById(id: Int): Single<WeatherResponse> =
        api.getWeatherById(id)
            .subscribeOn(Schedulers.io())

    override fun getWeatherByName(q: String): Single<WeatherResponse> =
        api.getWeatherByName(q)
            .subscribeOn(Schedulers.io())

    override fun getNearCities(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Single<List<CityItem>> = api.getNearCities(latitude, longitude, count)
        .observeOn(Schedulers.computation())
        .map {
            it.list.map { wr ->
                CityItem(
                    wr.id, wr.name, wr.main.temp.roundToInt(),
                    "https://openweathermap.org/img/w/${wr.weather[0].icon}.png"
                )
            }
        }
        .subscribeOn(Schedulers.io())
}
