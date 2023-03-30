package com.example.android2023.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android2023.presentation.recyclerview.models.CityItem

class CityAdapter(
    private val onItemClick: (Int) -> Unit,
) : ListAdapter<CityItem, CityHolder>(object : DiffUtil.ItemCallback<CityItem>() {
    override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean =
        oldItem == newItem

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder.create(parent, onItemClick)


    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.onBind(currentList[position])
    }

}
