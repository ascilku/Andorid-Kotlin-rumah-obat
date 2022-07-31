package com.example.rumahobat_.activity.ui_users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home_Main
import com.example.rumahobat_.activity.Konsultasi
import com.example.rumahobat_.activity.Profil_Data_Apoteker
import com.example.rumahobat_.adapter.Adapter_LihatKonsultasi
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.model.Model_lihatKonsultasi
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.value.Value_List_Chat_Apoteker
import com.example.rumahobat_.value.Value_lihatKonsultasi
import kotlinx.android.synthetic.main.daftar_semua_apoteker.*
import kotlinx.android.synthetic.main.daftar_semua_apoteker.progress_konsultasi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Daftar__Semua_Apoteker : AppCompatActivity() {
    companion object{
        const val KEY_KIRIM_KONSULTASI  = "kirimKonsultasi"
    }
    lateinit var kirimKonsultasiDaftarSemuaApoteker : String
    var itemsSemuaDaftarApoteker : MutableList<Model_lihatKonsultasi> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_semua_apoteker)
        kirimKonsultasiDaftarSemuaApoteker = intent.getStringExtra(KEY_KIRIM_KONSULTASI).toString()
//        Toast.makeText(applicationContext, kirimKonsultasiDaftarSemuaApoteker, Toast.LENGTH_LONG).show()
        listKonsultasi()
    }
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    private fun listKonsultasi(){
        progress_konsultasi.visibility = View.VISIBLE
    val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatKonsultasi>
        call = gsonRetrofit.getDaftarSemuaApoteker()
        call.enqueue(object : Callback<Value_lihatKonsultasi> {
            override fun onFailure(call: Call<Value_lihatKonsultasi>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Value_lihatKonsultasi>,
                response: Response<Value_lihatKonsultasi>
            ) {
                val dataChatHOme = response.body()
                val value = dataChatHOme?.value

                if (value.equals("1")){
                    itemsSemuaDaftarApoteker = dataChatHOme?.response as MutableList<Model_lihatKonsultasi>
                    progress_konsultasi.visibility = View.GONE
                    rv_semua_daftar_apoteker.layoutManager = LinearLayoutManager(applicationContext)
                    rv_semua_daftar_apoteker.adapter = Adapter_LihatKonsultasi(applicationContext, itemsSemuaDaftarApoteker){
//                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
                        val modelListChatApoteker1 = Model_lihatKonsultasi(
                            it.id_akun_apoteker,
                            it.nama_panjang,
                            it.alumni_apoteker,
                            it.tahun_mulai_praktek,
                            it.tempat_praktik,
                            it.masa_praktek,
                            it.foto_profil,
                            it.foto_stra,
                            it.nomor_stra,
                            it.foto_sipa,
                            it.no_sipa,
                            it.status_satu
                        )
                        var kirimDaftarApoteker = "semuaApoteker"
                        var intent = Intent(applicationContext, Profil_Data_Apoteker::class.java)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER, modelListChatApoteker1)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimDaftarApoteker)
                        finish()
                        startActivity(intent)

                    }
                }else{
                    Toast.makeText(applicationContext, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (kirimKonsultasiDaftarSemuaApoteker == "Konsultasi"){
            val intent = Intent(applicationContext, Konsultasi::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(applicationContext, Home_Main::class.java)
            startActivity(intent)
        }
        finish()
    }

}