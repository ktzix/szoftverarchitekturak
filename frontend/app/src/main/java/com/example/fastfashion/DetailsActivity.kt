package com.example.fastfashion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textViewCategory.text="Ide jön a ruhadarab kategóriája"
        textViewAge.text="Ide jön a ruhadarab életkora"
        textViewDate.text="Ide jön a ruhadarab megvásárlásának ideje"
        textViewDesc.text="Ide jön a ruhadarab leírása"
        textViewStyle.text="Ide jön a ruhadarab stílusa"
    }


}
