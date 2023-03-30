package com.example.android2023.domain.usecase

import com.example.android2023.domain.WeatherRepository
import com.example.android2023.presentation.recyclerview.models.CityItem

class GetNearCitiesUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        count: Int
    ): List<CityItem> = weatherRepository.getNearCities(latitude, longitude, count)
}
