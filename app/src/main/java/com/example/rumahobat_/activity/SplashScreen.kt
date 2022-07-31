package com.example.rumahobat_.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.ui_apoteker.Main_Home_Apoteker
import com.example.rumahobat_.shared_preferences.SessionManager

class SplashScreen : AppCompatActivity() {
    companion object{
        const val SPLESH = 3000
    }
    lateinit var session : SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash_screen)
        session = SessionManager(this)
        Handler().postDelayed({
            val _akses = session.akses
                if (_akses == "Apoteker"){
                    startActivity(Intent(this, Main_Home_Apoteker::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, Home_Main::class.java))
                    finish()
                }

        }, SPLESH.toLong())
    }


}
