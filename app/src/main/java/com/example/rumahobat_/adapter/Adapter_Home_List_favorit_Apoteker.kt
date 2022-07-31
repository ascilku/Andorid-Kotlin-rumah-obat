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
import com.example.rumahobat_.model_damy.Model_Damy
import com.example.rumahobat_.model_damy.Model_Damy4

class Adapter_Home_List_favorit_Apoteker (val context: Context, var items: List<Model_Damy4>)
    : RecyclerView.Adapter<Adapter_Home_List_favorit_Apoteker.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var foto_list_favorit_apoteker_home : ImageView
        lateinit var nama_list_favorit_apoteker_home : TextView
        lateinit var apoteker_list_favorit_apoteker_home : TextView
        lateinit var harga_list_favorit_apoteker_home : TextView
        lateinit var rating_list_favorit_apoteker_home : RatingBar
        lateinit var nilai_list_favorit_apoteker_home : TextView
        lateinit var pengalaman_list_favorit_apoteker_home : TextView
        lateinit var tahun_list_favorit_apoteker_home : TextView
        lateinit var bottonFavorit_apoteker : Button


        fun bindView(modelDamy4: Model_Damy4){
            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            var typeface2 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Bold.ttf")

            nama_list_favorit_apoteker_home = itemView.findViewById(R.id.nama_list_favorit_apoteker_home)
            apoteker_list_favorit_apoteker_home = itemView.findViewById(R.id.apoteker_list_favorit_apoteker_home)
            harga_list_favorit_apoteker_home = itemView.findViewById(R.id.harga_list_favorit_apoteker_home)
            nilai_list_favorit_apoteker_home = itemView.findViewById(R.id.nilai_list_favorit_apoteker_home)
            pengalaman_list_favorit_apoteker_home = itemView.findViewById(R.id.pengalaman_list_favorit_apoteker_home)
            tahun_list_favorit_apoteker_home = itemView.findViewById(R.id.tahun_list_favorit_apoteker_home)
            bottonFavorit_apoteker = itemView.findViewById(R.id.bottonFavorit_apoteker)
            foto_list_favorit_apoteker_home = itemView.findViewById(R.id.foto_list_favorit_apoteker_home)
            rating_list_favorit_apoteker_home = itemView.findViewById(R.id.rating_list_favorit_apoteker_home)

            bottonFavorit_apoteker.typeface = typeface1
            tahun_list_favorit_apoteker_home.typeface = typeface1
            pengalaman_list_favorit_apoteker_home.typeface = typeface1
            nilai_list_favorit_apoteker_home.typeface = typeface1
            harga_list_favorit_apoteker_home.typeface = typeface1
            apoteker_list_favorit_apoteker_home.typeface = typeface2
            nama_list_favorit_apoteker_home.setTypeface(typeface1)


            nama_list_favorit_apoteker_home.text =modelDamy4.nama
            pengalaman_list_favorit_apoteker_home.text = modelDamy4.pengalaman.toString()
            rating_list_favorit_apoteker_home.rating = modelDamy4.reting.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item1_favorit_apoteker, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }


}