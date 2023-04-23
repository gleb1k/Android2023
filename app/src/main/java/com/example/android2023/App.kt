package com.example.android2023

import android.app.Application
import com.example.android2023.di.AppComponent
import com.example.android2023.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }

    companion object {

        lateinit var appComponent: AppComponent
    }
}
