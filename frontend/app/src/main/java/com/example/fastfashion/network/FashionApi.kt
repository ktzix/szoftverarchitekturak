package com.example.fastfashion.network

import com.example.fastfashion.model.FashionItem
import retrofit2.Call
import retrofit2.http.GET

interface FashionApi {

    companion object{
        const val ENDPOINT_URL="http://192.168.100.12:44332"
    }

    @GET("/api/FashionItems")
    fun getFashionItems(): Call<List<FashionItem>>
}