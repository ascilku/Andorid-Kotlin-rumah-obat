package com.example.rumahobat_.shared_preferences

import android.content.Context
import android.content.SharedPreferences

class SessionCoba(var context: Context) {
    companion object {
        private const val PREF_NAME = "biodata1"
        private const val PREF_NAME1 = "biodata2"
    }

    var pref: SharedPreferences
    var pref1: SharedPreferences
    var editor: SharedPreferences.Editor
    var editor1: SharedPreferences.Editor
    var private_mode = 0

    var idAkun: String?
        get() = pref.getString("idakun", null)
        set(idakun) {
            editor.putString("idakun", idakun)
            editor.commit()
        }

    //    ------------------------------------------
    var idKategori: String?
        get() = pref1.getString("kategori", null)
        set(kategori) {
            editor1.putString("kategori", kategori)
            editor1.commit()
        }



    init {
        pref = context.getSharedPreferences(PREF_NAME, private_mode)
        editor = pref.edit()

        pref1 = context.getSharedPreferences(PREF_NAME1, private_mode)
        editor1 = pref1.edit()
    }
}