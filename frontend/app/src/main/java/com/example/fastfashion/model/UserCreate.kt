package com.example.fastfashion.model

import com.google.gson.annotations.SerializedName

data class UserCreate (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)