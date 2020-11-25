package com.example.fastfashion

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.fastfashion.adapter.ClothesAdapter
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.network.FashionInteractor

import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() ,ClothesAdapter.ItemSelectedListener{
    private val fashionInteractor=FashionInteractor()
    private lateinit var items : ArrayList<FashionItem>

    private var adapter: ClothesAdapter? = null
    private lateinit var itemToDelete: FashionItem

    private var userId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Ruhadarabok"
        userId=intent.getIntExtra("id",0)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        btnAll.setOnClickListener {
            loadItems()
        }

        btnSearch.setOnClickListener {
            searchItems()
        }

        initSpinner()
        loadItems()

    }

    private fun initSpinner(){
        val list=ArrayList<String>()
        list.add("Típus")
        list.add("Stílus")
        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=aa
    }

    private fun loadItems(){
        fashionInteractor.getFashionItems(userId, this::onLoadSuccess, this::onLoadError)
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
        Toast.makeText(applicationContext, "Nem sikerült betölteni a ruhadarabokat!", Toast.LENGTH_LONG ).show()
    }


    private fun searchItems(){
        if(spinner.selectedItem.toString()=="Típus"){
            fashionInteractor.getFashionItemsByType(userId, etSearchValue.text.toString(), this::onLoadSuccess, this::onLoadError)
        }
        else if(spinner.selectedItem.toString()=="Stílus"){
            fashionInteractor.getFashionItemsByStyle(userId, etSearchValue.text.toString(), this::onLoadSuccess, this::onLoadError)
        }
    }
    override fun onItemSelected(id: Int) {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }

    override fun onItemDeleted(item: FashionItem) {
        itemToDelete=item
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztosan törölni szeretnéd ezt a fájlt? ")
        builder.setPositiveButton("IGEN", DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            fashionInteractor.deleteFashionItem(item.id,this::onDeleteSuccess, this::onDeleteError)
        })
        builder.setNegativeButton("NEM", null)
        builder.show()
    }

    private fun onDeleteSuccess(item: Void?){
            adapter!!.deleteItem(itemToDelete)
            Toast.makeText(applicationContext, "Sikeres törlés!", Toast.LENGTH_LONG ).show()

    }

    private fun onDeleteError(e: Throwable){
        e.printStackTrace()
        Toast.makeText(applicationContext, "Sikertelen törlés!", Toast.LENGTH_LONG ).show()
    }



}
