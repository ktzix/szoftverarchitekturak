package com.example.fastfashion.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fastfashion.R
import com.example.fastfashion.model.FashionItem
import kotlinx.android.synthetic.main.activity_upload.view.*
import kotlinx.android.synthetic.main.listitem.view.*

class ClothesAdapter(private val context: Context,
    private val listener: ItemSelectedListener,
                     private val list: ArrayList<FashionItem>?
): RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    interface ItemSelectedListener{
        fun onItemSelected(id: Int)
        fun onItemDeleted(item: FashionItem)

    }
    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.addListener(listener, list!![p1].id, list!![p1])
        holder.tvCategory.text=list!![p1].type
        holder.tvBought.text=list!![p1].date


    }

    override fun getItemCount()=list!!.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClothesAdapter.ViewHolder {
        val view = layoutInflater.inflate(R.layout.listitem, p0, false)
        return ViewHolder(view)
    }

    fun deleteItem(item: FashionItem){
        val pos= list!!.indexOf(item)
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategory : TextView=view.tvCat
        val tvBought: TextView  = view.tvBought
        val deleteBtn: ImageView=view.ivDelete


        fun addListener(listener: ItemSelectedListener,id: Int, item: FashionItem){
            itemView.setOnClickListener {
                listener.onItemSelected(id)
            }
            deleteBtn.setOnClickListener {
                listener.onItemDeleted(item)
            }
        }
    }
}