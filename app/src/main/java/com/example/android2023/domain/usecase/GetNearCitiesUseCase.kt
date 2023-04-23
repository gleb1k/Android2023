package com.example.android2023.domain.usecase

import com.example.android2023.domain.WeatherRepository
import com.example.android2023.presentation.recyclerview.models.CityItem
import io.reactivex.rxjava3.core.Single

class GetNearCitiesUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double,
        count: Int
    ): Single<List<CityItem>> =
        weatherRepository.getNearCities(latitude, longitude, count)
}
