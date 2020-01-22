package com.example.topmovies.di

import com.example.topmovies.mvp.presenter.FilmsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MVPModule {

    @Provides
    @Singleton
    fun provideFilmsPresenter(): FilmsPresenter = FilmsPresenter()
}