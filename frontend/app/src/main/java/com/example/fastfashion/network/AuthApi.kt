package com.example.fastfashion.network

import com.example.fastfashion.model.IdResult
import com.example.fastfashion.model.User
import com.example.fastfashion.model.UserCreate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    companion object{
        const val ENDPOINT_URL="https://fastfashion.azurewebsites.net/api/"
    }

    @POST("User/login")
    fun login(@Body param:UserCreate): Call<IdResult>

    @POST("User/register")
    fun register(@Body param: UserCreate): Call<IdResult>
}