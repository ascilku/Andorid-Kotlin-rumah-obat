package com.example.rumahobat_.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model_lihat_temanCerita(

   var id_akun_apoteker : String,
   var id_akun_users  : String,
   var jumlah_chat : String,
   var status_akun_users : String,
   var status_akun_apoteker : String,
   var nama_panjang : String,
   var alumni_apoteker : String,
   var tahun_mulai_praktek : String,
   var tempat_praktik : String,
   var foto_profil : String,
   var masa_praktek : String,
   var foto_stra : String,
   var nomor_stra : String,
   var foto_sipa : String,
   var no_sipa : String,
   var status_satu : String
) : Parcelable {

}