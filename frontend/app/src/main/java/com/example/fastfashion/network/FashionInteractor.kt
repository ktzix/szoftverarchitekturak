package com.example.fastfashion.network

import android.os.Handler
import android.util.Log
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.model.FashionItemCreate
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class FashionInteractor {
    private val fashionApi: FashionApi

    init {
        val innerClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(FashionApi.ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(innerClient)
            .build()

        this.fashionApi=retrofit.create(FashionApi::class.java)
    }

    private fun <T> runCallOnBackgroundThread(
        call: Call<T>,
        onSuccess: (T?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val handler = Handler()
        Thread {
            try {
                Log.i("callrequest", call.request().toString())

                val c=call.execute()

                Log.i("statuscode", c.code().toString())
                if(c.code()!=200){
                    Log.d("message", c.message())
                }
                val response =c.body()
                handler.post {
                    onSuccess(response)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }.start()
    }

    fun getFashionItems(userId: Int, onSuccess: (List<FashionItem>?) -> Unit, onError: (Throwable) -> Unit){
        val getRequest = fashionApi.getFashionItems(userId)
        runCallOnBackgroundThread(getRequest, onSuccess, onError)
    }

    fun getFashionItemById(id: Int, onSuccess: (FashionItem?) -> Unit, onError: (Throwable) -> Unit){
        val req= fashionApi.getFashionItemById(id)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

    fun getFashionItemsByStyle(
        userId: Int,
        style: String,
        onSuccess: (List<FashionItem>?) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val req=fashionApi.getFashionItemsByStyle(style, userId)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

    fun getFashionItemsByType(
        userId: Int,
        type: String,
        onSuccess: (List<FashionItem>?) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val req=fashionApi.getFashionItemsByType(type, userId)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

    fun modifyFashionItem(
        id: Int,
        param: FashionItem,
        onSuccess: (Void?) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val req=fashionApi.modifyFashionItem(id, param)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

    fun deleteFashionItem(id: Int, onSuccess: (Void?) -> Unit, onError: (Throwable) -> Unit){
        val req=fashionApi.deleteFashionItem(id)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

    fun addFashionItem(
        param: FashionItemCreate,
        onSuccess: (FashionItem?) -> Unit,
        onError: (Throwable) -> Unit
    ){
        val req=fashionApi.addFashionItem(param)
        runCallOnBackgroundThread(req, onSuccess, onError)
    }

}
