package com.example.rumahobat_.adapter

import android.app.Activity
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
import com.example.rumahobat_.model.Model_lihat_riwayatApoteker
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Hidden_ApotekerHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Adapter_Home_List_Yuk_Ngobrol_Apoteker (val context: Context, var items: List<Model_lihat_riwayatApoteker>, val clik : (Model_lihat_riwayatApoteker)->Unit)
    : RecyclerView.Adapter<Adapter_Home_List_Yuk_Ngobrol_Apoteker.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item1_yuk_ngobrol, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position],clik)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var nama_list_yuk_apoteker_home : TextView
        lateinit var pengalaman_list_yuk_apoteker_home : TextView
        lateinit var foto_list_yuk_apoteker_home : ImageView
        lateinit var rating_list_yuk_apoteker_home : RatingBar
        lateinit var nilai_list_yuk_apoteker_home :TextView
        lateinit var button_chet_yuk : Button
        lateinit var harga_list_yuk_apoteker_home : TextView
        lateinit var apoteker_list_yuk_apoteker_home : TextView
        lateinit var tahun_list_yuk_apoteker_home : TextView
        lateinit var session : SessionManager
        lateinit var statusApoteker : String
        fun bindView(modelLihatTemancerita: Model_lihat_riwayatApoteker, clik: (Model_lihat_riwayatApoteker) -> Unit){
            tahun_list_yuk_apoteker_home = itemView.findViewById(R.id.tahun_list_yuk_apoteker_home)
            nama_list_yuk_apoteker_home = itemView.findViewById(R.id.nama_list_yuk_apoteker_home)
            session = SessionManager(itemView.context)
            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            var typeface2 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Bold.ttf")

            harga_list_yuk_apoteker_home = itemView.findViewById(R.id.harga_list_yuk_apoteker_home)
            apoteker_list_yuk_apoteker_home = itemView.findViewById(R.id.apoteker_list_yuk_apoteker_home)
            nilai_list_yuk_apoteker_home = itemView.findViewById(R.id.nilai_list_yuk_apoteker_home)
            pengalaman_list_yuk_apoteker_home = itemView.findViewById(R.id.pengalaman_list_yuk_apoteker_home)
            rating_list_yuk_apoteker_home = itemView.findViewById(R.id.rating_list_yuk_apoteker_home)
            button_chet_yuk = itemView.findViewById(R.id.button_chet_yuk)

            tahun_list_yuk_apoteker_home.typeface = typeface1
            nama_list_yuk_apoteker_home.typeface = typeface1
            harga_list_yuk_apoteker_home.typeface = typeface1
            apoteker_list_yuk_apoteker_home.typeface = typeface2
            nilai_list_yuk_apoteker_home.typeface = typeface1
            pengalaman_list_yuk_apoteker_home.typeface = typeface1
            button_chet_yuk.typeface = typeface1
            button_chet_yuk.visibility = View.GONE

            nama_list_yuk_apoteker_home.text =modelLihatTemancerita.nama_panjang
            pengalaman_list_yuk_apoteker_home.text = modelLihatTemancerita.masa_praktek.toString()
            statusApoteker = modelLihatTemancerita.status_satu
//            rating_list_yuk_apoteker_home.rating = modelLihatTemancerita.reting1.toFloat()

            itemView.setOnClickListener { clik(modelLihatTemancerita) }
            itemHiddenApoteker()
            button_chet_yuk.setOnClickListener(View.OnClickListener {
//                kirimKonsultasi  = "Home Main"
                val intent = Intent(itemView.context, Chat_Apoteker::class.java)
//                intent.putExtra(Chat_Apoteker.KEY_AKSES_CHAT, kirimKonsultasi)
                intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, modelLihatTemancerita.id_akun_apoteker)

                itemView.context.startActivity(intent)
                (itemView.context as Activity?)?.finish()
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
//                            bottonChat_apoteker_hidden.visibility = View.VISIBLE
                            button_chet_yuk.visibility = View.GONE
                        }else{
                            if (statusApoteker == "aktif"){
//                                bottonChat_apoteker_hidden.visibility = View.VISIBLE
                                button_chet_yuk.visibility = View.GONE
                            }else{
//                                bottonChat_apoteker_hidden.visibility = View.GONE
                                button_chet_yuk.visibility = View.VISIBLE
                            }
                        }
                    }else{

                    }
                }
            })
        }
    }
}