package com.example.fastfashion.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    companion object{
        const val ENDPOINT_URL="https://fastfashion.azurewebsites.net"
    }

    @POST("auth/login")
    fun login(): Call<Void>

    @POST("auth/register")
    fun register(): Call<Void>
}