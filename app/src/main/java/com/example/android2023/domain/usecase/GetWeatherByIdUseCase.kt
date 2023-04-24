package com.example.android2023.domain.usecase

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository
import io.reactivex.rxjava3.core.Single

class GetWeatherByIdUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        id: Int
    ): Single<WeatherResponse> = weatherRepository.getWeatherById(id)
}
