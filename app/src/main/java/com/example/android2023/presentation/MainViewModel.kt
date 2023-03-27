package com.example.android2023.presentation

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android2023.data.Constants
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.usecase.GetNearCitiesUseCase
import com.example.android2023.domain.usecase.GetWeatherByNameUseCase
import com.example.android2023.presentation.recyclerview.models.CityItem
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNearCitiesUseCase: GetNearCitiesUseCase,
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase
) : ViewModel() {
    //todo костыль.  как делать правильную навигацию ??????
//    val navigateToDetails = SingleLiveEvent<Any>()
//    fun userClicksOnButton() {
//        navigateToDetails.call()
//    }

//    private val _weatherResponseResult = MutableLiveData<Result<WeatherResponse>>(null)
//    val weatherResponseResult: LiveData<Result<WeatherResponse>>
//        get() = _weatherResponseResult

    private val _weatherResponse = MutableLiveData<WeatherResponse?>(null)
    val weatherResponse: LiveData<WeatherResponse?>
        get() = _weatherResponse

    private val _citiesList = MutableLiveData<List<CityItem>?>(null)
    val citiesList: LiveData<List<CityItem>?>
        get() = _citiesList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    fun onSearchClick(cityName: String) {
        loadWeather(cityName)
    }

    private fun loadWeather(cityName: String) {
        viewModelScope.launch {
            getWeatherByNameUseCase(cityName).also {
                _weatherResponse.value = it
            }
        }
    }


//    private fun loadWeatherResult(cityName: String) {
//        viewModelScope.launch {
//            try {
//                getWeatherByNameUseCase(cityName).also {
//                    _weatherResponse.value = Result.success(it)
//                }
//            } catch (ex: Throwable) {
//                _weatherResponse.value = Result.failure(ex)
//                _error.value = ex
//            }
//        }
//    }

    fun loadCities(
        latitude: Double = Constants.KAZAN_LATITUDE,
        longitude: Double = Constants.KAZAN_LONGITUDE,
        count: Int = 12
    ) {
        viewModelScope.launch {
            getNearCitiesUseCase(latitude, longitude, count).also {
                _citiesList.value = it
            }
        }
    }

    companion object {
        //Костыль чтобы заинджектить вьюмодельку
        fun provideFactory(
            getNearCitiesUseCase: GetNearCitiesUseCase,
            getWeatherByNameUseCase: GetWeatherByNameUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    getNearCitiesUseCase,
                    getWeatherByNameUseCase
                )
            }
        }
    }
}
