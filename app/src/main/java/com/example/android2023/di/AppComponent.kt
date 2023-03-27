package com.example.android2023.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.android2023.presentation.MainActivity
import com.example.android2023.presentation.fragment.CityInfoFragment
import com.example.android2023.presentation.fragment.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, WeatherModule::class])
interface AppComponent {

    fun inject(fragment : MainFragment)
    fun inject(fragment : CityInfoFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

}
