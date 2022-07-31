package com.example.rumahobat_.activity

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_users.Daftar__Semua_Apoteker
import com.example.rumahobat_.activity.ui_users.Teman_Cerita
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.*
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Data_Periwayat
import com.example.rumahobat_.value.Value_Hidden_ApotekerHome
import kotlinx.android.synthetic.main.profil__data__apoteker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profil_Data_Apoteker : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val KEY_DATA_PROFIL_APOTEKER = "data_profil_apoteker"
        const val KEY_DATA_AKSES = "akses"
    }
    lateinit var  kirimKonsultasi : String
    lateinit var  kirimKonsultasi_id : String
    lateinit var session : SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.profil__data__apoteker)
        session = SessionManager(this)
            val typeface1 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-SemiBold.ttf")
            val typeface2 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Bold.ttf")
            val typeface3 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Regular.ttf")
            nama_list_chat_apoteker_home.typeface = typeface3
            apoteker_list_chat_apoteker_home.typeface = typeface2
            nilai_list_chat_apoteker_home.typeface   = typeface3
            pengalaman_list_chat_apoteker_home.typeface = typeface3
            tahun_list_chat_apoteker_home.typeface = typeface3
            harga_list_chat_apoteker_home.typeface = typeface3

            text_alumni_profil.typeface = typeface1
            alumni_profil.typeface = typeface3
            text_tempat_praktek_profil.typeface = typeface1
            tempat_praktek_profil.typeface = typeface3
            text_no_stra_profil.typeface = typeface1
            no_stra_profil.typeface = typeface3
            text_no_sipa_profil.typeface = typeface1
            no_sipa_profil.typeface = typeface3
            chatingan_profil.typeface = typeface3

            chatingan_profil_hidden.visibility = View.GONE
            chatingan_profil.visibility = View.GONE

            kirimKonsultasi = intent.getStringExtra(KEY_DATA_AKSES).toString()
//            Toast.makeText(applicationContext, kirimKonsultasi.)

                if (kirimKonsultasi.equals("Riwayat")){
                    val modelListChatApoteker = intent.getParcelableExtra<Model_Riwayat>(KEY_DATA_PROFIL_APOTEKER)
                    kirimKonsultasi_id = modelListChatApoteker!!.id_akun_apoteker
                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker.id_akun_apoteker, Toast.LENGTH_LONG).show()
                            val intent = Intent(this, Chat_Apoteker::class.java)
                                intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker.id_akun_apoteker)
                            startActivity(intent)
                            finish()
                        }
                    dataRiwayat()
                }else if (kirimKonsultasi.equals("yukNgobrol")){
                    val modelListChatApoteker = intent.getParcelableExtra<Model_lihat_temanCerita>(KEY_DATA_PROFIL_APOTEKER)

//                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker?.id_akun_apoteker, Toast.LENGTH_LONG).show()
//                        }

                    nama_list_chat_apoteker_home.text = modelListChatApoteker?.nama_panjang
//                    nilai_list_chat_apoteker_home.text = modelListChatApoteker?.jumlah_rating
//                    rating_list_chat_apoteker_home.rating = modelListChatApoteker?.jumlah_rating!!.toFloat()
                    pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
                    alumni_profil.text = modelListChatApoteker.alumni_apoteker
                    tempat_praktek_profil.text = modelListChatApoteker.tempat_praktik
                    no_stra_profil.text = modelListChatApoteker.nomor_stra
                    no_sipa_profil.text = modelListChatApoteker.no_sipa
//                    Toast.makeText(applicationContext,modelListChatApoteker.status_satu, Toast.LENGTH_SHORT).show()
                    val status_satu = modelListChatApoteker.status_satu

                    var idAKun = session.id.toString()
                    val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                    val call : Call<Value_Hidden_ApotekerHome>
                    call = gsonRetrofit.getHiddenApotekerHome(idAKun)
                    call.enqueue(object : Callback<Value_Hidden_ApotekerHome>{
                        override fun onFailure(call: Call<Value_Hidden_ApotekerHome>, t: Throwable) {
                            Toast.makeText(this@Profil_Data_Apoteker,"Tidak Ada Koneksi",  Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<Value_Hidden_ApotekerHome>,
                            response: Response<Value_Hidden_ApotekerHome>
                        ) {
                            var dataAKunUsers = response.body()
                            var value = dataAKunUsers?.value
                            var responsee = dataAKunUsers?.response?.get(0)
                            if (value.equals("1")){

                                var statusUsers = responsee?.status_satu_users
                                if (statusUsers.equals("aktif")){
                                    chatingan_profil_hidden.visibility = View.VISIBLE
                                    chatingan_profil.visibility = View.GONE
                                }else{
                                    if (status_satu.equals("aktif")){
                                        chatingan_profil_hidden.visibility = View.VISIBLE
                                        chatingan_profil.visibility = View.GONE
                                        chatingan_profil_hidden.setOnClickListener {
                                            Toast.makeText(applicationContext,"Maaf Sementara Waktu Apoteker Sedang Melayani Pengguna Lain", Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        chatingan_profil_hidden.visibility = View.GONE
                                        chatingan_profil.visibility = View.VISIBLE
                                        chatingan_profil.setOnClickListener(View.OnClickListener {
                                            kirimKonsultasi  = "Home Main"
                                            val intent = Intent(this@Profil_Data_Apoteker, Chat_Apoteker::class.java)
                                            intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker?.id_akun_apoteker)
                                            startActivity(intent)
                                            finish()
                                        })
                                    }
                                }
                            }else{

                            }
                        }
                    })



                }else if (kirimKonsultasi.equals("konsul")){
                    val modelListChatApoteker = intent.getParcelableExtra<Model_lihatKonsultasi>(KEY_DATA_PROFIL_APOTEKER)

//                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker?.id_akun_apoteker, Toast.LENGTH_LONG).show()
//                        }

                    nama_list_chat_apoteker_home.text = modelListChatApoteker?.nama_panjang
//                    nilai_list_chat_apoteker_home.text = modelListChatApoteker?.jumlah_rating
//                    rating_list_chat_apoteker_home.rating = modelListChatApoteker?.jumlah_rating!!.toFloat()
                    pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
                    alumni_profil.text = modelListChatApoteker.alumni_apoteker
                    tempat_praktek_profil.text = modelListChatApoteker.tempat_praktik
                    no_stra_profil.text = modelListChatApoteker.nomor_stra
                    no_sipa_profil.text = modelListChatApoteker.no_sipa
//                    Toast.makeText(applicationContext,modelListChatApoteker.status_satu, Toast.LENGTH_SHORT).show()
                    val status_satu = modelListChatApoteker.status_satu

                    var idAKun = session.id.toString()
                    val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                    val call : Call<Value_Hidden_ApotekerHome>
                    call = gsonRetrofit.getHiddenApotekerHome(idAKun)
                    call.enqueue(object : Callback<Value_Hidden_ApotekerHome>{
                        override fun onFailure(call: Call<Value_Hidden_ApotekerHome>, t: Throwable) {
                            Toast.makeText(this@Profil_Data_Apoteker,"Tidak Ada Koneksi",  Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<Value_Hidden_ApotekerHome>,
                            response: Response<Value_Hidden_ApotekerHome>
                        ) {
                            var dataAKunUsers = response.body()
                            var value = dataAKunUsers?.value
                            var responsee = dataAKunUsers?.response?.get(0)
                            if (value.equals("1")){

                                var statusUsers = responsee?.status_satu_users
                                if (statusUsers.equals("aktif")){
                                    chatingan_profil_hidden.visibility = View.VISIBLE
                                    chatingan_profil.visibility = View.GONE
                                }else{
                                    if (status_satu.equals("aktif")){
                                        chatingan_profil_hidden.visibility = View.VISIBLE
                                        chatingan_profil.visibility = View.GONE
                                        chatingan_profil_hidden.setOnClickListener {
                                            Toast.makeText(applicationContext,"Maaf Sementara Waktu Apoteker Sedang Melayani Pengguna Lain", Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        chatingan_profil_hidden.visibility = View.GONE
                                        chatingan_profil.visibility = View.VISIBLE
                                        chatingan_profil.setOnClickListener(View.OnClickListener {
                                            kirimKonsultasi  = "Home Main"
                                            val intent = Intent(this@Profil_Data_Apoteker, Chat_Apoteker::class.java)
                                            intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker?.id_akun_apoteker)
                                            startActivity(intent)
                                            finish()
                                        })
                                    }
                                }
                            }else{

                            }
                        }
                    })



                }
                else if (kirimKonsultasi.equals("semuaApoteker")){
                    val modelListChatApoteker = intent.getParcelableExtra<Model_lihatKonsultasi>(KEY_DATA_PROFIL_APOTEKER)

//                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker?.id_akun_apoteker, Toast.LENGTH_LONG).show()
//                        }

                    nama_list_chat_apoteker_home.text = modelListChatApoteker?.nama_panjang
//                    nilai_list_chat_apoteker_home.text = modelListChatApoteker?.jumlah_rating
//                    rating_list_chat_apoteker_home.rating = modelListChatApoteker?.jumlah_rating!!.toFloat()
                    pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
                    alumni_profil.text = modelListChatApoteker.alumni_apoteker
                    tempat_praktek_profil.text = modelListChatApoteker.tempat_praktik
                    no_stra_profil.text = modelListChatApoteker.nomor_stra
                    no_sipa_profil.text = modelListChatApoteker.no_sipa
//                    Toast.makeText(applicationContext,modelListChatApoteker.status_satu, Toast.LENGTH_SHORT).show()
                    val status_satu = modelListChatApoteker.status_satu

                    var idAKun = session.id.toString()
                    val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                    val call : Call<Value_Hidden_ApotekerHome>
                    call = gsonRetrofit.getHiddenApotekerHome(idAKun)
                    call.enqueue(object : Callback<Value_Hidden_ApotekerHome>{
                        override fun onFailure(call: Call<Value_Hidden_ApotekerHome>, t: Throwable) {
                            Toast.makeText(this@Profil_Data_Apoteker,"Tidak Ada Koneksi",  Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<Value_Hidden_ApotekerHome>,
                            response: Response<Value_Hidden_ApotekerHome>
                        ) {
                            var dataAKunUsers = response.body()
                            var value = dataAKunUsers?.value
                            var responsee = dataAKunUsers?.response?.get(0)
                            if (value.equals("1")){

                                var statusUsers = responsee?.status_satu_users
                                if (statusUsers.equals("aktif")){
                                    chatingan_profil_hidden.visibility = View.VISIBLE
                                    chatingan_profil.visibility = View.GONE
                                }else{
                                    if (status_satu.equals("aktif")){
                                        chatingan_profil_hidden.visibility = View.VISIBLE
                                        chatingan_profil.visibility = View.GONE
                                        chatingan_profil_hidden.setOnClickListener {
                                            Toast.makeText(applicationContext,"Maaf Sementara Waktu Apoteker Sedang Melayani Pengguna Lain", Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        chatingan_profil_hidden.visibility = View.GONE
                                        chatingan_profil.visibility = View.VISIBLE
                                        chatingan_profil.setOnClickListener(View.OnClickListener {
                                            kirimKonsultasi  = "Home Main"
                                            val intent = Intent(this@Profil_Data_Apoteker, Chat_Apoteker::class.java)
                                            intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker?.id_akun_apoteker)
                                            startActivity(intent)
                                            finish()
                                        })
                                    }
                                }
                            }else{

                            }
                        }
                    })



                }
                else if (kirimKonsultasi.equals("temanCerita")){
                    val modelListChatApoteker = intent.getParcelableExtra<Model_lihat_temanCerita>(KEY_DATA_PROFIL_APOTEKER)

//                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker?.id_akun_apoteker, Toast.LENGTH_LONG).show()
//                        }

                    nama_list_chat_apoteker_home.text = modelListChatApoteker?.nama_panjang
//                    nilai_list_chat_apoteker_home.text = modelListChatApoteker?.jumlah_rating
//                    rating_list_chat_apoteker_home.rating = modelListChatApoteker?.jumlah_rating!!.toFloat()
                    pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
                    alumni_profil.text = modelListChatApoteker.alumni_apoteker
                    tempat_praktek_profil.text = modelListChatApoteker.tempat_praktik
                    no_stra_profil.text = modelListChatApoteker.nomor_stra
                    no_sipa_profil.text = modelListChatApoteker.no_sipa
//                    Toast.makeText(applicationContext,modelListChatApoteker.status_satu, Toast.LENGTH_SHORT).show()
                    val status_satu = modelListChatApoteker.status_satu

                    var idAKun = session.id.toString()
                    val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                    val call : Call<Value_Hidden_ApotekerHome>
                    call = gsonRetrofit.getHiddenApotekerHome(idAKun)
                    call.enqueue(object : Callback<Value_Hidden_ApotekerHome>{
                        override fun onFailure(call: Call<Value_Hidden_ApotekerHome>, t: Throwable) {
                            Toast.makeText(this@Profil_Data_Apoteker,"Tidak Ada Koneksi",  Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<Value_Hidden_ApotekerHome>,
                            response: Response<Value_Hidden_ApotekerHome>
                        ) {
                            var dataAKunUsers = response.body()
                            var value = dataAKunUsers?.value
                            var responsee = dataAKunUsers?.response?.get(0)
                            if (value.equals("1")){

                                var statusUsers = responsee?.status_satu_users
                                if (statusUsers.equals("aktif")){
                                    chatingan_profil_hidden.visibility = View.VISIBLE
                                    chatingan_profil.visibility = View.GONE
                                }else{
                                    if (status_satu.equals("aktif")){
                                        chatingan_profil_hidden.visibility = View.VISIBLE
                                        chatingan_profil.visibility = View.GONE
                                        chatingan_profil_hidden.setOnClickListener {
                                            Toast.makeText(applicationContext,"Maaf Sementara Waktu Apoteker Sedang Melayani Pengguna Lain", Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        chatingan_profil_hidden.visibility = View.GONE
                                        chatingan_profil.visibility = View.VISIBLE
                                        chatingan_profil.setOnClickListener(View.OnClickListener {
                                            kirimKonsultasi  = "Home Main"
                                            val intent = Intent(this@Profil_Data_Apoteker, Chat_Apoteker::class.java)
                                            intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker?.id_akun_apoteker)
                                            startActivity(intent)
                                            finish()
                                        })
                                    }
                                }
                            }else{

                            }
                        }
                    })



                }
                else{
                    val modelListChatApoteker = intent.getParcelableExtra<Model_List_Chat_Apoteker>(KEY_DATA_PROFIL_APOTEKER)

//                        chatingan_profil.setOnClickListener {
//                            Toast.makeText(applicationContext, modelListChatApoteker?.id_akun_apoteker, Toast.LENGTH_LONG).show()
//                        }

                    nama_list_chat_apoteker_home.text = modelListChatApoteker?.nama_panjang
//                    nilai_list_chat_apoteker_home.text = modelListChatApoteker?.jumlah_rating
//                    rating_list_chat_apoteker_home.rating = modelListChatApoteker?.jumlah_rating!!.toFloat()
                    pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
                    alumni_profil.text = modelListChatApoteker.alumni_apoteker
                    tempat_praktek_profil.text = modelListChatApoteker.tempat_praktik
                    no_stra_profil.text = modelListChatApoteker.nomor_stra
                    no_sipa_profil.text = modelListChatApoteker.no_sipa
//                    Toast.makeText(applicationContext,modelListChatApoteker.status_satu, Toast.LENGTH_SHORT).show()
                    val status_satu = modelListChatApoteker.status_satu
                    if (status_satu.equals("aktif")){
                        chatingan_profil_hidden.visibility = View.VISIBLE
                        chatingan_profil.visibility = View.GONE
                        chatingan_profil_hidden.setOnClickListener {
                            Toast.makeText(applicationContext,"Maaf Sementara Waktu Apoteker Sedang Melayani Pengguna Lain", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        chatingan_profil_hidden.visibility = View.GONE
                        chatingan_profil.visibility = View.VISIBLE
                        chatingan_profil.setOnClickListener(View.OnClickListener {
                            kirimKonsultasi  = "Home Main"
                            val intent = Intent(this, Chat_Apoteker::class.java)
                            intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelListChatApoteker?.id_akun_apoteker)
                            startActivity(intent)
                            finish()
                        })
                    }

                }


            beckProfil.setOnClickListener(this)

    }

    private fun dataRiwayat() {
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Data_Periwayat>
        call = gsonRetrofit.getDataPeriwayat(kirimKonsultasi_id)
        call.enqueue(object : Callback<Value_Data_Periwayat>{
            override fun onFailure(call: Call<Value_Data_Periwayat>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Ada Koneksi" ,  Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<Value_Data_Periwayat>,
                response: Response<Value_Data_Periwayat>
            ) {
                val dataLogin = response.body()
//                items = dataLogin?.response as MutableList<Model_Login>

                val value = dataLogin?.value
                val pesan = dataLogin?.pesan
                val response = dataLogin?.response?.get(0)
                    if (value.equals("1")){
//                        Toast.makeText(applicationContext, response?.nama_panjang ,  Toast.LENGTH_LONG).show()
                        nama_list_chat_apoteker_home.text = response?.nama_panjang
//                        nilai_list_chat_apoteker_home.text = response?.jumlah_rating
//                        rating_list_chat_apoteker_home.rating = response?.jumlah_rating!!.toFloat()
                        pengalaman_list_chat_apoteker_home.text = response?.masa_praktek
                        alumni_profil.text = response?.alumni_apoteker
                        tempat_praktek_profil.text = response?.tempat_praktik
                        no_stra_profil.text = response?.nomor_stra

                        chatingan_profil_hidden.visibility = View.GONE
                        chatingan_profil.visibility = View.VISIBLE
                    }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.beckProfil -> {
                if (kirimKonsultasi == "Home Main"){
                    val intent = Intent(applicationContext, Home_Main::class.java)
                    startActivity(intent)
                }else if (kirimKonsultasi == "Riwayat"){
                    val intent = Intent(applicationContext, Home_Main::class.java)
                    startActivity(intent)
                }else if (kirimKonsultasi == "semuaApoteker"){
                    var kirimHomeMain = "Home Main"
                    val intent = Intent(applicationContext, Daftar__Semua_Apoteker::class.java)
                    intent.putExtra(Daftar__Semua_Apoteker.KEY_KIRIM_KONSULTASI, kirimHomeMain)
                    startActivity(intent)
                }else if (kirimKonsultasi == "yukNgobrol"){
                    var kirimHomeMain = "Home Main"
                    val intent = Intent(applicationContext, Home_Main::class.java)
                    startActivity(intent)
                }else if (kirimKonsultasi == "konsul"){
                    var kirimHomeMain = "Home Main"
                    val intent = Intent(applicationContext, Konsultasi::class.java)
                    startActivity(intent)
                }
                else if (kirimKonsultasi == "temanCerita"){
                    val intent = Intent(applicationContext, Teman_Cerita::class.java)
                    startActivity(intent)
                }

                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (kirimKonsultasi == "Home Main"){
            val intent = Intent(applicationContext, Home_Main::class.java)
            startActivity(intent)
        }else if (kirimKonsultasi == "Riwayat"){
            val intent = Intent(applicationContext, Home_Main::class.java)
            startActivity(intent)
        }else if (kirimKonsultasi == "semuaApoteker"){
            var kirimHomeMain = "Home Main"
        val intent = Intent(applicationContext, Daftar__Semua_Apoteker::class.java)
            intent.putExtra(Daftar__Semua_Apoteker.KEY_KIRIM_KONSULTASI, kirimHomeMain)
        startActivity(intent)
        }else if (kirimKonsultasi == "yukNgobrol"){
            var kirimHomeMain = "Home Main"
            val intent = Intent(applicationContext, Home_Main::class.java)
            startActivity(intent)
        }else if (kirimKonsultasi == "konsul"){
            var kirimHomeMain = "Home Main"
            val intent = Intent(applicationContext, Konsultasi::class.java)
            startActivity(intent)
        }
        else if (kirimKonsultasi == "temanCerita"){
            val intent = Intent(applicationContext, Teman_Cerita::class.java)
            startActivity(intent)
        }

        finish()
    }
}
