package com.example.android2023.di

import com.example.android2023.data.WeatherRepositoryImpl
import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.domain.WeatherRepository
import com.example.android2023.domain.usecase.GetNearCitiesUseCase
import com.example.android2023.domain.usecase.GetWeatherByIdUseCase
import com.example.android2023.domain.usecase.GetWeatherByNameUseCase
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)

    @Provides
    fun provideGetWeatherByIdUseCase(
        weatherRepository: WeatherRepository
    ): GetWeatherByIdUseCase = GetWeatherByIdUseCase(weatherRepository)

    @Provides
    fun provideGetWeatherByNameUseCase(
        weatherRepository: WeatherRepository
    ): GetWeatherByNameUseCase = GetWeatherByNameUseCase(weatherRepository)

    @Provides
    fun provideGetNearCitiesUseCase(
        weatherRepository: WeatherRepository
    ): GetNearCitiesUseCase = GetNearCitiesUseCase(weatherRepository)

}
