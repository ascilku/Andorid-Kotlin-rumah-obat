package com.example.rumahobat_.activity

import android.R.attr.delay
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.provider.SyncStateContract.Helpers.update
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rumahobat_.R
import com.example.rumahobat_.model.Model_Lihat_Chatinga


class Sms : AppCompatActivity() {
//    var timer: TextView? = null
//    var start: Button? = null
//    var pause: Button? = null
//    var reset: Button? = null
//    var idInput: EditText? = null
//
//    var MillisecondTime: Long = 0
//    var StartTime: Long = 0
//    var TimeBuff: Long = 0
//    var UpdateTime = 0L
//    var handler: Handler? = null
//    var Seconds = 0
//    var Minutes = 0
//    var MilliSeconds = 0
var handlerPeringatan: Handler? = null
    companion object{
        const val SPLESH = 1000
    }
    lateinit var mHandlerThread : HandlerThread
var items : MutableList<Model_Lihat_Chatinga> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        mHandlerThread = HandlerThread("yeye")
        mHandlerThread.start()
        handlerPeringatan = Handler(mHandlerThread.getLooper())
        handlerPeringatan!!.postDelayed(object : Runnable {
            override fun run() {
                toast1()
                toast2()
                toast3()
                handlerPeringatan!!.postDelayed(this, SPLESH.toLong())
            }
        }, SPLESH.toLong())
//        Toast.makeText(applicationContext, "Ini jalan", Toast.LENGTH_LONG).show()

//        refresh_logPeringatanChatingan(1000)
//    val gsonRetrofit = Gson_Retrofit.createRetrofit()
//    val call : Call<Value_Lihat_Chatingan>
//    call = gsonRetrofit.getChatingan("35","1")
//    call.enqueue(object : Callback<Value_Lihat_Chatingan> {
//        override fun onFailure(call: Call<Value_Lihat_Chatingan>, t: Throwable) {
//            Toast.makeText(applicationContext, "Tidak Koneksi", Toast.LENGTH_LONG).show()
//        }
//
//        override fun onResponse(
//            call: Call<Value_Lihat_Chatingan>,
//            response: Response<Value_Lihat_Chatingan>
//        ) {
//            val dataChatHOme = response.body()
//            val value = dataChatHOme?.value
//
//            if (value.equals("1")){
//                items = dataChatHOme?.response as MutableList<Model_Lihat_Chatinga>
//                rv_container_chatingan.layoutManager = LinearLayoutManager(this@Sms, LinearLayoutManager.HORIZONTAL, false)
//                rv_container_chatingan.adapter = Adapter_Item_Chat_Dari(this@Sms, items)
//            }else{
//                Toast.makeText(applicationContext, "gagal", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    })
//        timer = findViewById<View>(R.id.tvTimer) as TextView
//        start = findViewById<View>(R.id.btStart) as Button
//        pause = findViewById<View>(R.id.btPause) as Button
//        reset = findViewById<View>(R.id.btReset) as Button
//        idInput = findViewById<View>(R.id.idInput) as EditText
//        handler = Handler()
//        StartTime = SystemClock.uptimeMillis()
//        handler!!.postDelayed(runnable, 0)
//        reset!!.isEnabled = false
////        start!!.setOnClickListener {
////            StartTime = SystemClock.uptimeMillis()
////            handler!!.postDelayed(runnable, 0)
////            reset!!.isEnabled = false
////        }
//        pause!!.setOnClickListener {
//            TimeBuff += MillisecondTime
//            handler!!.removeCallbacks(runnable)
//            reset!!.isEnabled = true
//        }
//        reset!!.setOnClickListener {
//            MillisecondTime = 0L
//            StartTime = 0L
//            TimeBuff = 0L
//            UpdateTime = 0L
//            Seconds = 0
//            Minutes = 0
//            MilliSeconds = 0
//            timer!!.text = "00:00:00"
//        }
    }

    private fun toast3() {
        Toast.makeText(applicationContext, "toast 3", Toast.LENGTH_LONG).show()
    }

    private fun toast2() {
        Toast.makeText(applicationContext, "toast 2", Toast.LENGTH_LONG).show()
    }

    private fun toast1() {
        Toast.makeText(applicationContext, "toast 1", Toast.LENGTH_LONG).show()
    }


    private fun refresh_logPeringatanChatingan(millionsecon_logPeringatanChatingan: Int) {
        handlerPeringatan = Handler()
        val runnable_logPeringatanChatingan = Runnable {
//            waktuSekarangPerbandinganPeringatan()
            Toast.makeText(applicationContext, "Ini jalan", Toast.LENGTH_LONG).show()
        }
        handlerPeringatan?.postDelayed(runnable_logPeringatanChatingan, millionsecon_logPeringatanChatingan.toLong())
    }


//    var runnable: Runnable = object : Runnable {
//        override fun run() {
//            MillisecondTime = SystemClock.uptimeMillis() - StartTime
//            UpdateTime = TimeBuff + MillisecondTime
//            Seconds = (UpdateTime / 1000).toInt()
//            Minutes = Seconds / 60
//            Seconds = Seconds % 60
//            MilliSeconds = (UpdateTime % 1000).toInt()
//            timer!!.text = ("" + Minutes + ":"
//                    + String.format("%02d", Seconds) + ":"
//                    + String.format("%03d", MilliSeconds))
//            handler!!.postDelayed(this, 0)
//        }
//    }
}