package com.example.android2023.di

import com.example.android2023.data.WeatherRepositoryImpl
import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent


@Module
//todo почему когда тут указываю ActivityComponent, то проект не биндится и выкидывает ошибку
//todo com.example.android2023.domain.WeatherRepository cannot be provided without an @Provides-annotated method.
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    fun provideWeatherRepositoryImpl(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)

}
