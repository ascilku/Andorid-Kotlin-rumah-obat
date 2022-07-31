package com.example.rumahobat_.activity.ui_apoteker.coba

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_users.Coba1

class TreedCoba : AppCompatActivity() {
    var sleepp = 1000
    var nilaiLoop = 1000
    lateinit var treed111: TextView
    lateinit var treed222: TextView
    lateinit var treed333: TextView
    lateinit var treed444: TextView
    lateinit var treed555: TextView
    lateinit var treed666: TextView
    var i = 0
    var b = 0
    var c = 0
    var d = 0
    var e = 0
    var f = 0
    var handler1: Handler? = null
    var handler2: Handler? = null
    var handler3: Handler? = null
    var handler4: Handler? = null
    var handler5: Handler? = null
    var handler6: Handler? = null
    var treed11: Thread? = null
    var treed22: Thread? = null
    var treed33: Thread? = null
    var treed44: Thread? = null
    var treed55: Thread? = null
    var treed66: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treed)
        treed111 = findViewById(R.id.treed1)
        treed222 = findViewById(R.id.treed2)
        treed333 = findViewById(R.id.treed3)
        treed444 = findViewById(R.id.treed4)
        treed555 = findViewById(R.id.treed5)
        treed666 = findViewById(R.id.treed6)
        val pndah = findViewById<Button>(R.id.pndah)
        pndah.setOnClickListener { v ->
            val intent = Intent(v.context, Coba1::class.java)
            startActivity(intent)
        }
        handler1 = Handler()
        handler2 = Handler()
        handler3 = Handler()
        handler4 = Handler()
        handler5 = Handler()
        handler6 = Handler()
        treed11 = object : Thread() {
            override fun run() {
                i = 0
                while (i < 1000) {
                    try {
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler1!!.post { treed111.text = "i$i" }
                    i++
                }
            }
        };(treed11 as Thread).start()
        treed22 = object : Thread() {
            override fun run() {
                b = 0
                while (b < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler2!!.post { treed222.setText("i =  $b") }
                    b++
                }
            }
        };(treed22 as Thread).start()
        treed33 = object : Thread() {
            override fun run() {
                c = 0
                while (c < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler3!!.post { treed333.setText("i =  $c") }
                    c++
                }
            }
        };(treed33 as Thread).start()
        treed44 = object : Thread() {
            override fun run() {
                d = 0
                while (d < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler4!!.post { treed444.setText("i =  $d") }
                    d++
                }
            }
        };(treed44 as Thread).start()
        treed55 = object : Thread() {
            override fun run() {
                e = 0
                while (e < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler5!!.post { treed555.setText("i =  $e") }
                    e++
                }
            }
        };(treed55 as Thread).start()
        treed66 = object : Thread() {
            override fun run() {
                f = 0
                while (f < nilaiLoop) {
                    try {
                        sleep(sleepp.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

//                        Log.d(LOG,"i" +i );
                    handler6!!.post { treed666.setText("i =  $f") }
                    f++
                }
            }
        };(treed66 as Thread).start()
    }

    companion object {
        private const val LOG = "TreedCoba"
    }
}