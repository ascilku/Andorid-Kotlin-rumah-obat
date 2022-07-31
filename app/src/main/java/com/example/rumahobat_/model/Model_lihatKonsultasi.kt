package com.example.rumahobat_.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model_lihatKonsultasi(
    val id_akun_apoteker : String,
    val nama_panjang : String,
    val alumni_apoteker : String,
    val tahun_mulai_praktek : String,
    val tempat_praktik : String,
    val masa_praktek : String,
    val foto_profil : String,
    val foto_stra : String,
    val nomor_stra : String,
    val foto_sipa : String,
    val no_sipa : String,
    val status_satu : String
) : Parcelable {

}