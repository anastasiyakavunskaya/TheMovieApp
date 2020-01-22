package com.example.topmovies.mvp.presenter

import com.example.topmovies.adapter.FilmsAdapter
import com.example.topmovies.di.App
import com.example.topmovies.mvp.contract.FilmsContract
import com.example.topmovies.rest.TheMovieDBApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmsPresenter: FilmsContract.Presenter() {

    @Inject
    lateinit var filmsApi: TheMovieDBApi

    init {
        App.appComponent.inject(this)
    }

    override fun refreshList(page: Int) {
        view.refresh()
        makeList(page)
    }

    override fun makeList(page:Int) {

        if (page == 1) view.showProgress()
            subscribe(filmsApi.getInfoByYear(2019,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    for (i in 0 until it.results.size) {
                        if(it.results[i].vote_average>7){
                        view.addFilm(
                            FilmsAdapter.Film(
                                it!!.results[i].id!!,
                                it.results[i].title,
                                it.results[i].release_date,
                                it.results[i].overview,
                                it.results[i].poster_path,
                                it.results[i].vote_average
                            )
                        )
                    }}
                }
                .doOnComplete {
                    view.hideProgress()
                }
                .subscribe({
                    view.hideProgress()
                    view.notifyAdapter()
                }, {
                        view.showErrorMessage(it.message)
                        view.hideProgress()
                        it.printStackTrace()
                    }))
    }
}