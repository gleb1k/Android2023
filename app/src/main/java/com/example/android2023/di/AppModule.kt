package com.example.android2023.di

import android.content.Context
import com.example.android2023.utils.AndroidResourceProvider
import com.example.android2023.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Provides
    fun provideAndroidResourceProvider(
        context: Context
    ): ResourceProvider = AndroidResourceProvider(context)

}
