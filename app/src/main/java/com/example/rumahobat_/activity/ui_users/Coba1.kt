package com.example.rumahobat_.activity.ui_users

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahobat_.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.coba1.*

class Coba1 : AppCompatActivity() {
    var fab: FloatingActionButton? = null
    var toolbar: Toolbar? = null
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    var dialogView: View? = null
    var txt_nama: EditText? = null
    var txt_usia: EditText? = null
    var txt_alamat: EditText? = null
    var txt_status: EditText? = null
    var txt_hasil: TextView? = null
    var nama: String? = null
    var usia: String? = null
    var alamat: String? = null
    var status: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coba1)

        pndah.setOnClickListener(View.OnClickListener { v ->
            val intent = Intent(v.context, Coba1::class.java)
            startActivity(intent)
        })
    }

    private fun DialogForm() {
        dialog = AlertDialog.Builder(this)
        inflater = layoutInflater
        dialogView = inflater!!.inflate(R.layout.sheet_waktu_habis, null)
        dialog!!.setView(dialogView)
        dialog!!.setCancelable(true)
//        dialog!!.setIcon(R.mipmap.ic_launcher)
        dialog!!.setTitle("Form Biodata")
        dialog!!.setPositiveButton("SUBMIT") { dialog, which ->
            nama = txt_nama!!.text.toString()
            usia = txt_usia!!.text.toString()
            alamat = txt_alamat!!.text.toString()
            status = txt_status!!.text.toString()
            txt_hasil!!.text = "Nama : $nama\nUsia : $usia\nAlamat : $alamat\nStatus : $status"
            dialog.dismiss()
        }
        dialog!!.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
        dialog!!.show()
    }
}