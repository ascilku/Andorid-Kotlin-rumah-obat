package com.example.rumahobat_.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumahobat_.R
import kotlinx.android.synthetic.main.syarat_ketantuan.*

class Syarat_Ketantuan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.syarat_ketantuan)
        beckChatingan.setOnClickListener {
            val intent = Intent(this, Slide_Awal::class.java)
                finish()
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, Slide_Awal::class.java)
        startActivity(intent)
        finish()
    }
}
