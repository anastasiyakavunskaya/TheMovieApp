package com.example.topmovies.adapter

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseAdapter <VH : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter <VH>(){

    //список элементов списка
    var items: ArrayList<Any> = ArrayList()

    override fun getItemCount():Int{
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int){
        holder.bind(getItem(position))
    }

    fun getItem(position: Int):Any{
        return items[position]
    }
    fun add(newItem: Any) {
        items.add(newItem)
    }
    abstract class BaseViewHolder(protected val view: View): RecyclerView.ViewHolder(view){
        abstract fun bind(item: Any)
    }
}