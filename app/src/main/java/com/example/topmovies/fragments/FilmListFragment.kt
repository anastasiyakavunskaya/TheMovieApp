package com.example.topmovies.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.topmovies.adapter.BaseAdapter
import com.example.topmovies.adapter.FilmsAdapter
import com.example.topmovies.di.App
import com.example.topmovies.mvp.contract.FilmsContract
import com.example.topmovies.mvp.presenter.FilmsPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class FilmListFragment: BaseListFragment(), FilmsContract.View{

    @Inject
    lateinit var presenter: FilmsPresenter

    var page = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.example.topmovies.R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)

        presenter.makeList(page)

        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                page++
                presenter.makeList(page)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // code
            }
        }
       getRecycler().addOnScrollListener(onScrollListener)
    }

    override fun createAdapterInstance(): BaseAdapter<*> {
        return FilmsAdapter()
    }

    override fun addFilm(filmsList: FilmsAdapter.Film) {
        viewAdapter.add(filmsList)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }


}