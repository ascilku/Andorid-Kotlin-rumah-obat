package com.example.rumahobat_.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Model_lihatDataUsers(
    val foto_users : String,

    val nama_panjang_users : String,
    val no_hp : String,
    val usia : String,
    val tempat_lahir : String,
    val date_lahir : String,
    val jenis_kelamin : String,

    val tinggi_badan : String,
    val berat_badan : String,

    val alamat : String,
    val riwayat_obat : String,
    val riwayat_penyakit : String


)