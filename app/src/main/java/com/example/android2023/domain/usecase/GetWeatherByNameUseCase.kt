package com.example.android2023.domain.usecase

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository
import javax.inject.Inject

class GetWeatherByNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        query: String
    ): WeatherResponse = weatherRepository.getWeatherByName(query)
}
