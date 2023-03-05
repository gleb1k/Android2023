package com.example.android2023.data.response.citiesResponse

import com.example.android2023.data.response.weatherResponse.WeatherResponse
import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<WeatherResponse>,
    @SerializedName("message")
    val message: String
)
