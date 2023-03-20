package com.example.android2023.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android2023.R
import com.example.android2023.databinding.FragmentMainBinding
import com.example.android2023.presentation.MainViewModel
import com.example.android2023.presentation.recyclerview.CityAdapter
import com.example.android2023.utils.showSnackbar

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private var adapter: CityAdapter? = null

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        //viewModel.loadCities()

        adapter = CityAdapter(onItemClick = ::navigateToCityInfoFragment).also {
            viewModel.citiesList.observe(viewLifecycleOwner) { list ->
                it.submitList(list)
            }
            binding?.rvCity?.adapter = it
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

    //ВОТ ЧТО БЫВАЕТ КОГДА ПЫТАЕШЬСЯ ДЕБАЖИТЬ АПИШКУ
    //Your account is temporary blocked due to exceeding of requests limitation of your subscription type. Please choose the proper subscription https://openweathermap.org/price
    //СПАСИБО ЗА БАН
    private fun setCitySearchView() {
        binding?.run {
            sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    try {
                        viewModel.weatherResponse.observe(viewLifecycleOwner) {
                            viewModel.onSearchClick(query ?: "")
                            if (it == null) return@observe
                            navigateToCityInfoFragment(it.id)

//                            it.fold(onSuccess = { wr ->
//                                //todo из-за того что данные долго подгружаются навигация происходит
//                                //по старому айдишнику weatherResponse
//                                //т.е нужно как-то подождать ответа, КАК?
//                                //попаболь с навигацией
//                                navigateToCityInfoFragment(wr.id)
//                                //не понял как это работает
////                            viewModel.navigateToDetails.observe(viewLifecycleOwner) { _ ->
////                                viewModel.userClicksOnButton()
////                            }
//                            },
//                                onFailure = { throwable ->
//                                    Timber.d(throwable)
//                                    activity!!.findViewById<View>(android.R.id.content)
//                                        .showSnackbar("Город $query не найден")
//                                })
                        }

                    } catch (ex: Exception) {
                        activity!!.findViewById<View>(android.R.id.content)
                            .showSnackbar("Город $query не найден")
                        Log.e("ex", ex.message.toString())
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
