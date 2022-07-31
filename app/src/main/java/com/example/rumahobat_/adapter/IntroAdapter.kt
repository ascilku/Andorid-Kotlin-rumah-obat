package com.example.rumahobat_.adapter

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home_Main
import kotlinx.android.synthetic.main.item_slide.*

class IntroAdapter(var context: Context) : PagerAdapter() {
    var current : Int = 0
    var layoutInflater: LayoutInflater? = null
    var slide = intArrayOf(
        R.drawable.intro_awal1,
        R.drawable.intro_awal2,
        R.drawable.intro_awal3
    )
    var text1 = arrayOf(
        "Konsultasi Obat Dengan Mudah",
        """
            Diskusi Terkait Isu Obat dan
            Kesehatan Terkini
            """.trimIndent(),
        "Apoteker Tidak Mendiagnosa Ya..."
    )
    var text2 = arrayOf(
        """
            Mulai sekarang kamu bisa konsultasi tentang obat apa saja
            dan dimana saja dengan apoteker yang ramah dan
            terpercaya hanya dengan satu kali klik loh...
            """.trimIndent(),
        """
            Punya isu yang simpang siur terkait obat dan kesehatan ?
            Gabung di Forum Diskusi Rumah Obat aja...
            """.trimIndent(),
        """
            Kamu tidak akan menerima diagnosa terkait penyakit.
            Apoteker akan memberikan informasi lengkap
            tentang obatmu.
            """.trimIndent()
    )
    var text3 = arrayOf(
        "",
        "",
        """
            Dengan klik daftar, saya menerima Syarat dan Ketentuan
            Pengguna yang berlaku di Rumah Obat.
            """.trimIndent()
    )

    override fun getCount(): Int {
        return text1.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.intro_awal, container, false)
        val gambar_slide = view.findViewById<View>(R.id.gambar_slide) as ImageView
        val keterangan1 = view.findViewById<View>(R.id.keterangan1) as TextView
        val keterangan2 = view.findViewById<View>(R.id.keterangan2) as TextView

        gambar_slide.setImageResource(slide[position])
        keterangan1.text = text1[position]
        keterangan2.text = text2[position]
        current = position


//        var spannableString = SpannableString(text3[2])
//        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
//            override fun onClick(view: View) {
//
//                val i = Intent(context, Home_Main::class.java)
//                i.putExtra("idobat", 57)
//                context.startActivity(i)
////                finish()
//            }
//        }
//
//        spannableString.setSpan(clickableSpan1, 34, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        keterangan3.text = spannableString
//
//        if (position == 0){
//            keterangan3.isEnabled = false
//            keterangan3.visibility = View.INVISIBLE
//        }else if(position == 1){
//            keterangan3.isEnabled = false
//            keterangan3.visibility = View.INVISIBLE
//        }else{
//            keterangan3.isEnabled = true
//            keterangan3.visibility = View.VISIBLE
//        }

        container.addView(view)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as RelativeLayout)
    }

}