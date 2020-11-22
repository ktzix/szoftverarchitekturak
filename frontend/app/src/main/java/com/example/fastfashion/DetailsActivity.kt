package com.example.fastfashion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.network.FashionInteractor
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var id=0
    private val fashionInteractor= FashionInteractor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        id= intent.getIntExtra("id", 0)

        fashionInteractor.getFashionItemById(id, this::onLoadSuccess, this::onLoadError)


    }

    private fun onLoadError(e: Throwable){
        Toast.makeText(applicationContext, "Unable to load FashionItem", Toast.LENGTH_LONG).show()
        textViewCategory.text="Ide jön a ruhadarab kategóriája"
        textViewAge.text="Ide jön a ruhadarab életkora"
        textViewDate.text="Ide jön a ruhadarab megvásárlásának ideje"
        textViewDesc.text="Ide jön a ruhadarab leírása"
        textViewStyle.text="Ide jön a ruhadarab stílusa"
        e.printStackTrace()

    }

    private fun onLoadSuccess(item: FashionItem?){
        if(item==null){
            onLoadError(Exception("item is null"))
        }
        else{
            textViewCategory.text=item.type
            textViewAge.text=item.age.toString()
            textViewDate.text=item.age.toString()
            textViewDesc.text=item.detail
            textViewStyle.text=item.style
        }
    }

}
