package com.example.topmovies.di

import com.example.topmovies.MainActivity
import com.example.topmovies.fragments.FilmListFragment
import com.example.topmovies.mvp.presenter.FilmsPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RestModule::class, MVPModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(presenter: FilmsPresenter)
    fun inject(fragment: FilmListFragment)
}