package com.example.rumahobat_.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Chat_Apoteker
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_lihatKonsultasi
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Hidden_ApotekerHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Adapter_LihatKonsultasi (val context: Context , var itemsKonsultasi : List<Model_lihatKonsultasi>, val clik : (Model_lihatKonsultasi)->Unit)
    : RecyclerView.Adapter<Adapter_LihatKonsultasi.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var nama_list_konsultasi : TextView
        lateinit var pengalaman_list_konsultasi : TextView
        lateinit var foto_list_yuk_apoteker_home : ImageView
        lateinit var rating_list_yuk_apoteker_home : RatingBar
        lateinit var nilai_list_yuk_apoteker_home :TextView

        lateinit var harga_list_yuk_apoteker_home : TextView
        lateinit var apoteker_list_yuk_apoteker_home : TextView
        lateinit var tahun_list_yuk_apoteker_home : TextView
        lateinit var button_chet_konsultasi_hidden : Button
        lateinit var button_chet_konsultasi : Button
        lateinit var  kirimKonsultasi : String
        lateinit var status_aktiff : String
        lateinit var id_apoteker : String
        lateinit var session : SessionManager
        fun bindView(modelLihatkonsultasi: Model_lihatKonsultasi, clik: (Model_lihatKonsultasi) -> Unit){
            pengalaman_list_konsultasi = itemView.findViewById(R.id.pengalaman_list_konsultasi)
            nama_list_konsultasi = itemView.findViewById(R.id.nama_list_konsultasi)

            button_chet_konsultasi = itemView.findViewById(R.id.button_chet_konsultasi)
            button_chet_konsultasi_hidden = itemView.findViewById(R.id.button_chet_konsultasi_hidden)
            button_chet_konsultasi_hidden.visibility = View.GONE
            button_chet_konsultasi.visibility = View.GONE
            session = SessionManager(itemView.context)
            itemView.setOnClickListener { clik(modelLihatkonsultasi) }
            id_apoteker = modelLihatkonsultasi.id_akun_apoteker
            nama_list_konsultasi.text = modelLihatkonsultasi.nama_panjang
            pengalaman_list_konsultasi.text = modelLihatkonsultasi.masa_praktek
            status_aktiff = modelLihatkonsultasi.status_satu

            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            var typeface2 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Bold.ttf")
            nama_list_konsultasi.typeface = typeface1
            pengalaman_list_konsultasi.typeface = typeface2


            itemHiddenApoteker()

            button_chet_konsultasi.setOnClickListener(View.OnClickListener {
                val intent = Intent(itemView.context, Chat_Apoteker::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, id_apoteker)
                itemView.context.startActivity(intent)

            })
        }

        private fun itemHiddenApoteker() {

//            Toast.makeText(itemView.context,session.id ,  Toast.LENGTH_LONG).show()
            var idAKun = session.id.toString()
            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
            val call : Call<Value_Hidden_ApotekerHome>
            call = gsonRetrofit.getHiddenApotekerHome(idAKun)
            call.enqueue(object : Callback<Value_Hidden_ApotekerHome> {
                override fun onFailure(call: Call<Value_Hidden_ApotekerHome>, t: Throwable) {
                    Toast.makeText(itemView.context,"Tidak Ada Koneksi",  Toast.LENGTH_LONG).show()
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
                            button_chet_konsultasi_hidden.visibility = View.VISIBLE
                            button_chet_konsultasi.visibility = View.GONE
                        }else{
//
                                if (status_aktiff.equals("aktif")){
                                    button_chet_konsultasi_hidden.visibility = View.VISIBLE
                                    button_chet_konsultasi.visibility = View.GONE
                                }else{
                                    button_chet_konsultasi_hidden.visibility = View.GONE
                                    button_chet_konsultasi.visibility = View.VISIBLE
                                }



                        }
                    }else{

                    }
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_konsultasi, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemsKonsultasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(itemsKonsultasi[position],clik)
    }
}


