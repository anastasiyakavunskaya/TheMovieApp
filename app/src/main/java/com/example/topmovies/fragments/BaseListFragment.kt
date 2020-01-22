package com.example.topmovies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.topmovies.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_film_list.*

abstract class BaseListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        viewAdapter = createAdapterInstance()


        recyclerView = list.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun getRecycler():RecyclerView{
        return recyclerView
    }

    abstract fun createAdapterInstance(): BaseAdapter<*>
}