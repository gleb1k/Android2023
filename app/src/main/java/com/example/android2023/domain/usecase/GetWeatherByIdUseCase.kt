package com.example.android2023.domain.usecase

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository

class GetWeatherByIdUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        id: Int
    ): WeatherResponse = weatherRepository.getWeatherById(id)
}
