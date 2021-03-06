package com.example.fastfashion.network

import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.model.FashionItemCreate
import retrofit2.Call
import retrofit2.http.*

interface FashionApi {

    companion object{
        //const val ENDPOINT_URL="http://192.168.100.12:44332"
        const val ENDPOINT_URL="https://fastfashion.azurewebsites.net"
        //const val ENDPOINT_URL="http://localhost:44332"
    }

    @GET("/api/FashionItems/all/{userId}")
    fun getFashionItems(@Path("userId") userId: Int): Call<List<FashionItem>>

    @POST("/api/FashionItems")
    fun addFashionItem(@Body param: FashionItemCreate): Call<FashionItem>

    @GET("/api/FashionItems/{id}")
    fun getFashionItemById(@Path("id") id: Int): Call<FashionItem>

    @PUT("/api/FashionItems/{id}")
    fun modifyFashionItem(@Path("id")id :Int, @Body param: FashionItem): Call<Void>

    @DELETE("/api/FashionItems/{id}")
    fun deleteFashionItem(@Path("id") id: Int): Call<Void>

    @GET("/api/FashionItems/type/{Type}/{UserId}")
    fun getFashionItemsByType(@Path("Type") type: String, @Path("UserId") userId: Int): Call<List<FashionItem>>

    @GET("/api/FashionItems/style/{Style}/{UserId}")
    fun getFashionItemsByStyle(@Path("Style") style: String, @Path("UserId") userId: Int): Call<List<FashionItem>>
}