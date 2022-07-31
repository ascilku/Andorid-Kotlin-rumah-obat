package com.example.rumahobat_.activity.ui_apoteker.coba

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object AppConfig {
    private const val BASE_URL = "http://192.168.43.47/uploadimage/"

    //    public static String BASE_URL = "http://unitypuzzlegame.com/";
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}