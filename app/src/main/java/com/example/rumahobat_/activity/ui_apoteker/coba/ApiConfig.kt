package com.example.rumahobat_.activity.ui_apoteker.coba

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

internal interface ApiConfig {
    //    @Multipart
    //    @POST("uploadimage.php")
    //    Call<ResponseApiModel> uploadImage (@Part MultipartBody.Part image);
    @Multipart
    @POST("upload_image.php")
    fun upload(
        @Header("Authorization") authorization: String?,
        @PartMap map: MutableMap<String, RequestBody>
    ): Call<ServerResponse?>?
}