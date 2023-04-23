package com.example.android2023.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android2023.data.Constants
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.usecase.GetNearCitiesUseCase
import com.example.android2023.domain.usecase.GetWeatherByNameUseCase
import com.example.android2023.presentation.recyclerview.models.CityItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(
    private val getNearCitiesUseCase: GetNearCitiesUseCase,
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase
) : ViewModel() {
    //todo костыль.  как делать правильную навигацию ??????
//    val navigateToDetails = SingleLiveEvent<Any>()
//    fun userClicksOnButton() {
//        navigateToDetails.call()
//    }

    private val _weatherResponse = MutableLiveData<WeatherResponse?>(null)
    val weatherResponse: LiveData<WeatherResponse?>
        get() = _weatherResponse

    private val _citiesList = MutableLiveData<List<CityItem>?>(null)
    val citiesList: LiveData<List<CityItem>?>
        get() = _citiesList

    private var disposable: CompositeDisposable = CompositeDisposable()

    fun onSearchClick(cityName: String) {
        loadWeather(cityName)
    }

    private fun loadWeather(cityName: String) {
        disposable += getWeatherByNameUseCase(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                _weatherResponse.value = it
            }, onError = {

            })
    }


    fun loadCities(
        latitude: Double = Constants.KAZAN_LATITUDE,
        longitude: Double = Constants.KAZAN_LONGITUDE,
        count: Int = 12
    ) {
        disposable += getNearCitiesUseCase(latitude, longitude, count)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                _citiesList.value = it
            }, onError = {

            })
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
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
