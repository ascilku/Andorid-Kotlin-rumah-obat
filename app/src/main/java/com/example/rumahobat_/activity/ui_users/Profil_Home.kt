package com.example.rumahobat_.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_users.Isi_Biodata_Users
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_lihatDataUsers
import kotlinx.android.synthetic.main.profil_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profil_Home : AppCompatActivity() {
    lateinit var session : SessionManager
    lateinit var terimaFotoProfil : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.profil_home)
        session = SessionManager(this)
        var id_penilaian = session.id.toString()
//        tambahDataUsers = findViewById(R.id.tambahDataUsers)
        lihatDataUsers()
//        nama_list_chat_apoteker_home.text = session.nama
//        nomor_profil_users.text = session.nomor

        profil_image.setOnClickListener {
            dialogFotoProfil()
        }

        tambahDataUsers.setOnClickListener {
            var intent = Intent(this, Isi_Biodata_Users::class.java)
            intent.putExtra(Isi_Biodata_Users.KEY_ID_CHAT, id_penilaian)
            startActivity(intent)
            finish()
        }

        beckChatingan.setOnClickListener {
            var intent = Intent(this, Home_Main::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun lihatDataUsers() {
        var id_penilaian = session.id.toString()
        var status_akun_users = "tidak"
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatDataUsers>
        call = gsonRetrofit.get_lihatDataUsers(id_penilaian)
        call.enqueue(object : Callback<Value_lihatDataUsers> {
            override fun onFailure(call: Call<Value_lihatDataUsers>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi lihat teman", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_lihatDataUsers>,
                response: Response<Value_lihatDataUsers>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){

                    val responsePenilaian = dataLogin?.response?.get(0)
                    var terima_nama_profil   = responsePenilaian?.nama_panjang_users
                    var terima_nomorHp = responsePenilaian?.no_hp
                    var terima_usia = responsePenilaian?.usia
                    var terima_tempatLahir   = responsePenilaian?.tempat_lahir
                    var terima_dateLahir   = responsePenilaian?.date_lahir
                    var terima_jenisKelamin   = responsePenilaian?.jenis_kelamin
                    var terima_beratBadan = responsePenilaian?.berat_badan
                    var terima_tinggiBadan = responsePenilaian?.tinggi_badan
                    var terima_alamat = responsePenilaian?.alamat

                    var terima_obat = responsePenilaian?.riwayat_obat
                    var terima_penyakit = responsePenilaian?.riwayat_penyakit
                    terimaFotoProfil = responsePenilaian?.foto_users.toString()


                    nama_list_chat_apoteker_home.text = terima_nama_profil
                    nomor_profil_users.text = terima_nomorHp
                    usia.text = terima_usia
                    tempatLahir.text = terima_tempatLahir
                    jenisKelamin.text = terima_jenisKelamin
                    beratBadan.text = terima_beratBadan
                    tinggiBadan.text = terima_tinggiBadan
                    alamat.text = terima_alamat
                    obat.text = terima_obat
                    penyakit.text = terima_penyakit


//                    Toast.makeText(applicationContext, responsePenilaian?.foto_users ,Toast.LENGTH_LONG).show()
                    Glide.with(applicationContext)
                         .load(Api_Interface.KEY_URL_FOTO_USERS+terimaFotoProfil).into(profil_image)
//                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11
                }else
                {
                    nama_list_chat_apoteker_home.text = session.nama
                    nomor_profil_users.text = session.nomor
                    Toast.makeText(applicationContext, "Silahkan Lengkapi Data Anda", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun dialogFotoProfil() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.lihat_foto_profil)

        var imageTampilFoto = openDialog.findViewById<ImageView>(R.id.image_tampil_profil) !!
//        var namaTampilFoto = openDialog.findViewById<TextView>(R.id.nama_tampil_profil) !!
        openDialog.setCancelable(true)

        Glide.with(applicationContext)
            .load(Api_Interface.KEY_URL_FOTO_USERS+terimaFotoProfil).into(imageTampilFoto)

//        Toast.makeText(applicationContext, terima_status_namaPanjang, Toast.LENGTH_LONG).show()




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

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext, Home_Main::class.java)
        startActivity(intent)
        finish()
    }
}