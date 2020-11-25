package com.example.fastfashion.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("userId")
    val userId:Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

)