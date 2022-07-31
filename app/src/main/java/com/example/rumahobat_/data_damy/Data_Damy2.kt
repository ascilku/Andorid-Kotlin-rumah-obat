package com.example.rumahobat_.data_damy

import com.example.rumahobat_.R
import com.example.rumahobat_.model_damy.Model_Damy2

object Data_Damy2 {
    private val namaApoteker1  = arrayOf(
        "Apt. St. Chadijah, S.Farm"
        , "Apt. St. Hasrul, S.Farm"
        , "Apt. St. Kurniawan Ascil Wawank Kurni, S.Farm"
        , "Apt. St. Syafii, S.Farm"
        , "Apt. St. Fahri, S.Farm"
    )
    val jumlahPoint : Int = 10
    val jumlahUserKlik : Int = 10
    val hasil = jumlahPoint / jumlahUserKlik
    private val rating1 = intArrayOf(hasil, 5, 10, 9, 8)

    private val pengalaman11 = intArrayOf(3, 6, 10, 20, 30)
    private val fotoApoteker1 = intArrayOf(
        R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
    )

    val getData1: ArrayList<Model_Damy2> get() {
        val listData1 = arrayListOf<Model_Damy2>()
        for (i in namaApoteker1.indices){
            var modelDamy2 = Model_Damy2()
            modelDamy2.nama1 = namaApoteker1[i]
            modelDamy2.reting1 = rating1[i]
            modelDamy2.pengalaman1 = pengalaman11[i]
            modelDamy2.foto1 = fotoApoteker1[i]
            listData1.add(modelDamy2)
        }
        return listData1
    }
}