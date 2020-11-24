package com.example.fastfashion.network

import android.os.Handler
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInteractor {
    private val authApi: AuthApi

    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(AuthApi.ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.authApi=retrofit.create(AuthApi::class.java)
    }

    private fun <T> runCallOnBackgroundThread(
        call: Call<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val handler = Handler()
        Thread {
            try {

                val response = call.execute().body()!!

                handler.post {
                    onSuccess(response)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }.start()
    }

    fun login(onSuccess: (Void) -> Unit, onError: (Throwable) -> Unit){

        val loginRequest=authApi.login()
        runCallOnBackgroundThread(loginRequest,onSuccess, onError)
    }

    fun register( onSuccess: (Void) -> Unit, onError: (Throwable) -> Unit){

        val registerRequest=authApi.register()
        runCallOnBackgroundThread(registerRequest,onSuccess, onError)

    }

}