package com.example.rumahobat_.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahobat_.R
import com.example.rumahobat_.model.Model_Lihat_Chatinga
import com.example.rumahobat_.model.Model_List_Chat_Apoteker
import com.example.rumahobat_.model_damy.Model_Damy2

class Adapter_Semua_Data_apoteker (val context: Context, var items: List<Model_List_Chat_Apoteker>, val clik : (Model_List_Chat_Apoteker)->Unit)
    : RecyclerView.Adapter<Adapter_Semua_Data_apoteker.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_semua_daftar_apoteker, parent, false))
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
        fun bindView(model_list_chat_apoteker: Model_List_Chat_Apoteker, clik: (Model_List_Chat_Apoteker) -> Unit){
            tahun_list_yuk_apoteker_home = itemView.findViewById(R.id.tahun_list_yuk_apoteker_home)
            nama_list_yuk_apoteker_home = itemView.findViewById(R.id.nama_list_yuk_apoteker_home)

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


            nama_list_yuk_apoteker_home.text =model_list_chat_apoteker.nama_panjang
            pengalaman_list_yuk_apoteker_home.text = model_list_chat_apoteker.masa_praktek
//            rating_list_yuk_apoteker_home.rating = model_list_chat_apoteker.jumlah_rating.toFloat()
        }
    }
}