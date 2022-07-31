package com.example.rumahobat_.activity


import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_users.Daftar__Semua_Apoteker
import com.example.rumahobat_.adapter.Adapter_Home_List_Cari_Apoteker
import com.example.rumahobat_.adapter.Adapter_Home_List_Mari_Diskusi_Apoteker
import com.example.rumahobat_.adapter.Adapter_Home_List_Tahukah_Apoteker
import com.example.rumahobat_.adapter.Adapter_Home_List_Yuk_Ngobrol_Apoteker
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.data_damy.Data_Damy3
import com.example.rumahobat_.data_damy.Data_Damy_Tahukah
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.model.Model_Riwayat
import com.example.rumahobat_.model.Model_lihat_riwayatApoteker
import com.example.rumahobat_.model_damy.Model_Damy3
import com.example.rumahobat_.model_damy.Model_Damy4
import com.example.rumahobat_.model_damy.Model_Damy_Tahukah
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionCoba
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.*
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.home_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Main : AppCompatActivity(), Drawer.OnDrawerItemClickListener {
    lateinit var drawer : Drawer.Result
    private var accountHeader : AccountHeader.Result? = null
//    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    var dialogView: View? = null
    //    var items : MutableList<Model_Damy> = mutableListOf()
    var itemsRiwayatApoteker : MutableList<Model_lihat_riwayatApoteker> = mutableListOf()
     var items2 : MutableList<Model_Damy3> = mutableListOf()
    var items3 : MutableList<Model_Damy4> = mutableListOf()
    var items4 : MutableList<Model_Damy_Tahukah> = mutableListOf()
    lateinit var kirimHomeMain : String
    lateinit var kirimHomeMain_riwayat : String
    lateinit var session : SessionManager
    lateinit var sessionCoba : SessionCoba
    lateinit var penilaian_puass : ImageView
    lateinit var penilaian_puastidak : ImageView
    lateinit var terima_status_namaPanjang : String
    lateinit var terima_status_Foto : String
    lateinit var values: String
    lateinit var ambildata : String
    private var lastPosition = 0
    var i = 0
    var handler1: Handler? = null
    var treed11: Thread? = null
    val liveDataA = MutableLiveData<String> ()
    var items : MutableList<Model_List_Chat_Apoteker> = mutableListOf()
    var items_riwayat : MutableList<Model_Riwayat> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)


        //retrieve last position on start



        var dialog: AlertDialog.Builder? = null
        var inflater: LayoutInflater? = null
        var dialogView: View? = null
        foto_riwayat_apoteker_home.visibility = View.GONE
        ly1.visibility = View.GONE

        namaRiwayat.visibility = View.GONE

        lyRiwayat.visibility = View.GONE
        text_riwayat.visibility = View.GONE
        card_riwayat.visibility = View.GONE
        lyRiwayat2.visibility = View.GONE
//        mShimmerViewRiwayat.setVisibility(View.GONE)
        session = SessionManager(this)
        sessionCoba = SessionCoba(this)
        handler1 = Handler()


        listChatHome()
        listYukNgobrol()
        listMulaiDiskusi()
//        listFavorit()
        listTahukah()
        riwayat()
        lihatDataPenilaian()

        val typeface1 = Typeface.createFromAsset(assets, "nunito_sans/NunitoSans-SemiBold.ttf")
        val typeface2 = Typeface.createFromAsset(assets, "nunito_sans/NunitoSans-Bold.ttf")
        val typeface3 = Typeface.createFromAsset(assets, "nunito_sans/NunitoSans-Regular.ttf")
        text_konsultasi_cariApoteker_home.typeface = typeface1
//        text_teman_cerita_cariApoteker_home.typeface = typeface1
//        text_forum_cariApoteker_home.typeface = typeface1
        cariApoteker_home.typeface = typeface2
        klik_cariApoteker_home.typeface = typeface3
        yuk_list_yuk_ngobrol.typeface = typeface2
        mulai_berdiskusi_list_yuk_ngobrol.typeface = typeface2
        rl2.setOnClickListener {
            val intent = Intent(applicationContext, Konsultasi::class.java)
            startActivity(intent)
            finish()
        }

        rl3.setOnClickListener {
            val intent = Intent(applicationContext, Konsultasi::class.java)
            startActivity(intent)
            finish()
        }

        kirimHomeMain  = "Home Main"
        kirimHomeMain_riwayat = "Riwayat"
        klik_cariApoteker_home.setOnClickListener {
            val intent = Intent(applicationContext, Daftar__Semua_Apoteker::class.java)
            intent.putExtra(Daftar__Semua_Apoteker.KEY_KIRIM_KONSULTASI, kirimHomeMain)
            startActivity(intent)
            finish()
        }

//        if (session.isLoggedIn) {
//            accountHeader = AccountHeader()
//                .withActivity(this)
//                .withCompactStyle(false)
//                .withSavedInstance(savedInstanceState)
//                .withHeaderBackground(R.color.colorPrimary)
//                .addProfiles(
//                    ProfileDrawerItem().withName(session.nama).withIcon(resources.getDrawable(R.drawable.logo))
//                )
//                .build()
//        }

        if (!session.isLoggedIn) {
            finish()
            val intent = Intent(this, Slide_Awal::class.java)
            startActivity(intent)
        }

//        drawer = Drawer()
//            .withActivity(this)
//            .withToolbar(idToolbar)
//            .withDisplayBelowToolbar(false)
//            .withActionBarDrawerToggleAnimated(true)
//            .withDrawerGravity(LEFT)
//            .withSavedInstance(savedInstanceState)
//            .withAccountHeader(accountHeader)
//            .withSelectedItem(0)
//            .build()
//        drawer.addItem(
//            PrimaryDrawerItem().withName("Pengaturan")
//                .withIcon(resources.getDrawable(R.drawable.ic_settings_black_24dp))
//        )
//        drawer.addItem(
//            PrimaryDrawerItem().withName("Bantuan")
//                .withIcon(resources.getDrawable(R.drawable.ic_help_black_24dp))
//        )

//        if (session.isLoggedIn) {
//            drawer.addItem(
//                PrimaryDrawerItem().withName("Log Out")
//                    .withIcon(resources.getDrawable(R.drawable.ic_power_settings_new_black_24dp))
//            )
//        }else{
//            drawer.addItem(
//                PrimaryDrawerItem().withName("Log In")
//                    .withIcon(resources.getDrawable(R.drawable.ic_power_settings_new_black_24dp))
//            )
//        }

//        drawer.onDrawerItemClickListener = this
        profil_home.setOnClickListener {
            val intent = Intent(applicationContext, Profil_Home::class.java)
            finish()
            startActivity(intent)
        }


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            Toast.makeText(applicationContext, "tidak berhasil diizinkan", Toast.LENGTH_LONG).show()
//            ambilGambar.setEnabled(false)
//            botton_kirim.setEnabled(false)
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                0
            )
        } else {
//            Toast.makeText(applicationContext, " berhasil diizinkan", Toast.LENGTH_LONG).show()
//            image.setEnabled(true)
//            video.setEnabled(true)
//            pdf.setEnabled(true)
        }


    }

    private fun lihatDataPenilaian() {
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatPenilaian>
        call = gsonRetrofit.get_lihatPenilaian(id_penilaian)
        call.enqueue(object : Callback<Value_lihatPenilaian> {
            override fun onFailure(call: Call<Value_lihatPenilaian>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_lihatPenilaian>,
                response: Response<Value_lihatPenilaian>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value

                if (value.equals("1")) {
                    val responsePenilaian = dataLogin?.response?.get(0)
                    var terima_status_rating = responsePenilaian?.status_rating
                    terima_status_namaPanjang = responsePenilaian?.nama_panjang.toString()

//                    Toast.makeText(applicationContext, terima_status_rating, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11
                    if (terima_status_rating == "akhiri") {
                        dialogPenilaian()
                    }


                } else {

                }
            }
        })
    }

    private fun updateDataPenilaianSuka() {
        val nilaiSuka = "1"
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_update_perPenilaian>
        call = gsonRetrofit.get_update_perPenilaian(id_penilaian, nilaiSuka)
        call.enqueue(object : Callback<Value_update_perPenilaian> {
            override fun onFailure(call: Call<Value_update_perPenilaian>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_update_perPenilaian>,
                response: Response<Value_update_perPenilaian>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")) {


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11


                } else {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun updateDataPenilaianTidakSuka() {
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_update_perPenilaian>
        val nilaiTidakSuka = "0"
        call = gsonRetrofit.get_update_perPenilaian(id_penilaian, nilaiTidakSuka)
        call.enqueue(object : Callback<Value_update_perPenilaian> {
            override fun onFailure(call: Call<Value_update_perPenilaian>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_update_perPenilaian>,
                response: Response<Value_update_perPenilaian>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")) {


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11


                } else {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun riwayat() {
        var id_riwayat = session.id.toString()
//        Toast.makeText(applicationContext, id_riwayat, Toast.LENGTH_LONG).show()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call :Call<Value_Riwayat>
        call = gsonRetrofit.getRiwayatChatingan(id_riwayat)
        call.enqueue(object : Callback<Value_Riwayat> {
            override fun onFailure(call: Call<Value_Riwayat>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Value_Riwayat>, response: Response<Value_Riwayat>) {
                val dataLogin = response.body()
//                items = dataLogin?.response as MutableList<Model_Login>

                mShimmerViewRiwayat.stopShimmerAnimation()
                mShimmerViewRiwayat.setVisibility(View.GONE)

                val value = dataLogin?.value
                val pesan = dataLogin?.pesan
                val response = dataLogin?.response?.get(0)
                if (value.equals("1")) {
                    Toast.makeText(
                        applicationContext,
                        "Teman Chat Saat Ini " + response?.nama_panjang,
                        Toast.LENGTH_LONG
                    ).show()
                    lyRiwayat.visibility = View.VISIBLE
                    text_riwayat.visibility = View.VISIBLE
                    card_riwayat.visibility = View.VISIBLE
                    lyRiwayat2.visibility = View.VISIBLE
                    namaRiwayat.visibility = View.VISIBLE
                    foto_riwayat_apoteker_home.visibility = View.VISIBLE
                    ly1.visibility = View.VISIBLE
                    namaRiwayat.text = response?.nama_panjang
                    text_riwayat.text = response?.nama_panjang
                    card_riwayat.setOnClickListener {
                        val modelRiwayat = Model_Riwayat(
                            response?.nama_panjang_users.toString(),
                            response?.nama_panjang.toString(),
                            response?.isi.toString(),
                            response?.kode_akses_chat.toString(),
                            response?.id_akun_apoteker.toString()
                        )
                        val intent = Intent(
                            applicationContext,
                            Profil_Data_Apoteker::class.java
                        )
                        intent.putExtra(
                            Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER,
                            modelRiwayat
                        )
                        intent.putExtra(
                            Profil_Data_Apoteker.KEY_DATA_AKSES,
                            kirimHomeMain_riwayat
                        )

                        startActivity(intent)
                        finish()
                    }
                } else {
                    lyRiwayat.visibility = View.GONE
                    text_riwayat.visibility = View.GONE
                    card_riwayat.visibility = View.GONE
                    lyRiwayat2.visibility = View.GONE
                }
            }
        })

    }

    private fun listChatHome(){



//        progress_cari_apoteker_home.visibility = View.VISIBLE
                 val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)

//        val call :Call<Value_List_Chat_Apoteker>?
        val call = gsonRetrofit.getDataListChatApoteker()
            call?.enqueue(object : Callback<Value_List_Chat_Apoteker> {
                override fun onFailure(call: Call<Value_List_Chat_Apoteker>, t: Throwable) {
                    Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
                    mShimmerViewContainer.stopShimmerAnimation()
                    mShimmerViewContainer.setVisibility(View.GONE)
//                progress_cari_apoteker_home.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<Value_List_Chat_Apoteker>,
                    response: Response<Value_List_Chat_Apoteker>
                ) {
                    val dataChatHOme = response.body()

                    values = dataChatHOme?.value.toString()
//                    Toast.makeText(applicationContext, "jalan nomor  " +values, Toast.LENGTH_LONG).show()
                    items = dataChatHOme?.response as MutableList<Model_List_Chat_Apoteker>
                    if (response.isSuccessful) {

//                        refreshCariApoteker(1000)





//                        progress_cari_apoteker_home.visibility = View.GONE
                        mShimmerViewContainer.stopShimmerAnimation()
                        mShimmerViewContainer.setVisibility(View.GONE)
                        rv_list_chat_home.layoutManager = LinearLayoutManager(
                            this@Home_Main,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        var adapterr = Adapter_Home_List_Cari_Apoteker(this@Home_Main, items) {
//                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
//                            rv_list_chat_home.scrollToPosition((items.size -1))
//                            rv_list_chat_home.smoothScrollToPosition(items.size -1)

                            val modelListChatApoteker1 = Model_List_Chat_Apoteker(
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
                            finish()
                            var intent = Intent(this@Home_Main, Profil_Data_Apoteker::class.java)
                            intent.putExtra(
                                Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER,
                                modelListChatApoteker1
                            )
                            intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimHomeMain)
                            startActivity(intent)
                        }
                        rv_list_chat_home.adapter = adapterr
                        adapterr.notifyDataSetChanged()
                        adapterr.updateItems(items)
                        rv_list_chat_home.removeAllViews()

                    } else {
                        mShimmerViewContainer.stopShimmerAnimation()
                        mShimmerViewContainer.setVisibility(View.GONE)

                    }
                }

            })


    }

    private fun listYukNgobrol(){
        var kirimListYuk = "yukNgobrol"
//        rv_list_yuk_ngobrol.setHasFixedSize(true)
//        items1.addAll(Data_Damy2.getData1)
//        rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this)
//        rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(this, items1)




        var id_users = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
//        val call :Call<Value_lihat_riwayatApoteker>
        val call = gsonRetrofit.get_lihat_riwayatApoteker(id_users)

        call.enqueue(object : Callback<Value_lihat_riwayatApoteker> {
            override fun onFailure(call: Call<Value_lihat_riwayatApoteker>, t: Throwable) {
                Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
                mShimmerViewContainer.stopShimmerAnimation()
                mShimmerViewContainer.setVisibility(View.GONE)
//                progress_cari_apoteker_home.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<Value_lihat_riwayatApoteker>,
                response: Response<Value_lihat_riwayatApoteker>
            ) {
                val dataChatHOme = response.body()
                val value = dataChatHOme?.value

                if (value.equals("1")) {
//                        refreshCariApoteker(1000)
//                        Toast.makeText(applicationContext,"jalanji kah Home", Toast.LENGTH_LONG).show()
                    itemsRiwayatApoteker =
                        dataChatHOme?.response as MutableList<Model_lihat_riwayatApoteker>
//                        progress_cari_apoteker_home.visibility = View.GONE
                    mShimmerViewContainer.stopShimmerAnimation()
                    mShimmerViewContainer.setVisibility(View.GONE)
                    rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this@Home_Main)
                    rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(
                        this@Home_Main,
                        itemsRiwayatApoteker
                    ) {
//                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
                        val modelListChatApoteker1 = Model_lihat_riwayatApoteker(
                            it.id_akun_apoteker,
                            it.id_akun_users,
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
                        var intent = Intent(this@Home_Main, Profil_Data_Apoteker::class.java)
                        intent.putExtra(
                            Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER,
                            modelListChatApoteker1
                        )
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimListYuk)
                        startActivity(intent)
                    }
                } else {
                    mShimmerViewContainer.stopShimmerAnimation()
                    mShimmerViewContainer.setVisibility(View.GONE)
                }
            }

        })
    }

    private fun listMulaiDiskusi(){
        rv_list_mulai_diskusi_ngobrol.setHasFixedSize(true)
        items2.addAll(Data_Damy3.getDataDiskusi)
        rv_list_mulai_diskusi_ngobrol.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_list_mulai_diskusi_ngobrol.adapter = Adapter_Home_List_Mari_Diskusi_Apoteker(
            this,
            items2
        )
    }

//    private fun listFavorit(){
//        rv_list_favorit_ngobrol.setHasFixedSize(true)
//        items3.addAll(Data_Damy4.getData)
//        rv_list_favorit_ngobrol.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
//        rv_list_favorit_ngobrol.adapter = Adapter_Home_List_favorit_Apoteker(this, items3)
//    }

    private fun listTahukah(){
        rv_list_tahukah_ngobrol.setHasFixedSize(true)
        items4.addAll(Data_Damy_Tahukah.getDataTahukah)
        rv_list_tahukah_ngobrol.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_list_tahukah_ngobrol.adapter = Adapter_Home_List_Tahukah_Apoteker(this, items4)
    }

    override fun onItemClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
        drawerItem: IDrawerItem?
    ) {
        val position1 = position
            if (position1 == 0){
                Toast.makeText(view?.context, "0", Toast.LENGTH_SHORT).show()
            }else if (position1 == 1){
                startActivity(Intent(this, Main_LoginRegister::class.java))
            }else if (position1 == 2){
                session.logoutUser()
                finish()
            }else if (position1 == 3){
                startActivity(Intent(this, Main_LoginRegister::class.java))
                finish()
            }
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmerAnimation()
        mShimmerViewRiwayat.startShimmerAnimation()
    }

    override fun onPause() {
        mShimmerViewContainer.stopShimmerAnimation()
        mShimmerViewRiwayat.stopShimmerAnimation()
        super.onPause()
    }

    private fun dialogPenilaian() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.penilaian_apoteker_home)

        penilaian_puass = openDialog.findViewById(R.id.penilaian_puass) !!
        penilaian_puastidak = openDialog.findViewById(R.id.penilaiantidakPuass) !!
        var nama_penilaian_apoteker = openDialog.findViewById<TextView>(R.id.nama_penilaian_apoteker) !!
        openDialog.setCancelable(false)

//        Toast.makeText(applicationContext, terima_status_namaPanjang, Toast.LENGTH_LONG).show()
        nama_penilaian_apoteker.text = terima_status_namaPanjang
        penilaian_puass.setOnClickListener {
            Toast.makeText(applicationContext, "suka", Toast.LENGTH_LONG).show()
            updateDataPenilaianSuka()
            openDialog.dismiss()
        }
        penilaian_puastidak.setOnClickListener {
            Toast.makeText(applicationContext, "tidak suka", Toast.LENGTH_LONG).show()
            updateDataPenilaianTidakSuka()
            openDialog.dismiss()
        }



//        dialog!!.setIcon(R.mipmap.ic_launcher)
//        dialog!!.setTitle("Form Biodata")
//        dialog!!.setPositiveButton("SUBMIT") { dialog, which ->
////            nama = txt_nama!!.text.toString()
////            usia = txt_usia!!.text.toString()
////            alamat = txt_alamat!!.text.toString()
////            status = txt_status!!.text.toString()
////            txt_hasil!!.text = "Nama : $nama\nUsia : $usia\nAlamat : $alamat\nStatus : $status"
//
//            dialog.dismiss()
//            dialog.dismiss()
//        }
//        dialog!!.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
        openDialog!!.show()
    }

    private fun refreshCariApoteker(millionsecon_LihatChatingan: Int) {
        var handlerLihatChatingan = Handler()
        val runnableLihatChatingan = Runnable {
//            listChatHome()
        }
        handlerLihatChatingan?.postDelayed(
            runnableLihatChatingan,
            millionsecon_LihatChatingan.toLong()
        )
    }

}






//private fun listYukNgobrol(){
//    var kirimListYuk = "yukNgobrol"
////        rv_list_yuk_ngobrol.setHasFixedSize(true)
////        items1.addAll(Data_Damy2.getData1)
////        rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this)
////        rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(this, items1)
//
//
//
//
//    var id_users = session.id.toString()
//    var status_akun_users = "setuju"
//    val gsonRetrofit = Gson_Retrofit.createRetrofit()
//    val call :Call<Value_lihat_temanCerita>
//    call = gsonRetrofit.get_lihat_temanCerita(id_users,status_akun_users)
//    call.enqueue(object : Callback<Value_lihat_temanCerita>{
//        override fun onFailure(call: Call<Value_lihat_temanCerita>, t: Throwable) {
//            Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
//            mShimmerViewContainer.stopShimmerAnimation()
//            mShimmerViewContainer.setVisibility(View.GONE)
////                progress_cari_apoteker_home.visibility = View.GONE
//        }
//
//        override fun onResponse(
//            call: Call<Value_lihat_temanCerita>,
//            response: Response<Value_lihat_temanCerita>
//        ) {
//            val dataChatHOme = response.body()
//            val value = dataChatHOme?.value
//
//            if (value.equals("1")){
////                        refreshCariApoteker(1000)
////                        Toast.makeText(applicationContext,"jalanji kah Home", Toast.LENGTH_LONG).show()
//                items1 = dataChatHOme?.response as MutableList<Model_lihat_temanCerita>
////                        progress_cari_apoteker_home.visibility = View.GONE
//                mShimmerViewContainer.stopShimmerAnimation()
//                mShimmerViewContainer.setVisibility(View.GONE)
//                rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this@Home_Main)
//                rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(this@Home_Main, items1){
////                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
//                    val modelListChatApoteker1 = Model_lihat_temanCerita(
//                        it.id_akun_apoteker,
//                        it.id_akun_users,
//                        it.jumlah_chat,
//                        it.status_akun_users,
//                        it.status_akun_apoteker,
//                        it.nama_panjang,
//                        it.alumni_apoteker,
//                        it.tahun_mulai_praktek,
//                        it.tempat_praktik,
//                        it.foto_profil,
//                        it.masa_praktek,
//                        it.foto_stra,
//                        it.nomor_stra,
//                        it.foto_sipa,
//                        it.no_sipa,
//                        it.status_satu
//                    )
//                    finish()
//                    var intent = Intent(this@Home_Main, Profil_Data_Apoteker::class.java)
//                    intent.putExtra(Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER, modelListChatApoteker1)
//                    intent.putExtra(Profil_Data_Apoteker.KEY_DATA_AKSES, kirimListYuk)
//                    startActivity(intent)
//                }
//            }else{
//                mShimmerViewContainer.stopShimmerAnimation()
//                mShimmerViewContainer.setVisibility(View.GONE)
//            }
//        }
//
//    })
//}