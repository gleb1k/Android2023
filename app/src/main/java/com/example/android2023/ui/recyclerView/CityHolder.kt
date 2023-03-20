package com.example.android2023.ui.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android2023.databinding.ItemCityBinding
import com.example.android2023.ui.recyclerView.models.CityItem
import timber.log.Timber

class CityHolder(
    private val binding: ItemCityBinding,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(city: CityItem) {
        binding.run {
            tvCityName.text = city.name
            tvTemp.text = "${city.temp}°"
//            tvTemp.setTextColor(activity.getRecources.getColor(R.color.orange))
            tvTemp.setTextColor(binding.root.context.resources.getColor(city.tempColor()))
            ivIcon.load(city.url)


            //КАК СДЕЛАТЬ ВЕСЬ ITEM КЛИКАБЕЛЬНЫМ? работает через раз
            root.setOnClickListener {
                onItemClick(city.id)
                Timber.log(1,"click")
                Log.e("ddd","click")
            }
            //костыль
            tvTemp.setOnClickListener {
                onItemClick(city.id)
            }
            tvCityName.setOnClickListener {
                onItemClick(city.id)
            }
            ivIcon.setOnClickListener {
                onItemClick(city.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (Int) -> Unit,
        ): CityHolder = CityHolder(
            binding = ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick = onItemClick
        )
    }
}
