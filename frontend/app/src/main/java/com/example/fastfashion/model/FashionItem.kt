package com.example.fastfashion.model

import com.google.gson.annotations.SerializedName

data class FashionItem (
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("style")
    val style: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("pictureUri")
    val uri: String
)