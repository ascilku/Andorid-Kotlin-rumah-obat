package com.example.rumahobat_.data_damy

import com.example.rumahobat_.R
import com.example.rumahobat_.model_damy.Model_Damy3

object Data_Damy3 {
    private val judulApoteker1  = arrayOf(
        "Cara Pencegahan Covid-19 di Era New Normal Cara Pencegahan Covid-19 di Era New Normal"
        , "Apt. St. Hasrul, S.Farm"
        , "Apt. St. Kurniawan Ascil Wawank Kurni, S.Farm"
    )

    private val fotoApoteker1 = intArrayOf(
        R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
    )

    val getDataDiskusi : ArrayList<Model_Damy3>
        get() {
            val listDataDiskusi = arrayListOf<Model_Damy3>()
                for (i in judulApoteker1.indices){
                    val modelDamy3 =  Model_Damy3()
                    modelDamy3.judul = judulApoteker1[i]
                    modelDamy3.foto = fotoApoteker1[i]
                    listDataDiskusi.add(modelDamy3)
                }
            return listDataDiskusi
        }
}