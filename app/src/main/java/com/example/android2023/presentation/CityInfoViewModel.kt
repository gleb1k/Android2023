package com.example.android2023.presentation

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.usecase.GetWeatherByIdUseCase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class CityInfoViewModel(
    private val getWeatherByIdUseCase: GetWeatherByIdUseCase
) : ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse?>(null)
    val weatherResponse: LiveData<WeatherResponse?>
        get() = _weatherResponse

    fun loadWeather(id: Int) {
        viewModelScope.launch {
            getWeatherByIdUseCase(id).also {
                _weatherResponse.value = it
            }
        }
    }

    fun unixToDate(unix: Int): Date {
        return Date(unix.toLong() * 1000)
    }

    fun getTime(unix: Int): String {
        val date = unixToDate(unix)
        return "${date.hours}:${date.minutes} GMT"
    }

    //из Гекто-Паскаль в миллиметры ртутного столба
    fun pressureConverter(pressure: Int) = (pressure / 1.33).roundToInt()

    fun windConverter(deg: Int): String {
        return when (deg) {
            in 0..10 -> "C"
            in 350..360 -> "C"
            in 260..280 -> "З"
            in 170..190 -> "Ю"
            in 80..100 -> "В"
            in 281..349 -> "СЗ"
            in 11..79 -> "CВ"
            in 190..259 -> "ЮЗ"
            in 101..169 -> "ЮВ"
            else -> "ex"
        }
    }

    companion object {
        fun provideFactory(
            getWeatherByIdUseCase: GetWeatherByIdUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CityInfoViewModel(
                    getWeatherByIdUseCase
                )
            }
        }

    }

}
