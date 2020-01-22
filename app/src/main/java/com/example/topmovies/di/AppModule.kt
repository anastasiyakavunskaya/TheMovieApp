package com.example.topmovies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app:App) {

    @Provides
    @Singleton
    fun provideContex(): Context = app
}