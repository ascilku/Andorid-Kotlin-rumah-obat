package com.example.rumahobat_.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_users.Daftar__Semua_Apoteker
import com.example.rumahobat_.adapter.Adapter_LihatKonsultasi
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.model.Model_lihatKonsultasi
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.value.Value_List_Chat_Apoteker
import com.example.rumahobat_.value.Value_lihatKonsultasi
import kotlinx.android.synthetic.main.konsultasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Konsultasi : AppCompatActivity() {
    var itemsKonsultasi : MutableList<Model_lihatKonsultasi> = mutableListOf()
    lateinit var  kirimKonsultasi : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.konsultasi)
        kirimKonsultasi  = "Konsultasi"
        var klik_lihat_apoteker_konsultasi = findViewById<TextView>(R.id.klik_lihat_apoteker_konsultasi)
        klik_lihat_apoteker_konsultasi.setOnClickListener {
            val intent = Intent(applicationContext, Daftar__Semua_Apoteker::class.java)
                intent.putExtra(Daftar__Semua_Apoteker.KEY_KIRIM_KONSULTASI, kirimKonsultasi)
            startActivity(intent)
            finish()
        }
        beckChatingan.setOnClickListener {
            val intent = Intent(applicationContext, Home_Main::class.java)
            intent.putExtra(Daftar__Semua_Apoteker.KEY_KIRIM_KONSULTASI, kirimKonsultasi)
            startActivity(intent)
            finish()
        }
        listKonsultasi()
    }

    private fun listKonsultasi(){
        progress_konsultasi.visibility = View.VISIBLE
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatKonsultasi>
        call = gsonRetrofit.getDataApotekerKonsultasi()
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
                    itemsKonsultasi = dataChatHOme?.response as MutableList<Model_lihatKonsultasi>
                    progress_konsultasi.visibility = View.GONE
                    rv_konsultasi.layoutManager = LinearLayoutManager(applicationContext)
                    rv_konsultasi.adapter = Adapter_LihatKonsultasi(applicationContext, itemsKonsultasi)
                    {
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
                        var kirimKonsultasiKirim = "konsul"
                        var intent = Intent(applicationContext, Profil_Data_Apoteker::class.java)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER, modelListChatApoteker1)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimKonsultasiKirim)
                        startActivity(intent)
                        finish()
                    }
                }else{
                    Toast.makeText(applicationContext, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext, Home_Main::class.java)
        startActivity(intent)
        finish()
    }

}
