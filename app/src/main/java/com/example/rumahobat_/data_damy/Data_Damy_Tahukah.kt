package com.example.rumahobat_.data_damy

import com.example.rumahobat_.R
import com.example.rumahobat_.model_damy.Model_Damy3
import com.example.rumahobat_.model_damy.Model_Damy_Tahukah

object Data_Damy_Tahukah {
    private val judulApoteker1  = arrayOf(
        "Tahukah Cara Pencegahan Covid-19 di Era New Normal Cara Pencegahan Covid-19 di Era New Normal"
        , "Tahukah Apt. St. Hasrul, S.Farm"
        , "Tahukah Apt. St. Kurniawan Ascil Wawank Kurni, S.Farm"
    )

    private val fotoApoteker1 = intArrayOf(
        R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
    )

    val getDataTahukah : ArrayList<Model_Damy_Tahukah>
        get() {
            val listDataDiskusi = arrayListOf<Model_Damy_Tahukah>()
                for (i in judulApoteker1.indices){
                    val modelDamy3 =  Model_Damy_Tahukah()
                    modelDamy3.judul = judulApoteker1[i]
                    modelDamy3.foto = fotoApoteker1[i]
                    listDataDiskusi.add(modelDamy3)
                }
            return listDataDiskusi
        }
}