package com.example.rumahobat_.activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.rumahobat_.R
import com.example.rumahobat_.adapter.IntroAdapter
import kotlinx.android.synthetic.main.item_slide.*

class Slide_Awal : AppCompatActivity() {
//    private var into_ViewPager: ViewPager? = null
    private var intro_layout: LinearLayout? = null
    lateinit var mDots: Array<TextView?>
    private var introAdapter: IntroAdapter? = null
    var current : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.item_slide)
        intro_layout = findViewById(R.id.intro_layout)
//        into_ViewPager = findViewById(R.id.into_ViewPager)

        val terima1 = "Dengan klik daftar, saya menerima Syarat dan Ketentuan Pengguna yang berlaku di Rumah Obat. "
        var spannableString = SpannableString(terima1)
        val clickableSpan1: ClickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                Toast.makeText(this@Slide_Awal,"ko", Toast.LENGTH_LONG).show()
            }
        }
//        Toast.makeText(this@Slide_Awal,"ko", Toast.LENGTH_LONG).show()

        spannableString.setSpan(clickableSpan1, 34, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        keterangan3.text = spannableString
        keterangan3.setOnClickListener {
            val intent = Intent(this, Syarat_Ketantuan::class.java)
            startActivity(intent)
            finish()
        }

        slide_botton_daftar.setOnClickListener {
            var intent = Intent(this, Main_LoginRegister::class.java)
            startActivity(intent)
            finish()
        }


        introAdapter = IntroAdapter(this)
        into_ViewPager.adapter = introAdapter
        addDotsIndicator(0)
        into_ViewPager.addOnPageChangeListener(onPageChangeListener)
    }

    fun addDotsIndicator(position: Int) {
        mDots = arrayOfNulls(3)
        intro_layout!!.removeAllViews()
        for (i in mDots.indices) {
            mDots[i] = TextView(this)
            mDots[i]!!.text = Html.fromHtml("&#8226;")
            mDots[i]!!.textSize = 35f
            mDots[i]!!.setTextColor(resources.getColor(R.color.transparantColor))
            intro_layout!!.addView(mDots[i])
        }
        if (mDots.isNotEmpty()) {
            mDots[position]!!.setTextColor(resources.getColor(R.color.putih))
        }
    }

    private var onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDotsIndicator(position)
            current = position


                if (position == 0){
                    slide_botton_daftar.isEnabled = false
                    slide_botton_daftar.visibility = View.INVISIBLE

                    keterangan3.isEnabled = false
                    keterangan3.visibility = View.INVISIBLE
                }else if(position == 1){
                    slide_botton_daftar.isEnabled = false
                    slide_botton_daftar.visibility = View.INVISIBLE

                    keterangan3.isEnabled = false
                    keterangan3.visibility = View.INVISIBLE
                }else{
                    slide_botton_daftar.isEnabled = true
                    slide_botton_daftar.visibility = View.VISIBLE

                    keterangan3.isEnabled = true
                    keterangan3.visibility = View.VISIBLE
                }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}