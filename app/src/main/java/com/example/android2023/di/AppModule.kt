package com.example.android2023.di

import android.content.Context
import com.example.android2023.utils.AndroidResourceProvider
import com.example.android2023.utils.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideAndroidResourceProvider(
        context: Context
    ): ResourceProvider = AndroidResourceProvider(context)

}
