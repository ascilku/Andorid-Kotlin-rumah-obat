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
import com.example.rumahobat_.model_damy.Model_Damy3
import com.example.rumahobat_.model_damy.Model_Damy_Tahukah

class Adapter_Home_List_Tahukah_Apoteker (val context: Context, var items: List<Model_Damy_Tahukah>)
    : RecyclerView.Adapter<Adapter_Home_List_Tahukah_Apoteker.ViewHolder>() {

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var foto_list_tahukah_apoteker_home : ImageView
        lateinit var nama_list_tahukah_apoteker_home : TextView


        fun bindView(modelDamyTahukah : Model_Damy_Tahukah){
            nama_list_tahukah_apoteker_home = itemView.findViewById(R.id.nama_list_tahukah_apoteker_home)
            foto_list_tahukah_apoteker_home = itemView.findViewById(R.id.foto_list_tahukah_apoteker_home)
            var typeface1 = Typeface.createFromAsset(itemView.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
            nama_list_tahukah_apoteker_home.typeface = typeface1

            nama_list_tahukah_apoteker_home.text =modelDamyTahukah.judul
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item1_tahukah, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }


}