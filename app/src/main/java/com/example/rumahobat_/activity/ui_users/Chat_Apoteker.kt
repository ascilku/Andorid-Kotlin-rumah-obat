package com.example.rumahobat_.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rumahobat_.R
import com.example.rumahobat_.adapter.Adapter_Item_Chat_Dari
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_Lihat_Chatinga
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionCoba
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat__apoteker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Chat_Apoteker : AppCompatActivity() {
    companion object{
        const val KEY_AKSES_CHAT = "key_akses_chat"
        const val KEY_ID_CHAT = "key_id_chat"
    }
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    var dialogView: View? = null
    lateinit var session : SessionManager
    lateinit var sessionCoba: SessionCoba
    lateinit var  terimaKonsultasi : String
    lateinit var  terimaKonsultasiID : String
    lateinit var  terimaKonsultasiSession : String
    lateinit var  terima_logBatasWaktuChatingan : String
    lateinit var terima_logStatus : String
    lateinit var terima_jamBatasChatingan : String

    lateinit var  terima_logAwalBatasWaktuChatinganPeringatan : String
    lateinit var  terima_logAkhirBatasWaktuChatinganPeringatan : String
    lateinit var  terima_jamBatasChatinganPeringatanPutus : String
    lateinit var  terima_jamBatasChatinganPeringatan : String
    lateinit var terima_status_namaPanjang : String
    lateinit var waktu1 : String
    lateinit var waktu11 : String
    lateinit var waktu111 : String
    var itemsLihatChatingan : MutableList<Model_Lihat_Chatinga> = mutableListOf()
    val adapter = GroupAdapter<ViewHolder>()

    var MillisecondTime: Long = 0
    var StartTime: Long = 0
    var TimeBuff: Long = 0
    var UpdateTime = 0L
    var handler: Handler? = null
    var handlerLog: Handler? = null
    var handlerPeringatan: Handler? = null
    var handlerLihatChatingan: Handler? = null

    var Seconds = 0
    var Minutes = 0
    var MilliSeconds = 0

    lateinit var penilaian_puass : ImageView
    lateinit var penilaian_puastidak : ImageView

    var handlerTime: Handler? = null


//    ini untuk treed

    var sleepp = 1860
    var nilaiLoop = 1860
    lateinit var treed111: TextView
    lateinit var treed222: TextView
    lateinit var treed333: TextView
    lateinit var treed444: TextView
    lateinit var treed555: TextView
    lateinit var treed666: TextView
    var i = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    var f = 0
    var handler1: Handler? = null
    var handler2: Handler? = null
    var handler3: Handler? = null
    var handler4: Handler? = null
    var handler5: Handler? = null
    var handler6: Handler? = null
    var treed11: Thread? = null
    var treed22: Thread? = null
    var treed33: Thread? = null
    var treed44: Thread? = null
    var treed55: Thread? = null
    var treed66: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat__apoteker)
        session = SessionManager(this)
        handlerTime = Handler()

        terimaKonsultasiID = intent.getStringExtra(KEY_ID_CHAT).toString()
        terimaKonsultasiSession = session.id.toString()

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
                        lihatChatinganDari()
                    }
                    i++
                }
            }
        };(treed11 as Thread).start()

        periksaChatingan()

        lihatNamaChatinganDari()
        lihatDataTemanCerita()



        val c1 = Calendar.getInstance()
        val simpleDateFormatDate = SimpleDateFormat("Y-MM-d HH:mm:ss", Locale.getDefault())
        waktu1 = simpleDateFormatDate.format(c1.time)

        bottom_kirimChat.setOnClickListener {
            var isiChatingan = text_kirim_chatingan.text.toString()
            var id_akun_apoteker = terimaKonsultasiID
            var idakunUsers = session.id.toString()
            var kode = "1"
            var status = "aktif"

            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
            val call : Call<Value_Input_Chat>
            call = gsonRetrofit.inputChatingan(isiChatingan,id_akun_apoteker, idakunUsers, kode, status, waktu1)
            call.enqueue(object : Callback<Value_Input_Chat>{
                override fun onFailure(call: Call<Value_Input_Chat>, t: Throwable) {
                    Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Value_Input_Chat>,
                    response: Response<Value_Input_Chat>
                ) {
                    val dataLogin = response.body()
                    val value = dataLogin?.value
                    val pesann = dataLogin?.pesan
                    if (value.equals("1")){
//                        Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
                        text_kirim_chatingan.setText("")
                    }
                }
            })
        }



//        ini untuk lihat chatingan



        //        ini untuk lihat periksa chatingan

        handler2 = Handler()

//        treed22 = object : Thread() {
//            override fun run() {
//                b = 0
//                while (b < nilaiLoop) {
//                    try {
//                        sleep(sleepp.toLong())
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                    }
//
////                        Log.d(LOG,"i" +i );
//                    handler2!!.post {
//                        periksaChatingan()
//                    }
//                    b++
//                }
//            }
//        };(treed22 as Thread).start()


//        ini untuk waktu akhir chatingan
        handler3 = Handler()

        treed33 = object : Thread() {
            override fun run() {
                c = 0
                while (c < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler3!!.post {
                        waktuSekarangPerbandingan()
                    }
                    c++
                }
            }
        };(treed33 as Thread).start()

//        ini untuk peringatan chatingan

        handler4 = Handler()

        treed44 = object : Thread() {
            override fun run() {
                d = 0
                while (d < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler4!!.post {
                        waktuSekarangPerbandinganPeringatan()
                    }
                    d++
                }
            }
        };(treed44 as Thread).start()


        handler5 = Handler()
        handler6 = Handler()


//        StartTime = SystemClock.uptimeMillis()
//        handlerTime!!.postDelayed(runnableTimerBatas, 0)

        val typeface1 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Regular.ttf")
        val typeface2 = Typeface.createFromAsset(assets,"nunito_sans/NunitoSans-Bold.ttf")
        nama_profil.typeface = typeface2
        text_kirim_chatingan.typeface = typeface1
        beriNilai.visibility = View.GONE


//        terimaKonsultasi = intent.getStringExtra(KEY_AKSES_CHAT).toString()


//        lihatLog_batasChatingan()]
//        jamSekarang()











//        Toast.makeText(applicationContext, terimaKonsultasiID, Toast.LENGTH_LONG).show()
    }

    private fun lihatDataPenilaian() {

    }

    private fun updateDataTemanCeritaTidakTerima(){
        val nilaiSuka = "tidak"
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_UpdateTemanCerita>
        call = gsonRetrofit.getupdate_temanCerita(terimaKonsultasiID,id_penilaian,nilaiSuka)
        call.enqueue(object : Callback<Value_UpdateTemanCerita> {
            override fun onFailure(call: Call<Value_UpdateTemanCerita>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_UpdateTemanCerita>,
                response: Response<Value_UpdateTemanCerita>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11



                }else
                {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun updateDataTemanCeritaTerima() {
        val nilaiSuka = "setuju"
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_UpdateTemanCerita>
        call = gsonRetrofit.getupdate_temanCerita(terimaKonsultasiID,id_penilaian,nilaiSuka)
        call.enqueue(object : Callback<Value_UpdateTemanCerita> {
            override fun onFailure(call: Call<Value_UpdateTemanCerita>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_UpdateTemanCerita>,
                response: Response<Value_UpdateTemanCerita>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11



                }else
                {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun lihatDataTemanCerita() {
        var id_penilaian = session.id.toString()
        var status_akun_users = "tidak"
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_TemanCerita>
        call = gsonRetrofit.gettemanCerita(terimaKonsultasiID,id_penilaian,status_akun_users)
        call.enqueue(object : Callback<Value_TemanCerita> {
            override fun onFailure(call: Call<Value_TemanCerita>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi lihat teman", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_TemanCerita>,
                response: Response<Value_TemanCerita>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){

                    val responsePenilaian = dataLogin?.response?.get(0)
                    var terima_status_rating   = responsePenilaian?.jumlah_chat
                    if (terima_status_rating != null) {
                        if (terima_status_rating >= "5"){
                            dialogTerimaCerita()
                        }
                    }
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11



                }else
                {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun updateDataPenilaianSuka() {
        val nilaiSuka = "1"
        var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_update_perPenilaian>
        call = gsonRetrofit.get_update_perPenilaian(id_penilaian,nilaiSuka)
        call.enqueue(object : Callback<Value_update_perPenilaian> {
            override fun onFailure(call: Call<Value_update_perPenilaian>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_update_perPenilaian>,
                response: Response<Value_update_perPenilaian>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11



                }else
                {
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
        call = gsonRetrofit.get_update_perPenilaian(id_penilaian,nilaiTidakSuka)
        call.enqueue(object : Callback<Value_update_perPenilaian> {
            override fun onFailure(call: Call<Value_update_perPenilaian>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_update_perPenilaian>,
                response: Response<Value_update_perPenilaian>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesan = dataLogin?.pesan

                if (value.equals("1")){


                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11



                }else
                {
                    Toast.makeText(applicationContext, pesan, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun waktuSekarangPerbandingan() {
//        refresh_logBatasChatingan(8000)
        val c1 = Calendar.getInstance()
//        val simpleDateFormatDate = SimpleDateFormat("Y-MM-d HH:mm:ss", Locale.getDefault())
//        waktu11 = simpleDateFormatDate.format(c1.time)

        val pemanggalanMenit = SimpleDateFormat("HH:mm")
        waktu11 = pemanggalanMenit.format(c1.time)
//        val angka1 = Integer.valueOf(waktu11)


        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_lihatLog_batasChatingan>
        call = gsonRetrofit.getlihatLog_batasChatingan(terimaKonsultasiID,terimaKonsultasiSession)
        call.enqueue(object : Callback<Value_lihatLog_batasChatingan> {
            override fun onFailure(call: Call<Value_lihatLog_batasChatingan>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi nama", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_lihatLog_batasChatingan>,
                response: Response<Value_lihatLog_batasChatingan>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value

                if (value.equals("1")){
                    val pesan = dataLogin?.response?.get(0)
                    terima_logBatasWaktuChatingan   = pesan?.waktu_akhir_chatingan.toString()
                    terima_logStatus                = pesan?.status_batasWaktuChatingan.toString()
                    terima_jamBatasChatingan        = pesan?.jam_akhir_chatingan.toString()

//                    Toast.makeText(applicationContext, "batas chatingan "+terima_jamBatasChatingan +" , "+ waktu11, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11

                                if (waktu11 == terima_jamBatasChatingan){
                                    text_kirim_chatingan.visibility = View.GONE
                                    bottom_kirimChat.visibility = View.GONE
                                    foto_chatingan.visibility = View.GONE
                                    beriNilai.visibility = View.VISIBLE
                                    beriNilai.setOnClickListener {
                                        lihatDataPenilaian()
                                        dialogPenilaian()
                                    }
                                    inputakhiriPesan()
                                }
                }
            }
        })

    }

    private fun waktuSekarangPerbandinganPeringatan() {
//        refresh_logPeringatanChatingan(7000)
        val c1 = Calendar.getInstance()
//        val simpleDateFormatDate = SimpleDateFormat("Y-MM-d HH:mm:ss", Locale.getDefault())
//        waktu11 = simpleDateFormatDate.format(c1.time)

        val pemanggalanMenit = SimpleDateFormat("HH:mm")
        waktu111 = pemanggalanMenit.format(c1.time)
//        val angka1 = Integer.valueOf(waktu11)


        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Log_peringatanChatingan>
        call = gsonRetrofit.get_lihatLog_peringatanChatingan(terimaKonsultasiID,terimaKonsultasiSession)
        call.enqueue(object : Callback<Value_Log_peringatanChatingan> {
            override fun onFailure(call: Call<Value_Log_peringatanChatingan>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi nama", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_Log_peringatanChatingan>,
                response: Response<Value_Log_peringatanChatingan>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value

                if (value.equals("1")){

                    val pesan = dataLogin?.response?.get(0)
                    terima_logAwalBatasWaktuChatinganPeringatan   = pesan?.waktu_awal_peringatanChatingan.toString()
                    terima_logAkhirBatasWaktuChatinganPeringatan  = pesan?.waktu_akhir_peringatanChatingan.toString()
                    terima_jamBatasChatinganPeringatanPutus        = pesan?.jam_akhir_chatingan.toString()
                    terima_jamBatasChatinganPeringatan        = pesan?.jam_pertengahanChatingan.toString()

//                    Toast.makeText(applicationContext, "peringatan putus chatingan "+  waktu111+" "+terima_jamBatasChatinganPeringatan, Toast.LENGTH_LONG).show()
//                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11
                    if (waktu111 == terima_jamBatasChatinganPeringatanPutus){
                        text_kirim_chatingan.visibility = View.GONE
                        bottom_kirimChat.visibility = View.GONE
                        foto_chatingan.visibility = View.GONE
                        beriNilai.visibility = View.VISIBLE

                            beriNilai.setOnClickListener {
                                lihatDataPenilaian()
                                dialogPenilaian()
                            }
                        inputakhiriPesan()
//                        Toast.makeText(applicationContext, "terima kasih telah menggunakan rumah obat. Jika ada yg ingin ditanyakan, silahkan request ulang", Toast.LENGTH_LONG).show()

                    }

                    if (waktu111 == terima_jamBatasChatinganPeringatan){
//                        Toast.makeText(applicationContext, "apakah mau lanjut konsul atau tidak..!!", Toast.LENGTH_LONG).show()
                        inputPesanPeringatan()
                    }


                }
            }
        })

    }

//    private fun lihatLog_batasChatingan() {
//        val gsonRetrofit = Gson_Retrofit.createRetrofit()
//        val call : Call<Value_lihatLog_batasChatingan>
//        call = gsonRetrofit.getlihatLog_batasChatingan(terimaKonsultasiID,terimaKonsultasiSession)
//        call.enqueue(object : Callback<Value_lihatLog_batasChatingan>{
//            override fun onFailure(call: Call<Value_lihatLog_batasChatingan>, t: Throwable) {
//                Toast.makeText(applicationContext,"Tidak Ada Koneksi nama", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<Value_lihatLog_batasChatingan>,
//                response: Response<Value_lihatLog_batasChatingan>
//            ) {
//                val dataLogin = response.body()
//                val value = dataLogin?.value
//                val pesan = dataLogin?.response?.get(0)
//                if (value.equals("1")){
//                    terima_logBatasWaktuChatingan = pesan?.waktu_akhir_chatingan.toString()
////                    Toast.makeText(applicationContext,, Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        })
//    }

    private fun jamSekarang() {



    }

    private fun periksaChatingan() {
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_periksaChatingan>
        call = gsonRetrofit.getperiksaChatingan(terimaKonsultasiID,terimaKonsultasiSession)
        call.enqueue(object : Callback<Value_periksaChatingan>{
            override fun onFailure(call: Call<Value_periksaChatingan>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<Value_periksaChatingan>,
                response: Response<Value_periksaChatingan>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesann = dataLogin?.pesan
                    if (value.equals("1")){
                        Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
//                            inputBaruChatingan()
//                        refresh_logBatasChatingan(5000)
//                        refresh_logPeringatanChatingan(6000)
//                        refreshLihatChatingan(4000)
//                        Toast.makeText(applicationContext,"Silahkan Konsultasi Dengan Opoteker Terbaik Kami", Toast.LENGTH_SHORT).show()
                    }
            }
        })
    }

    private fun inputBaruChatingan() {
        var isiChatingan = "Halo"+" "+session.nama+" "+"Silahkan Kosultasi Dengan Apoteker Terbaik Kami"
        var id_akun_apoteker = terimaKonsultasiID
        var idakunUsers = session.id.toString()
        var kodeApoteker = "2"
        var status = "aktif"

        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Input_Chat>
        call = gsonRetrofit.inputChatingan(isiChatingan, id_akun_apoteker, idakunUsers, kodeApoteker, status, waktu1)
        call.enqueue(object : Callback<Value_Input_Chat>{
            override fun onFailure(call: Call<Value_Input_Chat>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_Input_Chat>,
                response: Response<Value_Input_Chat>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesann = dataLogin?.pesan
                if (value.equals("1")){
//                    Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
//                    reset!!.isEnabled = false


//
//                    refreshLihatChatingan(3000)
                }
            }
        })
    }


    private fun inputakhiriPesan() {
        var isiChatingan = text_kirim_chatingan.text.toString()
        var id_akun_apoteker = terimaKonsultasiID
        var idakunUsers = session.id.toString()
        var isiPesanAkhir = "Maaf.!! Sesi Habis. Terimah Kasih Sudah Konsultasi Dengan Apoteker Terbaik Kami"


        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_akhiriPesan>
        call = gsonRetrofit.getakhiriPesan(id_akun_apoteker, idakunUsers, isiPesanAkhir)
        call.enqueue(object : Callback<Value_akhiriPesan>{
            override fun onFailure(call: Call<Value_akhiriPesan>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_akhiriPesan>,
                response: Response<Value_akhiriPesan>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesann = dataLogin?.pesan
                if (value.equals("1")){
//                    Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
//                    text_kirim_chatingan.setText("")
                }
            }
        })
    }

    private fun inputPesanPeringatan() {
        var isiChatingan = text_kirim_chatingan.text.toString()
        var id_akun_apoteker = terimaKonsultasiID
        var idakunUsers = session.id.toString()
        var isiPesanAkhir = "Maaf.!! Apakah Mau Lanjut Konsul atau Tidak"


        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_LihatPesanPeringatan>
        call = gsonRetrofit.get_lihatPesanPerinatan(id_akun_apoteker, idakunUsers, isiPesanAkhir)
        call.enqueue(object : Callback<Value_LihatPesanPeringatan>{
            override fun onFailure(call: Call<Value_LihatPesanPeringatan>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_LihatPesanPeringatan>,
                response: Response<Value_LihatPesanPeringatan>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value
                val pesann = dataLogin?.pesan
                if (value.equals("1")){
//                    Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
//                    text_kirim_chatingan.setText("")
                }
            }
        })
    }

//    private fun inputChatingan() {
//        var isiChatingan = text_kirim_chatingan.text.toString()
//        var id_akun_apoteker = terimaKonsultasiID
//        var idakunUsers = session.id.toString()
//        var kode = "1"
//        var status = "aktif"
//
//        val gsonRetrofit = Gson_Retrofit.createRetrofit()
//        val call : Call<Value_Input_Chat>
//        call = gsonRetrofit.inputChatingan(isiChatingan,id_akun_apoteker, idakunUsers, kode, status, waktu1)
//        call.enqueue(object : Callback<Value_Input_Chat>{
//            override fun onFailure(call: Call<Value_Input_Chat>, t: Throwable) {
//                Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(
//                call: Call<Value_Input_Chat>,
//                response: Response<Value_Input_Chat>
//            ) {
//                val dataLogin = response.body()
//                val value = dataLogin?.value
//                val pesann = dataLogin?.pesan
//                    if (value.equals("1")){
////                        Toast.makeText(applicationContext,pesann, Toast.LENGTH_SHORT).show()
//                        text_kirim_chatingan.setText("")
//                    }
//            }
//        })
//    }


    private fun lihatNamaChatinganDari() {
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Data_Periwayat>
        call = gsonRetrofit.getNama(terimaKonsultasiID)
        call.enqueue(object : Callback<Value_Data_Periwayat>{
            override fun onFailure(call: Call<Value_Data_Periwayat>, t: Throwable) {
                Toast.makeText(applicationContext,"Tidak Ada Koneksi nama", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Value_Data_Periwayat>,
                response: Response<Value_Data_Periwayat>
            ) {
                val dataLogin = response.body()
                val value = dataLogin?.value

                    if (value.equals("1")){
                        val pesan = dataLogin?.response?.get(0)
                        nama_profil.text = pesan?.nama_panjang
                    }
            }
        })
    }


    fun lihatChatinganDari(){
        val c1 = Calendar.getInstance()
        val simpleDateFormatDate = SimpleDateFormat("Y-MM-d HH:mm:ss", Locale.getDefault())
        waktu1 = simpleDateFormatDate.format(c1.time)
//        Toast.makeText(applicationContext,terimaKonsultasiSession, Toast.LENGTH_SHORT).show()
//        Toast.makeText(applicationContext,terimaKonsultasiID+" "+ terimaKonsultasiSession, Toast.LENGTH_SHORT).show()
//        val idApoteker = session.id
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Lihat_Chatingan>
        call = gsonRetrofit.getChatingan(terimaKonsultasiID, terimaKonsultasiSession)
        call.enqueue(object : Callback<Value_Lihat_Chatingan> {
            override fun onFailure(call: Call<Value_Lihat_Chatingan>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Value_Lihat_Chatingan>,
                response: Response<Value_Lihat_Chatingan>
            ) {
//                refreshLihatChatingan(2000)
//                val posisi = adapter
                val dataLihatChat = response.body()
                val value = dataLihatChat?.value

                    if (value == "1"){




                       itemsLihatChatingan = dataLihatChat?.response as MutableList<Model_Lihat_Chatinga>
                                        rv_container_chatingan.layoutManager = LinearLayoutManager(this@Chat_Apoteker)
                                        rv_container_chatingan.adapter = Adapter_Item_Chat_Dari(this@Chat_Apoteker, itemsLihatChatingan)
                                        rv_container_chatingan.scrollToPosition((itemsLihatChatingan.size -1))
//                        Toast.makeText(applicationContext,"jalan ji", Toast.LENGTH_SHORT).show()

                    }
            }

        })



    }

    

//     private fun refreshLihatChatingan(millionsecon_LihatChatingan: Int) {
//         handlerLihatChatingan = Handler()
//        val runnableLihatChatingan = Runnable {
//            lihatChatinganDari()
//        }
//         handlerLihatChatingan?.postDelayed(runnableLihatChatingan, millionsecon_LihatChatingan.toLong())
//    }
//
//    private fun refresh_logBatasChatingan(millionsecon_logBatasChatingan: Int) {
//        handlerLog = Handler()
//        val runnable_logBatasChatingan = Runnable {
//            waktuSekarangPerbandingan()
////            waktuSekarangPerbandinganPeringatan()
//        }
//        handlerLog?.postDelayed(runnable_logBatasChatingan, millionsecon_logBatasChatingan.toLong())
//    }
//
//    private fun refresh_logPeringatanChatingan(millionsecon_logPeringatanChatingan: Int) {
//        handlerPeringatan = Handler()
//        val runnable_logPeringatanChatingan = Runnable {
//            waktuSekarangPerbandinganPeringatan()
//        }
//        handlerPeringatan?.postDelayed(runnable_logPeringatanChatingan, millionsecon_logPeringatanChatingan.toLong())
//    }

    var runnableTimerBatas: Runnable = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime
            UpdateTime = TimeBuff + MillisecondTime
            Seconds = (UpdateTime / 1000).toInt()
            Minutes = Seconds / 60
            Seconds = Seconds % 60
            MilliSeconds = (UpdateTime % 1000).toInt()

//            nama_profil.text = "" + Minutes + ":"+ String.format("%02d", Seconds) + ":" + String.format("%02d", MilliSeconds)

            handlerTime!!.postDelayed(this, 0)
        }
    }

    private fun dialogPenilaian() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.penilaian_apoteker)

        penilaian_puass = openDialog.findViewById(R.id.penilaian_puas) !!
        penilaian_puastidak = openDialog.findViewById(R.id.penilaiantidakPuas) !!
        var nama_penilaian_apoteker = openDialog.findViewById<TextView>(R.id.nama_penilaian_apoteker) !!
        openDialog.setCancelable(false)
                var id_penilaian = session.id.toString()
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                val call : Call<Value_lihatPenilaian>
                call = gsonRetrofit.get_lihatPenilaian(id_penilaian)
                call.enqueue(object : Callback<Value_lihatPenilaian> {
                    override fun onFailure(call: Call<Value_lihatPenilaian>, t: Throwable) {
                        Toast.makeText(applicationContext,"Tidak Ada Koneksi", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<Value_lihatPenilaian>,
                        response: Response<Value_lihatPenilaian>
                    ) {
                        val dataLogin = response.body()
                        val value = dataLogin?.value

                        if (value.equals("1")){
                            val responsePenilaian = dataLogin?.response?.get(0)
                            var terima_status_rating   = responsePenilaian?.status_rating
                            terima_status_namaPanjang = responsePenilaian?.nama_panjang.toString()
                                Toast.makeText(applicationContext, terima_status_namaPanjang, Toast.LENGTH_LONG).show()
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
        //                    Toast.makeText(applicationContext, terima_status_rating, Toast.LENGTH_LONG).show()
        //                    nama_profil.text = terima_jamBatasChatingan+" , " +" "+ waktu11
        //                    if (terima_status_rating == "akhiri"){
        //                        dialogPenilaian()
        //                    }


                        }else
                        {

                        }
                    }
                })




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


    private fun dialogTerimaCerita() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.penilaian_teman_cerita)

        penilaian_puass = openDialog.findViewById(R.id.terima) !!
        penilaian_puastidak = openDialog.findViewById(R.id.tidakTerima) !!
        var nama_penilaian_apoteker = openDialog.findViewById<TextView>(R.id.nama_penilaian_apoteker) !!
        openDialog.setCancelable(false)

        penilaian_puass.setOnClickListener {
            Toast.makeText(applicationContext, "suka", Toast.LENGTH_LONG).show()
            updateDataTemanCeritaTerima()
            openDialog.dismiss()
        }
        penilaian_puastidak.setOnClickListener {
            Toast.makeText(applicationContext, "tidak suka", Toast.LENGTH_LONG).show()
            updateDataTemanCeritaTidakTerima()
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

    override fun onBackPressed() {
        super.onBackPressed()
//        if (terimaKonsultasi == "Home Main"){
//            val intent = Intent(applicationContext, Home_Main::class.java)
//            startActivity(intent)
//        }else{
//            val intent = Intent(applicationContext, Konsultasi::class.java)
//            startActivity(intent)
//        }
        val intent = Intent(applicationContext, Home_Main::class.java)
        startActivity(intent)
        finish()
    }
}
