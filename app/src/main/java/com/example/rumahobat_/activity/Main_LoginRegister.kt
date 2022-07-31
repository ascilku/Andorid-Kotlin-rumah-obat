package com.example.rumahobat_.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rumahobat_.R
import com.example.rumahobat_.fragment.AksesUsers_Fragment
import com.example.rumahobat_.fragment.Fragment_LoginRegister


class Main_LoginRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.main_login_register)
        val fragment_LoginRegister = AksesUsers_Fragment()
        val supportFragmentManagerr = supportFragmentManager
        supportFragmentManagerr.beginTransaction()
            .replace(R.id.framContainer_Login, fragment_LoginRegister).commit()
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.framContainer_Login)
        if (fragment !is OnBackPressedListner || !(fragment as OnBackPressedListner?)?.onBackPressed()!!) {
            super.onBackPressed()
        }
    }
    interface OnBackPressedListner {
        fun onBackPressed(): Boolean
    }
}
