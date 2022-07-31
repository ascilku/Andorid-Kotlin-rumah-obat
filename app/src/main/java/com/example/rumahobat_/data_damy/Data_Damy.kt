package com.example.rumahobat_.data_damy

import com.example.rumahobat_.R
import com.example.rumahobat_.model_damy.Model_Damy

object Data_Damy {
    private val namaApoteker  = arrayOf(
        "cari Apt. St. Chadijah, S.Farm"
        , "Apt. St. Hasrul, S.Farm"
        , "Apt. St. Kurniawan Ascil Wawank Kurni, S.Farm"
        , "Apt. St. Syafii, S.Farm"
        , "Apt. St. Fahri, S.Farm"
    )
    val jumlahPoint : Int = 10
    val jumlahUserKlik : Int = 10
    val hasil = jumlahPoint / jumlahUserKlik
    private val rating = intArrayOf(hasil, 5, 10, 9, 8)

    private val pengalaman1 = intArrayOf(3, 6, 10, 20, 30)
    private val fotoApoteker = intArrayOf(
        R.drawable.contoh_gambar1
        , R.drawable.contoh_gambar1
        , R.drawable.contoh_foto2
        , R.drawable.contoh_foto3
        , R.drawable.contoh_foto4
    )

    val getData: ArrayList<Model_Damy> get() {
        val listData = arrayListOf<Model_Damy>()
            for (i in namaApoteker.indices){
                var modelDamy = Model_Damy()
                modelDamy.nama = namaApoteker[i]
                modelDamy.reting = rating[i]
                modelDamy.pengalaman = pengalaman1[i]
                modelDamy.foto = fotoApoteker[i]
                listData.add(modelDamy)
            }
        return listData
    }
}