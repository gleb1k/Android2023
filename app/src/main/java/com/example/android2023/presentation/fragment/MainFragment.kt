package com.example.android2023.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android2023.App
import com.example.android2023.R
import com.example.android2023.databinding.FragmentMainBinding
import com.example.android2023.domain.usecase.GetNearCitiesUseCase
import com.example.android2023.domain.usecase.GetWeatherByNameUseCase
import com.example.android2023.presentation.MainViewModel
import com.example.android2023.presentation.recyclerview.CityAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Flowables
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private var adapter: CityAdapter? = null

    @Inject
    lateinit var getWeatherByNameUseCase: GetWeatherByNameUseCase

    @Inject
    lateinit var getNearCitiesUseCase: GetNearCitiesUseCase

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.provideFactory(
            getNearCitiesUseCase,
            getWeatherByNameUseCase
        )
    }

    private var searchDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //ВЫЗОВ INJECT ВСЕГДА ДО SUPER
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        viewModel.loadCities()

        adapter = CityAdapter(onItemClick = ::navigateToCityInfoFragment).also {
            viewModel.citiesList.observe(viewLifecycleOwner) { list ->
                it.submitList(list)
            }
            binding?.rvCity?.adapter = it
        }
        setCityEditText()
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

    private fun setCityEditText() {
        binding?.run {
            viewModel.weatherResponse.observe(viewLifecycleOwner) { weatherResponse ->
                searchDisposable = etCity.observeQuery()
                    .debounce(500L, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onNext = {
                        viewModel.onSearchClick(it)
                        if (weatherResponse != null)
                        //Навигация работает через жепу, нужно как-то проверять на изменение weatherRespons'a
                            navigateToCityInfoFragment(weatherResponse.id)
                    },
                        onError = {
                            Log.e("Search Error", it.toString())
                        })
            }
        }
    }


    private fun EditText.observeQuery() =
        Flowables.create<String>(mode = BackpressureStrategy.LATEST) { emitter ->
            addTextChangedListener {
                emitter.onNext(it.toString())
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        searchDisposable?.dispose()
        binding = null
    }
}
