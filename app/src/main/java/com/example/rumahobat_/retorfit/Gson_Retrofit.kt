package com.example.rumahobat_.retorfit

import com.example.rumahobat_.api.Api_Interface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Gson_Retrofit {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.43.47/api_rumahObat/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}






//{
//
//    fun createRetrofit(): Api_Interface{
//        val retrofit = Retrofit
//            .Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("http://192.168.1.10/rumahobat/api/").
//            build()
//        return retrofit.create(Api_Interface::class.java)
//    }
//}