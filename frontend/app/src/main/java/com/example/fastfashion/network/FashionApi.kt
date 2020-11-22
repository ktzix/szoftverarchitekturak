package com.example.fastfashion.network

import com.example.fastfashion.model.FashionItem
import retrofit2.Call
import retrofit2.http.*

interface FashionApi {

    companion object{
        const val ENDPOINT_URL="http://192.168.100.12:44332"
    }

    @GET("/api/FashionItems")
    fun getFashionItems(): Call<List<FashionItem>>

    @POST("/api/FashionItems")
    fun addFashionItem(@Body param: FashionItem): Call<FashionItem>

    @GET("/api/FashionItems/{id}")
    fun getFashionItemById(@Path("id") id: Int): Call<FashionItem>

    @PUT("/api/FashionItems/{id}")
    fun modifyFashionItem(@Path("id")id :Int, @Body param: FashionItem): Call<Void>

    @DELETE("/api/FashionItems/{id}")
    fun deleteFashionItem(@Path("id") id: Int): Call<FashionItem>

    @GET("/api/FashionItems/type/{Type}")
    fun getFashionItemsByType(@Path("Type") type: String): Call<List<FashionItem>>

    @GET("/api/FashionItems/style/{Style}")
    fun getFashionItemsByStyle(@Path("Style") style: String): Call<List<FashionItem>>
}