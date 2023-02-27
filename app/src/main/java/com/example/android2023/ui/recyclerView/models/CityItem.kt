package com.example.android2023.ui.recyclerView.models

import com.example.android2023.R

data class CityItem(
    val id: Int,
    val name: String,
    val temp: Int,
    val url: String
) {

    fun tempColor(): Int {
        return when (this.temp) {
            in -1000..-30 -> R.color.dark_blue
            in -29..-1 -> R.color.blue
            in 0..5 -> R.color.green
            in 6..20 -> R.color.yellow
            in 21..1000 -> R.color.orange
            else -> R.color.black
        }
    }
}
