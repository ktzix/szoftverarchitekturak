package com.example.fastfashion.adapter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fastfashion.R
import com.example.fastfashion.model.FashionItem
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_upload.view.*
import kotlinx.android.synthetic.main.listitem.view.*
import java.io.File
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

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
        try{
            if(list!![p1].uri != ""){
                val f2 = File(list!![p1].uri)
                val uri = Uri.fromFile(f2)
                holder.imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(context.contentResolver, uri))
            }
        }
        catch(e: Exception){
            e.printStackTrace()
        }

        val dates = SimpleDateFormat("yyyy.MM.dd.")
        val date1 = dates.parse(Calendar.getInstance().get(Calendar.YEAR).toString()+"."+(Calendar.getInstance().get(Calendar.MONTH)+1).toString()+"."+Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()+".")
        val date2 = dates.parse(list!![p1].date)
        val difference: Long = abs(date1.time - date2.time)
        val differenceDates = difference / (24 * 60 * 60 * 1000)
        if(differenceDates>14) holder.itemView.setBackgroundColor(Color.RED)
        else if(differenceDates>7) holder.itemView.setBackgroundColor(Color.GRAY)
        else holder.itemView.setBackgroundColor(Color.GREEN)



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
        val imageView: ImageView = view.ivDetails


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