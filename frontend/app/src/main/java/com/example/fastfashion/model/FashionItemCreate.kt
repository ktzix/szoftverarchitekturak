package com.example.fastfashion.model

import com.google.gson.annotations.SerializedName

data class FashionItemCreate (

    @SerializedName("type")
    val type: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("style")
    val style: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("pictureUri")
    val uri: String,
    @SerializedName("userId")
    val userId:Int
)