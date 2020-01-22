package com.example.topmovies.di

import android.app.Application

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }
    override fun onCreate(){
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .mVPModule(MVPModule())
            .build()
    }
}