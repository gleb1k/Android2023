package com.example.android2023.domain.usecase

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository
import io.reactivex.rxjava3.core.Single

class GetWeatherByNameUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        query: String
    ): Single<WeatherResponse> = weatherRepository.getWeatherByName(query)
}
