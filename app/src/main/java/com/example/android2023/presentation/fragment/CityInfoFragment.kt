package com.example.android2023.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.android2023.App
import com.example.android2023.R
import com.example.android2023.databinding.FragmentCityinfoBinding
import com.example.android2023.domain.usecase.GetWeatherByIdUseCase
import com.example.android2023.presentation.CityInfoViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

class CityInfoFragment : Fragment(R.layout.fragment_cityinfo) {

    private var binding: FragmentCityinfoBinding? = null
    private var cityId: Int = 0

    @Inject
    lateinit var getWeatherByIdUseCase: GetWeatherByIdUseCase

    private val viewModel: CityInfoViewModel by viewModels {
        CityInfoViewModel.provideFactory(
            getWeatherByIdUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //ВЫЗОВ INJECT ВСЕГДА ДО SUPER
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityinfoBinding.bind(view)
        arguments?.getString(CITY_ID_TAG).also {
            if (it != null) {
                cityId = it.toInt()
                viewModel.loadWeather(cityId)
            }
        }
        setView()
    }

    private fun setView() {
        viewModel.weatherResponse.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            binding?.run {
                tvCityName.text = it.name
                tvTemp.text = "${it.main.temp.roundToInt()}°"
                tvTempFeels.text = "Ощущается как ${it.main.feelsLike.roundToInt()}°"
                tvSun.text =
                    "Восход: ${viewModel.getTime(it.sys.sunrise)}\nЗакат: ${viewModel.getTime(it.sys.sunset)}"
                tvHumidity.text = "Влажность: ${it.main.humidity}%"
                tvCloud.text = it.weather[0].description
                tvWind.text = "Направление ветра: ${viewModel.windConverter(it.wind.deg)}"
                tvPressure.text = "${viewModel.pressureConverter(it.main.pressure)}мм.рт.ст."
                ivIcon.load("https://openweathermap.org/img/w/${it.weather[0].icon}.png")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val CITY_INFO_FRAGMENT_TAG = "CITY_INFO_FRAGMENT_TAG"
        const val CITY_ID_TAG = "CITY_ID_TAG"

        fun getInstance(message: String) =
            CityInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(CITY_ID_TAG, message)
                }
            }
    }

}
