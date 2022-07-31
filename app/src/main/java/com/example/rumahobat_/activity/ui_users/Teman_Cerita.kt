package com.example.rumahobat_.activity.ui_users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home_Main
import com.example.rumahobat_.activity.Profil_Data_Apoteker
import com.example.rumahobat_.adapter.Adapter_TemanCerita
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_lihat_temanCerita
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_lihat_temanCerita
import kotlinx.android.synthetic.main.teman_cerita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Teman_Cerita : AppCompatActivity() {

    lateinit var session : SessionManager
    var itemsTemanCeritaApoteker : MutableList<Model_lihat_temanCerita> = mutableListOf()
    var treed11: Thread? = null
    var handler1: Handler? = null
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teman_cerita)

        lyError.visibility = View.GONE
        lytidakError.visibility = View.GONE
        session = SessionManager(this)

        beckTemanCerita.setOnClickListener {
            val intent = Intent(applicationContext, Home_Main::class.java)
            startActivity(intent)


            finish()
        }

        handler1 = Handler()
        treed11 = object : Thread() {
            override fun run() {
                i = 0
                while (i < 1000) {
                    try {
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler1!!.post {
                        listTemanCerita()
                    }
                    i++
                }
            }
        };(treed11 as Thread).start()
    }

    private fun listTemanCerita(){
        var kirimListYuk = "temanCerita"
//        rv_list_yuk_ngobrol.setHasFixedSize(true)
//        items1.addAll(Data_Damy2.getData1)
//        rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this)
//        rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(this, items1)




        var id_users = session.id.toString()
        var status_akun_users = "setuju"
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihat_temanCerita>
        call = gsonRetrofit.get_lihat_temanCerita(id_users,status_akun_users)
        call.enqueue(object : Callback<Value_lihat_temanCerita> {
            override fun onFailure(call: Call<Value_lihat_temanCerita>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
//                mShimmerViewContainer.stopShimmerAnimation()
//                mShimmerViewContainer.setVisibility(View.GONE)
//                progress_cari_apoteker_home.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<Value_lihat_temanCerita>,
                response: Response<Value_lihat_temanCerita>
            ) {
                val dataChatHOme = response.body()
                val value = dataChatHOme?.value

                if (value.equals("1")){
                    lyError.visibility = View.GONE
                    lytidakError.visibility = View.VISIBLE

//                        refreshCariApoteker(1000)
//                        Toast.makeText(applicationContext,"jalanji kah Home", Toast.LENGTH_LONG).show()
                    itemsTemanCeritaApoteker = dataChatHOme?.response as MutableList<Model_lihat_temanCerita>
//                        progress_cari_apoteker_home.visibility = View.GONE
//                    mShimmerViewContainer.stopShimmerAnimation()
//                    mShimmerViewContainer.setVisibility(View.GONE)
                    rvTemanCerita.layoutManager = LinearLayoutManager(this@Teman_Cerita)
                    rvTemanCerita.adapter = Adapter_TemanCerita(this@Teman_Cerita, itemsTemanCeritaApoteker){
//                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
                        val modelListChatApoteker1 = Model_lihat_temanCerita(
                            it.id_akun_apoteker,
                            it.id_akun_users,
                            it.jumlah_chat,
                            it.status_akun_users,
                            it.status_akun_apoteker,
                            it.nama_panjang,
                            it.alumni_apoteker,
                            it.tahun_mulai_praktek,
                            it.tempat_praktik,
                            it.foto_profil,
                            it.masa_praktek,
                            it.foto_stra,
                            it.nomor_stra,
                            it.foto_sipa,
                            it.no_sipa,
                            it.status_satu
                        )
                        finish()
                        var intent = Intent(this@Teman_Cerita, Profil_Data_Apoteker::class.java)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER, modelListChatApoteker1)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimListYuk)
                        startActivity(intent)
                    }
                }else if (value.equals("0")){
                    lyError.visibility = View.VISIBLE
                    lytidakError.visibility = View.GONE
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