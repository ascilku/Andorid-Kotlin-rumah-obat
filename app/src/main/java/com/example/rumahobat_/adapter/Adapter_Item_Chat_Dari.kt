package com.example.rumahobat_.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahobat_.R
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_Lihat_Chatinga
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_lihatLog_batasChatingan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Adapter_Item_Chat_Dari (val context: Context, var items: List<Model_Lihat_Chatinga>)
    : RecyclerView.Adapter<Adapter_Item_Chat_Dari.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var pesan_chat_dari11 : TextView
        lateinit var pesan_chat_ke11 : TextView
        lateinit var pesan_chat_akhiri : TextView
        lateinit var pesan_chat_dari1_akhiri : TextView
        lateinit var pesan_chat_ke1_akhiri : TextView

        lateinit var pesan_chat_peringatan_akhiri : TextView
        lateinit var pesan_chat_peringatan : TextView

        lateinit var id_akun_apoteker : String
        lateinit var  terima_logBatasWaktuChatingan : String
        lateinit var  terima_logStatus : String
        lateinit var  terima_logPeringatan : String
        lateinit var  status : String
        lateinit var session : SessionManager
        lateinit var  terimaKonsultasiSession : String
        lateinit var waktu1 : String


        fun bindView(modelLihatChatinga:  Model_Lihat_Chatinga){
            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            pesan_chat_dari11 = itemView.findViewById(R.id.pesan_chat_dari1)
            pesan_chat_ke11 = itemView.findViewById(R.id.pesan_chat_ke1)
            pesan_chat_akhiri = itemView.findViewById(R.id.pesan_chat_akhiri)
            pesan_chat_dari1_akhiri = itemView.findViewById(R.id.pesan_chat_dari1_akhiri)
            pesan_chat_ke1_akhiri = itemView.findViewById(R.id.pesan_chat_ke1_akhiri)

            pesan_chat_peringatan_akhiri = itemView.findViewById(R.id.pesan_chat_peringatan_akhiri)
            pesan_chat_peringatan = itemView.findViewById(R.id.pesan_chat_peringatan)

            session = SessionManager(itemView.context)
            terimaKonsultasiSession = session.id.toString()
            pesan_chat_dari11.typeface = typeface1
            pesan_chat_ke11.typeface = typeface1
            val kode_aksess = modelLihatChatinga.kode_akses_chat
            val kode_aksesss = modelLihatChatinga.kode_akses_chat
            pesan_chat_dari11.text = modelLihatChatinga.isi
            id_akun_apoteker = modelLihatChatinga.id_akun_apoteker
            terima_logStatus = modelLihatChatinga.isi_pesanTerakhir
            terima_logPeringatan = modelLihatChatinga.isi_pesanPeringatan
            status = modelLihatChatinga.status
            pesan_chat_ke11.visibility = View.GONE
            pesan_chat_dari11.visibility = View.GONE
            pesan_chat_akhiri.visibility = View.GONE
            pesan_chat_dari1_akhiri.visibility = View.GONE
            pesan_chat_ke1_akhiri.visibility = View.GONE


            if (kode_aksess == "1" && status == "aktif"){
                pesan_chat_dari11.text = modelLihatChatinga.isi
                pesan_chat_ke11.visibility = View.GONE
                pesan_chat_ke1_akhiri.visibility = View.GONE
                pesan_chat_dari11.visibility = View.VISIBLE
                pesan_chat_akhiri.visibility = View.GONE
                pesan_chat_dari1_akhiri.visibility = View.GONE

                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE

            }
            else if (kode_aksess == "1" && status == "kadaluarsa"){
                pesan_chat_dari1_akhiri.text = modelLihatChatinga.isi
                pesan_chat_ke11.visibility = View.GONE
                pesan_chat_ke1_akhiri.visibility = View.GONE
                pesan_chat_dari11.visibility = View.GONE
                pesan_chat_akhiri.visibility = View.GONE
                pesan_chat_dari1_akhiri.visibility = View.VISIBLE

                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE
            }
//            else {
//                pesan_chat_ke11.text = modelLihatChatinga.isi
//                pesan_chat_dari11.visibility = View.GONE
//            }
            if (kode_aksesss == "2" && status == "aktif"){
                pesan_chat_akhiri.visibility = View.GONE
                pesan_chat_ke11.text = modelLihatChatinga.isi
                pesan_chat_dari11.visibility = View.GONE
                pesan_chat_ke11.visibility = View.VISIBLE
                pesan_chat_ke1_akhiri.visibility = View.GONE

                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE
            }

            else if (kode_aksess == "2" && status == "kadaluarsa"){
                pesan_chat_ke1_akhiri.visibility = View.GONE
                pesan_chat_ke1_akhiri.text = modelLihatChatinga.isi
                pesan_chat_dari11.visibility = View.GONE
                pesan_chat_ke11.visibility = View.GONE

                pesan_chat_ke1_akhiri.visibility = View.VISIBLE

                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE
            }



            if (terima_logStatus == ""){
                pesan_chat_akhiri.visibility = View.GONE
            }else{
                pesan_chat_akhiri.text = modelLihatChatinga.isi_pesanTerakhir
                pesan_chat_akhiri.visibility = View.VISIBLE
            }

            if (terima_logPeringatan == "" && status == "aktif"){
                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE
            }else if (terima_logPeringatan != "" && status == "aktif"){
                pesan_chat_peringatan.text = modelLihatChatinga.isi_pesanPeringatan
                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.VISIBLE
            }

            if (terima_logPeringatan == "" && status == "kadaluarsa"){
                pesan_chat_peringatan_akhiri.visibility = View.GONE
                pesan_chat_peringatan.visibility = View.GONE
            }else if (terima_logPeringatan != "" && status == "kadaluarsa"){
                pesan_chat_peringatan_akhiri.text = modelLihatChatinga.isi_pesanPeringatan
                pesan_chat_peringatan_akhiri.visibility = View.VISIBLE
                pesan_chat_peringatan.visibility = View.GONE
            }




//            lihatLog_batasChatingan()

//            waktuSekarangPerbandingan()
        }

        private fun waktuSekarangPerbandingan() {
            val c1 = Calendar.getInstance()
            val simpleDateFormatDate = SimpleDateFormat("Y-MM-d H:m:s", Locale.getDefault())
            waktu1 = simpleDateFormatDate.format(c1.time)



            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
                val call : Call<Value_lihatLog_batasChatingan>
                call = gsonRetrofit.getlihatLog_batasChatingan(id_akun_apoteker,terimaKonsultasiSession)
                call.enqueue(object : Callback<Value_lihatLog_batasChatingan> {
                    override fun onFailure(call: Call<Value_lihatLog_batasChatingan>, t: Throwable) {
                        Toast.makeText(itemView.context,"Tidak Ada Koneksi nama", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<Value_lihatLog_batasChatingan>,
                        response: Response<Value_lihatLog_batasChatingan>
                    ) {
                        val dataLogin = response.body()
                        val value = dataLogin?.value
                        val pesan = dataLogin?.response?.get(0)
                        if (value.equals("1")){
                            terima_logBatasWaktuChatingan   = pesan?.waktu_akhir_chatingan.toString()
                            terima_logStatus                = pesan?.status_batasWaktuChatingan.toString()
                            Toast.makeText(itemView.context, terima_logBatasWaktuChatingan +" "+ waktu1, Toast.LENGTH_LONG).show()
                                if (terima_logStatus != "aktif"){
                                    pesan_chat_akhiri.visibility = View.VISIBLE
                                }

//                                if (terima_logBatasWaktuChatingan == waktu1){
//                                    pesan_chat_akhiri.visibility = View.VISIBLE
//                                }
                        }
                    }
                })

        }

        private fun lihatLog_batasChatingan() {
//            Toast.makeText(itemView.context, id_akun_apoteker +" "+terimaKonsultasiSession, Toast.LENGTH_LONG).show()

        }

//        private fun refresh_logBatasChatingan(millionsecon_logBatasChatingan: Int) {
//            var handlerLog = Handler()
//            val runnable_logBatasChatingan = Runnable {
//                lihatLog_batasChatingan()
//            }
//            handlerLog?.postDelayed(runnable_logBatasChatingan, millionsecon_logBatasChatingan.toLong())
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.iitem_chat_dari, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }


}
