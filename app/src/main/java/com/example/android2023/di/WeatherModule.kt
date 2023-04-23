package com.example.android2023.di

import com.example.android2023.data.WeatherRepositoryImpl
import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class WeatherModule {

    @Provides
    fun provideWeatherRepositoryImpl(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)

}
