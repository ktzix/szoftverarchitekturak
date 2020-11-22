package com.example.fastfashion

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.fastfashion.adapter.ClothesAdapter
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.network.FashionInteractor

import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() ,ClothesAdapter.ItemSelectedListener{
    private val fashionInteractor=FashionInteractor()
    private lateinit var items : ArrayList<FashionItem>

    override fun onItemSelected(id: Int) {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private var adapter: ClothesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        loadItems()

    }

    private fun loadItems(){
        fashionInteractor.getFashionItems(this::onLoadSuccess, this::onLoadError)
    }

    private fun onLoadSuccess(list: List<FashionItem>?){
        if(list!=null){
            items=list as ArrayList<FashionItem>
            rvList.layoutManager=LinearLayoutManager(applicationContext)
            adapter= ClothesAdapter(applicationContext, this, items)
            rvList.adapter= adapter
        }
        else onLoadError(Exception())
    }

    private fun onLoadError(e: Throwable){
        e.printStackTrace()
        Toast.makeText(applicationContext, "unable load fashionitems", Toast.LENGTH_LONG ).show()
    }


}
