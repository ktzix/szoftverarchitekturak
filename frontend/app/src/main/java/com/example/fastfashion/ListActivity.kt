package com.example.fastfashion

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.example.fastfashion.adapter.ClothesAdapter

import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() ,ClothesAdapter.ItemSelectedListener{
    override fun onItemSelected() {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
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

        rvList.layoutManager=LinearLayoutManager(applicationContext)
        adapter= ClothesAdapter(applicationContext, this)
        rvList.adapter= adapter
    }

}
