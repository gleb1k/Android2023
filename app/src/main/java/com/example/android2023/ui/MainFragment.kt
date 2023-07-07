package com.example.android2023.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android2023.R
import com.example.android2023.data.DataContainer
import com.example.android2023.databinding.FragmentMainBinding
import com.example.android2023.ui.recyclerView.CityAdapter
import com.example.android2023.ui.recyclerView.CityRepository
import com.example.android2023.utils.showSnackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private var adapter: CityAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        adapter = CityAdapter(onItemClick = ::navigateToCityInfoFragment).also {
            lifecycleScope.launch {
                CityRepository.generateList()
                it.submitList(CityRepository.cityList)
                binding?.rvCity?.adapter = it
            }
        }
        setCitySearchView()
    }

    private fun navigateToCityInfoFragment(id: Int) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                CityInfoFragment.getInstance(id.toString()),
                CityInfoFragment.CITY_INFO_FRAGMENT_TAG,
            )
            .addToBackStack("")
            .commit()
    }

    private fun setCitySearchView() {
        binding?.run {
            sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    lifecycleScope.launch {
                        try {
                            DataContainer.weatherApi.getWeather(query ?: "").also {
                                navigateToCityInfoFragment(it.id)
                            }
                        } catch (ex: Exception) {
                            activity!!.findViewById<View>(android.R.id.content)
                                .showSnackbar("Город $query не найден")
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
