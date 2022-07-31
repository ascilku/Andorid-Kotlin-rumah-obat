package com.example.rumahobat_.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Chat_Apoteker
import com.example.rumahobat_.adapter.RepoDiffCallback.RepoDiffCallback_ListApotekerHome
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Hidden_ApotekerHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Adapter_Home_List_Cari_Apoteker (val context: Context, var items: List<Model_List_Chat_Apoteker>, val clik : (Model_List_Chat_Apoteker)->Unit)
    :RecyclerView.Adapter<Adapter_Home_List_Cari_Apoteker.ViewHolder>() {
    private var data = listOf<Model_List_Chat_Apoteker>()

    fun updateItems(newItems: List<Model_List_Chat_Apoteker>) {
        val diffResult = DiffUtil.calculateDiff(RepoDiffCallback_ListApotekerHome(data, newItems))
        data = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var nama_list_chat_apoteker_home : TextView
        lateinit var pengalaman_list_chat_apoteker_home : TextView
        lateinit var foto_list_chat_apoteker_home : ImageView
        lateinit var rating_list_chat_apoteker_home : RatingBar
        lateinit var apoteker_list_chat_apoteker_home : TextView
        lateinit var harga_list_chat_apoteker_home : TextView
        lateinit var nilai_list_chat_apoteker_home : TextView
        lateinit var tahun_list_chat_apoteker_home : TextView
        lateinit var bottonChat_apoteker : Button
        lateinit var bottonChat_apoteker_hidden : Button
         lateinit var statusUsers : String
        var nomor1 = 1
        var nomor2 = 2
        var jumlahPoint : Int = 0
        var jumlahUsers : Int =0
        var hamil : Int = 0
        lateinit var  kirimKonsultasi : String
        lateinit var status_aktif : String
        lateinit var session : SessionManager

        var i = 0

        var handler1: Handler? = null
        var treed11: Thread? = null
        fun bindView(modelListChatApoteker: Model_List_Chat_Apoteker, clik: (Model_List_Chat_Apoteker) -> Unit){
            session = SessionManager(itemView.context)
            nama_list_chat_apoteker_home = itemView.findViewById(R.id.nama_list_chat_apoteker_home)
            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            var typeface2 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Bold.ttf")

            apoteker_list_chat_apoteker_home = itemView.findViewById(R.id.apoteker_list_chat_apoteker_home)
            pengalaman_list_chat_apoteker_home = itemView.findViewById(R.id.pengalaman_list_chat_apoteker_home)
            rating_list_chat_apoteker_home = itemView.findViewById(R.id.rating_list_chat_apoteker_home)
            harga_list_chat_apoteker_home = itemView.findViewById(R.id.harga_list_chat_apoteker_home)
            nilai_list_chat_apoteker_home = itemView.findViewById(R.id.nilai_list_chat_apoteker_home)
            tahun_list_chat_apoteker_home = itemView.findViewById(R.id.tahun_list_chat_apoteker_home)
            foto_list_chat_apoteker_home = itemView.findViewById(R.id.foto_list_chat_apoteker_home)
            bottonChat_apoteker = itemView.findViewById(R.id.bottonChat_apoteker)
            bottonChat_apoteker_hidden = itemView.findViewById(R.id.bottonChat_apoteker_hidden)

            bottonChat_apoteker.typeface = typeface1
            tahun_list_chat_apoteker_home.typeface = typeface1
            pengalaman_list_chat_apoteker_home.typeface = typeface1
            nilai_list_chat_apoteker_home.typeface = typeface1
            harga_list_chat_apoteker_home.typeface = typeface1
            apoteker_list_chat_apoteker_home.typeface = typeface2
            nama_list_chat_apoteker_home.setTypeface(typeface1)


            val id_akun_apoteker = modelListChatApoteker.id_akun_apoteker
//            nilai_list_chat_apoteker_home.text = modelListChatApoteker.jumlah_rating
//            rating_list_chat_apoteker_home.rating = modelListChatApoteker.jumlah_rating.toFloat()
            nama_list_chat_apoteker_home.text = modelListChatApoteker.nama_panjang
            pengalaman_list_chat_apoteker_home.text = modelListChatApoteker.masa_praktek
            status_aktif = modelListChatApoteker.status_satu





//            Toast.makeText(itemView.context,modelListChatApoteker.id_akun_apoteker + " " + session.id ,  Toast.LENGTH_LONG).show()


//            bottonChat_apoteker.visibility = View.GONE
//            bottonChat_apoteker_hidden.visibility = View.VISIBLE


            bottonChat_apoteker.visibility = View.GONE

            itemHiddenApoteker()


            itemView.setOnClickListener { clik(modelListChatApoteker) }
            bottonChat_apoteker.setOnClickListener(View.OnClickListener {
                kirimKonsultasi  = "Home Main"
                val intent = Intent(itemView.context, Chat_Apoteker::class.java)
//                intent.putExtra(Chat_Apoteker.KEY_AKSES_CHAT, kirimKonsultasi)
                intent.putExtra(Chat_Apoteker.KEY_ID_CHAT, id_akun_apoteker)

                itemView.context.startActivity(intent)
                (itemView.context as Activity?)?.finish()
            })

//            Toast.makeText(itemView.context, this.statusUsers,  Toast.LENGTH_LONG).show()
        }


        private fun itemHiddenApoteker() {
//            Toast.makeText(itemView.context,session.id ,  Toast.LENGTH_LONG).show()
            var idAKun = session.id.toString()
            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
            val call : Call<Value_Hidden_ApotekerHome>
            call = gsonRetrofit.getHiddenApotekerHome(idAKun)
            call.enqueue(object : Callback<Value_Hidden_ApotekerHome>{
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
                        statusUsers = responsee?.status_satu_users!!
                            if (value.equals("1")){

                                refreshCariApoteker(1000)


                                var idUsers = responsee?.nama_panjang_users

                                    if (statusUsers == "aktif"){
                                        bottonChat_apoteker.visibility = View.GONE
                                        bottonChat_apoteker_hidden.visibility = View.VISIBLE
                                        Toast.makeText(itemView.context,"aktif",  Toast.LENGTH_LONG).show()
                                    }else if(statusUsers == "tidak"){
                                        bottonChat_apoteker_hidden.visibility = View.GONE
                                     bottonChat_apoteker.visibility = View.VISIBLE

                                     Toast.makeText(itemView.context,nomor1++,  Toast.LENGTH_LONG).show()
//                                        if (status_aktif == "aktif"){
//                                            bottonChat_apoteker_hidden.visibility = View.VISIBLE
//                                            bottonChat_apoteker.visibility = View.GONE
//                                        }
//                                        if (status_aktif == "tidak"){ 294696
//                                            bottonChat_apoteker_hidden.visibility = View.GONE
//                                            bottonChat_apoteker.visibility = View.VISIBLE
//                                        }
                                    }

                            }else{

                            }
                }
            })
        }

        private fun refreshCariApoteker(millionsecon_LihatChatingan: Int) {
            var handlerLihatChatingan = Handler()
            val runnableLihatChatingan = Runnable {
                itemHiddenApoteker()
            }
            handlerLihatChatingan?.postDelayed(
                runnableLihatChatingan,
                millionsecon_LihatChatingan.toLong()
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item1_chat_apoteker, parent, false))
    }



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position],clik)
    }



}