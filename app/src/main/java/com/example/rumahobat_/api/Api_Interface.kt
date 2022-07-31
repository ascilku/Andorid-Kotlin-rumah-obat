package com.example.rumahobat_.api

import com.example.rumahobat_.value.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api_Interface {
    companion object{
        const val KEY_URL_FOTO_USERS = "http://192.168.43.47/rumahobat/public/assets/gambar_foto_profil_users/"
    }


    @GET("lihatData/lihatDataApotekerHome.php")
    fun getDataListChatApoteker(): Call<Value_List_Chat_Apoteker>?

    @GET("lihatData/lihatDataApotekerKonsultasi.php")
    fun getDataApotekerKonsultasi(): Call<Value_lihatKonsultasi>

    @GET("lihatData/lihatDataSemuaApoteker.php")
    fun getDaftarSemuaApoteker(): Call<Value_lihatKonsultasi>


    @FormUrlEncoded
    @POST("lihatData/lihatPerNamaApoteker.php")
    fun getNama(
        @Field("id_akun_apoteker") id_akun_apoteker : String
    ): Call<Value_Data_Periwayat>

    @FormUrlEncoded
    @POST("lihatData/lihatPerNamaApoteker.php")
    fun getDataPeriwayat(
        @Field("id_akun_apoteker") id_akun_apoteker : String
    ): Call<Value_Data_Periwayat>

    @FormUrlEncoded
    @POST("lihatData/lihatPenilaian.php")
    fun get_lihatPenilaian(
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_lihatPenilaian>

    @FormUrlEncoded
    @POST("updateData/updatePenilaian.php")
    fun get_update_perPenilaian(
        @Field("id_akun_users") id_akun_users: String,
        @Field("nilai") nilai: String

    ): Call<Value_update_perPenilaian>

    @FormUrlEncoded
    @POST("lihatData/lihatTemanCerita.php")
    fun gettemanCerita(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String,
        @Field("status_akun_user") status_akun_user : String
    ): Call<Value_TemanCerita>


    @FormUrlEncoded
    @POST("updateData/updateTemanCerita.php")
    fun getupdate_temanCerita(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String,
        @Field("status_akun_user") status_akun_user : String
    ): Call<Value_UpdateTemanCerita>

    @FormUrlEncoded
    @POST("lihatData/lihatTemanCeritaSetujui.php")
    fun get_lihat_temanCerita(
        @Field("id_akun_users") id_akun_users : String,
        @Field("status_akun_users") status_akun_users : String
    ): Call<Value_lihat_temanCerita>

    @FormUrlEncoded
    @POST("lihatData/lihatRiwayatApoteker.php")
    fun get_lihat_riwayatApoteker(
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_lihat_riwayatApoteker>

    @FormUrlEncoded
    @POST("lihatData/lihatChatinganPerUsers.php")
    fun getChatingan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_Lihat_Chatingan>

    @FormUrlEncoded
    @POST("lihatData/lihatApotekerAktif.php")
    fun getRiwayatChatingan(
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_Riwayat>


    @FormUrlEncoded
    @POST("lihatData/periksaChatingan.php")
    fun getperiksaChatingan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_periksaChatingan>

    @FormUrlEncoded
    @POST("lihatData/lihatHiddenUsers.php")
    fun getHiddenApotekerHome(
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_Hidden_ApotekerHome>

//    @FormUrlEncoded
//    @POST("lihatData/lihatHiddenUsers.php")
//    fun getHiddenApotekerHome(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_Hidden_ApotekerHome>













//    @FormUrlEncoded
//    @POST("api/Apotekeraktif")
//    fun getRiwayatChatingan(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_Riwayat>


//    @FormUrlEncoded
//    @POST("api/lihatPenilaian")
//    fun get_lihatPenilaian(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_lihatPenilaian>
//

//    @GET("api/dataApoteker")
//    fun getDataListChatApoteker(): Call<Value_List_Chat_Apoteker>?

//    @GET("api/daftarSemuaApoteker")
//    fun getDaftarSemuaApoteker(): Call<Value_lihatKonsultasi>

//    @GET("api/dataApotekerKonsultasi")
//    fun getDataApotekerKonsultasi(): Call<Value_lihatKonsultasi>

    @FormUrlEncoded
    @POST("api/akun/register")
    fun getRegisterAkunApoteker(
        @Field("nama_panjang") nama_panjang : String,
        @Field("email") email : String,
        @Field("no_hp") no_hp : String,
        @Field("password") password : String,
        @Field("c_password") c_password : String,
        @Field("akses") aksesRegister : String
    ): Call<Value_Register_Akun_Apoteker>

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("no_hp") no_hp : String,
        @Field("akses") akses : String
    ) : Call<Value_Login>

//    @FormUrlEncoded
//    @POST("api/chatingan")
//    fun getChatingan(
//        @Field("id_akun_apoteker") id_akun_apoteker : String,
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_Lihat_Chatingan>

//    @FormUrlEncoded
//    @POST("api/daftarRiwayat_Apoteker")
//    fun getNama(
//        @Field("id_akun_apoteker") id_akun_apoteker : String
//    ): Call<Value_Data_Periwayat>

//    @FormUrlEncoded
//    @POST("api/Apotekeraktif")
//    fun getRiwayatChatingan(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_Riwayat>

//    @FormUrlEncoded
//    @POST("api/daftarRiwayat_Apoteker")
//    fun getDataPeriwayat(
//        @Field("id_akun_apoteker") id_akun_apoteker : String
//    ): Call<Value_Data_Periwayat>


//    @FormUrlEncoded
//    @POST("api/perbandinganData")
//    fun getHiddenApotekerHome(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_Hidden_ApotekerHome>

    @GET("api/perbandinganDataAktif")
    fun getperbandinganDataAktif(): Call<Value_PerbandinganDataAktif>

    @FormUrlEncoded
    @POST("api/inputChatingan")
    fun inputChatingan(
        @Field("isiChat") isiChat : String,
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String,
        @Field("kode") kode : String,
        @Field("status") status : String,
        @Field("waktu1") waktu1 : String

    ): Call<Value_Input_Chat>

//    @FormUrlEncoded
//    @POST("api/periksaChatingan")
//    fun getperiksaChatingan(
//        @Field("id_akun_apoteker") id_akun_apoteker : String,
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_periksaChatingan>

    @FormUrlEncoded
    @POST("api/lihatLog_batasChatingan")
    fun getlihatLog_batasChatingan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_lihatLog_batasChatingan>

    @FormUrlEncoded
    @POST("api/akhiriPesan")
    fun getakhiriPesan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String,
        @Field("isiPesanTerakhir") isiPesanTerakhir : String
    ): Call<Value_akhiriPesan>

    @FormUrlEncoded
    @POST("api/lihatLog_peringatanChatingan")
    fun get_lihatLog_peringatanChatingan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_Log_peringatanChatingan>

    @FormUrlEncoded
    @POST("api/akhiriPesanPeringatan")
    fun get_lihatPesanPerinatan(
        @Field("id_akun_apoteker") id_akun_apoteker : String,
        @Field("id_akun_users") id_akun_users : String,
        @Field("isiPesanPeringatan") isiPesanTerakhir : String
    ): Call<Value_LihatPesanPeringatan>



//    @FormUrlEncoded
//    @POST("api/update_perPenilaian")
//    fun get_update_perPenilaian(
//        @Field("id_akun_users") id_akun_users: String,
//        @Field("nilai") nilai: String
//
//    ): Call<Value_update_perPenilaian>

//    @FormUrlEncoded
//    @POST("api/temanCerita")
//    fun gettemanCerita(
//        @Field("id_akun_apoteker") id_akun_apoteker : String,
//        @Field("id_akun_users") id_akun_users : String,
//        @Field("status_akun_user") status_akun_user : String
//    ): Call<Value_TemanCerita>

//    @FormUrlEncoded
//    @POST("api/update_temanCerita")
//    fun getupdate_temanCerita(
//        @Field("id_akun_apoteker") id_akun_apoteker : String,
//        @Field("id_akun_users") id_akun_users : String,
//        @Field("status_akun_users") status_akun_users : String
//    ): Call<Value_UpdateTemanCerita>

//    @FormUrlEncoded
//    @POST("api/lihat_riwayatApoteker")
//    fun get_lihat_riwayatApoteker(
//        @Field("id_akun_users") id_akun_users : String
//    ): Call<Value_lihat_riwayatApoteker>

//    @FormUrlEncoded
//    @POST("api/lihat_temanCerita")
//    fun get_lihat_temanCerita(
//        @Field("id_akun_users") id_akun_users : String,
//        @Field("status_akun_users") status_akun_users : String
//    ): Call<Value_lihat_temanCerita>


    @FormUrlEncoded
    @POST("api/lihatDataUsers")
    fun get_lihatDataUsers(
        @Field("id_akun_users") id_akun_users : String
    ): Call<Value_lihatDataUsers>


    @FormUrlEncoded
    @POST("api/inputDatausers")
    fun get_inputDatausers(
        @Field("id_akun_users") id_akun_users: String,
        @Field("tinggiBadan") tinggiBadan: String,
        @Field("waktu") waktu: String,
        @Field("beratBadan") beratBadan: String,
        @Field("jenisKelamin") jenisKelamin: String,
        @Field("tempatLahir") tempatLahir: String,
        @Field("nama_panjang_users") nama_panjang_users: String,

        @Field("alamat") alamat: String,
        @Field("riwayat_obat") riwayat_obat: String,
        @Field("riwayat_penyakit") riwayat_penyakit: String
    ): Call<Value_Input_Data_Users>



//    @Multipart
//    @POST("inputDatausers")
//    fun upload(
//        @Header("Authorization") authorization: String?,
//        @PartMap map: MutableMap<String, RequestBody>,
//
//        @Field("waktu") waktu: String,
//        @Field("nama_panjang_users") nama_panjang_user : String,
//        @Field("id_akun_users") id_akun_users: String,
//        @Field("beratBadan") beratBadan: String,
//        @Field("tinggiBadan") tinggiBadan: String,
//        @Field("jenisKelamin") jenisKelamin: String,
//        @Field("tempatLahir") tempatLahir: String
//
//    ): Call<Value_Input_Data_Users?>?

    @Multipart
    @POST("api/inputDatausers")
    fun upload(

        @Header("Authorization") authorization: String?,
        @PartMap map: MutableMap<String, RequestBody>,
        @Part("id_akun_users") id_akun_users: RequestBody,
        @Part("tinggiBadan") tinggiBadan: RequestBody,
        @Part("waktu") waktu: RequestBody,
        @Part("beratBadan") beratBadan: RequestBody,
        @Part("jenisKelamin") jenisKelamin: RequestBody,
        @Part("tempatLahir") tempatLahir: RequestBody,
        @Part("nama_panjang_users") nama_panjang_users: RequestBody,

        @Part("alamat") alamat: RequestBody,
        @Part("riwayat_obat") riwayat_obat: RequestBody,
        @Part("riwayat_penyakit") riwayat_penyakit: RequestBody
    ): Call<Value_Input_Data_Users?>?



}


//tampung_tanggal_lahir,
//id_akunInputDataUsers,
//tampung_beratBadan,
//tampung_tinggiBadan,
//tampung_jenisKelamin,
//tampung_tempatLahir,

//
//tampung_nama_profil_users,
//
//tampung_beratBadan,
//
//tampung_tinggiBadan,
//
//tampung_tempatLahir