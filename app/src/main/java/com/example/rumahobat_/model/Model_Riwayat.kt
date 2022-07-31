package com.example.rumahobat_.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model_Riwayat(val nama_panjang_users : String, val nama_panjang : String, val isi : String , val kode_akses_chat : String, val id_akun_apoteker : String) :
    Parcelable