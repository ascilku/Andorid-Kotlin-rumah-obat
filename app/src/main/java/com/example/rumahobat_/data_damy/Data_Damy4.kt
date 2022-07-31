package com.example.rumahobat_.data_damy

import com.example.rumahobat_.R
import com.example.rumahobat_.model_damy.Model_Damy
import com.example.rumahobat_.model_damy.Model_Damy4

object Data_Damy4 {
    private val namaApoteker  = arrayOf(
        "Apt. St. Chadijah4, S.Farm"
        , "Apt. St. Hasru4l, S.Farm"
        , "Apt. St. Kurniawan Ascil Wawank Kurni,4 S.Farm"
        , "Apt. St. Syafii4, S.Farm"
        , "Apt. St. Fahri4, S.Farm"
    )
    val jumlahPoint : Int = 10
    val jumlahUserKlik : Int = 10
    val hasil = jumlahPoint / jumlahUserKlik
    private val rating = intArrayOf(hasil, 5, 10, 9, 8)

    private val pengalaman1 = intArrayOf(3, 6, 10, 20, 30)
    private val fotoApoteker = intArrayOf(
        R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
        , R.drawable.contoh_foto
    )

    val getData: ArrayList<Model_Damy4>
        get() {
        val listData = arrayListOf<Model_Damy4>()
            for (i in namaApoteker.indices){
                var modelDamy = Model_Damy4()
                modelDamy.nama = namaApoteker[i]
                modelDamy.reting = rating[i]
                modelDamy.pengalaman = pengalaman1[i]
                modelDamy.foto = fotoApoteker[i]
                listData.add(modelDamy)
            }
        return listData
    }
}