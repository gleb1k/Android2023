package com.example.android2023.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.android2023.R
import com.example.android2023.data.DataContainer
import com.example.android2023.databinding.FragmentCityinfoBinding
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class CityInfoFragment : Fragment(R.layout.fragment_cityinfo) {

    private var binding: FragmentCityinfoBinding? = null
    private var cityId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityinfoBinding.bind(view)

        lifecycleScope.launch {
            arguments?.getString(CITY_ID_TAG).also {
                if (it != null) {
                    cityId = it.toInt()
                }
            }
            setView(binding!!) //говнокод
        }
    }

    private suspend fun setView(binding: FragmentCityinfoBinding) {
        binding.run {
            DataContainer.weatherApi.getWeather(cityId = cityId).also {
                tvCityName.text = it.name
                tvTemp.text = "${it.main.temp.roundToInt()}°"
                tvTempFeels.text = "Ощущается как ${it.main.feelsLike.roundToInt()}°"
                tvSun.text =
                    "Восход: ${getTime(it.sys.sunrise)}\nЗакат: ${getTime(it.sys.sunset)}"
                tvHumidity.text = "Влажность: ${it.main.humidity}%"
                tvCloud.text = it.weather[0].description
                tvWind.text = "Направление ветра: ${windConverter(it.wind.deg)}"
                tvPressure.text = "${pressureConverter(it.main.pressure)}мм.рт.ст."
                ivIcon.load("https://openweathermap.org/img/w/${it.weather[0].icon}.png")
            }
        }
    }

    private fun unixToDate(unix: Int): Date {
        return Date(unix.toLong() * 1000)
    }

    private fun getTime(unix: Int): String {
        val date = unixToDate(unix)
        return "${date.hours}:${date.minutes} GMT"
    }

    //из Гекто-Паскаль в миллиметры ртутного столба
    private fun pressureConverter(pressure: Int) = (pressure / 1.33).roundToInt()

    private fun windConverter(deg: Int): String {
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
