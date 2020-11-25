package com.example.fastfashion

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.network.FashionInteractor
import kotlinx.android.synthetic.main.activity_details.*
import java.io.File

class DetailsActivity : AppCompatActivity() {

    private var id=0
    private val fashionInteractor= FashionInteractor()

    private var userId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Ruhadarab részletei"

        setContentView(R.layout.activity_details)
        id= intent.getIntExtra("id", 0)
        userId=intent.getIntExtra("userId",0)

        fashionInteractor.getFashionItemById(id, this::onLoadSuccess, this::onLoadError)


    }

    private fun onLoadError(e: Throwable){
        Toast.makeText(applicationContext, "Nem sikerült betölteni a ruhadarabot!", Toast.LENGTH_LONG).show()
        textViewCategory.text="Ide jön a ruhadarab kategóriája"
        textViewAge.text="Ide jön a ruhadarab életkora"
        textViewDate.text="Ide jön a ruhadarab megvásárlásának ideje"
        textViewDesc.text="Ide jön a ruhadarab leírása"
        textViewStyle.text="Ide jön a ruhadarab stílusa"
        e.printStackTrace()

    }

    private fun onLoadSuccess(item: FashionItem?){
        if(item==null){
            onLoadError(Exception("Nem sikerült betölteni a ruhadarabot!"))
        }
        else{
            textViewCategory.text=item.type
            textViewAge.text=item.date
            textViewDate.text=item.date
            textViewDesc.text=item.detail
            textViewStyle.text=item.style
            try{
                if(item.uri != ""){
                    val f2 = File(item.uri)
                    val uri = Uri.fromFile(f2)
                    imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver, uri))
                }
            }
            catch(e: Exception){
                e.printStackTrace()
            }

        }
    }

}
