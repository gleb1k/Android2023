package com.example.android2023.ui.recyclerView

import com.example.android2023.data.DataContainer
import com.example.android2023.data.response.weatherResponse.WeatherResponse
import com.example.android2023.ui.recyclerView.models.CityItem
import kotlin.math.roundToInt

object CityRepository {


    var cityList: ArrayList<CityItem>? = null

    private suspend fun findCities(
        lat: Double = 55.7887,
        lon: Double = 49.1221,
        count: Int = 10
    ): ArrayList<WeatherResponse> {
        return ArrayList(DataContainer.weatherApi.getNearCities(lat, lon, count).list)
    }

    suspend fun generateList() {
        val temp = findCities().map {
            CityItem(
                it.id, it.name, it.main.temp.roundToInt(),
                "https://openweathermap.org/img/w/${it.weather[0].icon}.png"
            )
        }
        cityList = ArrayList(temp)
    }
}


