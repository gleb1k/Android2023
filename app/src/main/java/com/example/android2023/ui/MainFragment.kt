package com.example.android2023.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android2023.R
import com.example.android2023.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

}
