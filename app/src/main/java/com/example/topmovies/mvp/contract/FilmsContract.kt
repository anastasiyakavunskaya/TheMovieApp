package com.example.topmovies.mvp.contract

import com.example.topmovies.adapter.FilmsAdapter

class FilmsContract {
    interface View: BaseContract.View{
        fun addFilm(filmsList: FilmsAdapter.Film)
        fun notifyAdapter()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error:String?)
        fun refresh()
    }

    abstract class Presenter: BaseContract.Presenter<View>() {
        abstract fun makeList(page: Int)
        abstract fun refreshList(page: Int)
    }
}