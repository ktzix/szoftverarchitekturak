package com.example.fastfashion.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fastfashion.R

class ClothesAdapter(private val context: Context,
    private val listener: ItemSelectedListener
): RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    interface ItemSelectedListener{
        fun onItemSelected()

    }
    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.addListener(listener)
    }

    override fun getItemCount()=5

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClothesAdapter.ViewHolder {
        val view = layoutInflater.inflate(R.layout.listitem, p0, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun addListener(listener: ItemSelectedListener){
            itemView.setOnClickListener {
                listener.onItemSelected()
            }
        }
    }
}