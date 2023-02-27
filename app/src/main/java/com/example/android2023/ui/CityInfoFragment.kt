package com.example.android2023.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android2023.R
import com.example.android2023.databinding.FragmentCityinfoBinding

class CityInfoFragment : Fragment(R.layout.fragment_cityinfo) {

    private var binding: FragmentCityinfoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityinfoBinding.bind(view)


    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

}
