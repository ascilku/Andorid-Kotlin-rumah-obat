package com.example.rumahobat_.activity

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahobat_.R
import com.example.rumahobat_.adapter.*
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.data_damy.Data_Damy3
import com.example.rumahobat_.data_damy.Data_Damy_Tahukah
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.model_damy.Model_Damy2
import com.example.rumahobat_.model_damy.Model_Damy3
import com.example.rumahobat_.model_damy.Model_Damy4
import com.example.rumahobat_.model_damy.Model_Damy_Tahukah
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_List_Chat_Apoteker
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.home_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Rumah_Obat : AppCompatActivity(), Drawer.OnDrawerItemClickListener {
    lateinit var drawer : Drawer.Result
    private var accountHeader : AccountHeader.Result? = null

    //    var items : MutableList<Model_Damy> = mutableListOf()
    var items1 : MutableList<Model_Damy2> = mutableListOf()
    var items2 : MutableList<Model_Damy3> = mutableListOf()
    var items3 : MutableList<Model_Damy4> = mutableListOf()
    var items4 : MutableList<Model_Damy_Tahukah> = mutableListOf()
    lateinit var session : SessionManager
    var items : MutableList<Model_List_Chat_Apoteker> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_rumah_obat)
        session = SessionManager(this)

        val typeface1 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-SemiBold.ttf")
        val typeface2 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Bold.ttf")
        val typeface3 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Regular.ttf")
        text_konsultasi_cariApoteker_home.typeface = typeface1
//        text_teman_cerita_cariApoteker_home.typeface = typeface1
//        text_forum_cariApoteker_home.typeface = typeface1
        cariApoteker_home.typeface = typeface2
        klik_cariApoteker_home.typeface = typeface3
        yuk_list_yuk_ngobrol.typeface = typeface2
        mulai_berdiskusi_list_yuk_ngobrol.typeface = typeface2

        if (session.isLoggedIn) {
            accountHeader = AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                    ProfileDrawerItem().withName(session.nama).withIcon(resources.getDrawable(R.drawable.logo))
                )
                .build()
        }

        if (!session.isLoggedIn) {
            finish()
            val intent = Intent(this, Slide_Awal::class.java)
            startActivity(intent)
        }

        drawer = Drawer()
            .withActivity(this)
            .withToolbar(idToolbar as androidx.appcompat.widget.Toolbar?)
            .withDisplayBelowToolbar(false)
            .withActionBarDrawerToggleAnimated(true)
            .withDrawerGravity(Gravity.LEFT)
            .withSavedInstance(savedInstanceState)
            .withAccountHeader(accountHeader)
            .withSelectedItem(0)
            .build()
        drawer.addItem(
            PrimaryDrawerItem().withName("Pengaturan")
                .withIcon(resources.getDrawable(R.drawable.ic_settings_black_24dp))
        )
        drawer.addItem(
            PrimaryDrawerItem().withName("Bantuan")
                .withIcon(resources.getDrawable(R.drawable.ic_help_black_24dp))
        )

        if (session.isLoggedIn) {
            drawer.addItem(
                PrimaryDrawerItem().withName("Log Out")
                    .withIcon(resources.getDrawable(R.drawable.ic_power_settings_new_black_24dp))
            )
        }else{
            drawer.addItem(
                PrimaryDrawerItem().withName("Log In")
                    .withIcon(resources.getDrawable(R.drawable.ic_power_settings_new_black_24dp))
            )
        }

        drawer.onDrawerItemClickListener = this

        listChatHome()
        listYukNgobrol()
        listMulaiDiskusi()
//        listFavorit()
        listTahukah()
    }

    private fun listChatHome(){
//        progress_cari_apoteker_home.visibility = View.VISIBLE
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_List_Chat_Apoteker>?
        call = gsonRetrofit.getDataListChatApoteker()
        call?.enqueue(object : Callback<Value_List_Chat_Apoteker> {
            override fun onFailure(call: Call<Value_List_Chat_Apoteker>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Value_List_Chat_Apoteker>,
                response: Response<Value_List_Chat_Apoteker>
            ) {
                val dataChatHOme = response.body()
                val value = dataChatHOme?.value
                items = dataChatHOme?.response as MutableList<Model_List_Chat_Apoteker>
                if (value.equals("1")){
//                    progress_cari_apoteker_home.visibility = View.GONE
                    rv_list_chat_home.layoutManager = LinearLayoutManager(this@Home_Rumah_Obat, LinearLayoutManager.HORIZONTAL, false)
                    rv_list_chat_home.adapter = Adapter_Home_List_Cari_Apoteker(this@Home_Rumah_Obat, items){
//                            Toast.makeText(applicationContext,it.nama_panjang,Toast.LENGTH_SHORT).show()
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
                        var intent = Intent(this@Home_Rumah_Obat, Profil_Data_Apoteker::class.java)
                        intent.putExtra(Profil_Data_Apoteker.KEY_DATA_PROFIL_APOTEKER, modelListChatApoteker1)
                        finish()
                        startActivity(intent)

                    }
                }else{
                    Toast.makeText(applicationContext, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun listYukNgobrol(){
//        rv_list_yuk_ngobrol.setHasFixedSize(true)
//        items1.addAll(Data_Damy2.getData1)
//        rv_list_yuk_ngobrol.layoutManager = LinearLayoutManager(this)
//        rv_list_yuk_ngobrol.adapter = Adapter_Home_List_Yuk_Ngobrol_Apoteker(this, items1)
    }

    private fun listMulaiDiskusi(){
        rv_list_mulai_diskusi_ngobrol.setHasFixedSize(true)
        items2.addAll(Data_Damy3.getDataDiskusi)
        rv_list_mulai_diskusi_ngobrol.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        rv_list_mulai_diskusi_ngobrol.adapter = Adapter_Home_List_Mari_Diskusi_Apoteker(this, items2)
    }

//    private fun listFavorit(){
//        rv_list_favorit_ngobrol.setHasFixedSize(true)
//        items3.addAll(Data_Damy4.getData)
//        rv_list_favorit_ngobrol.layoutManager = LinearLayoutManager(this,
//            LinearLayoutManager.HORIZONTAL,false)
//        rv_list_favorit_ngobrol.adapter = Adapter_Home_List_favorit_Apoteker(this, items3)
//    }

    private fun listTahukah(){
        rv_list_tahukah_ngobrol.setHasFixedSize(true)
        items4.addAll(Data_Damy_Tahukah.getDataTahukah)
        rv_list_tahukah_ngobrol.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
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


}
