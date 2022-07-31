package com.example.rumahobat_.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home_Main
import com.example.rumahobat_.shared_preferences.SessionManager
import kotlinx.android.synthetic.main.akses_users_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class AksesUsers_Fragment : Fragment(), View.OnClickListener {
    lateinit var session : SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.akses_users_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManager(view.context)
        bottonApoteker.setOnClickListener(this)
        bottonPengguna.setOnClickListener(this)
//        skip_loginAkses.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bottonApoteker ->{
                if (session.isLoggedIn) {
                    val intent = Intent(context, Home_Main::class.java)
                    activity?.finish()
                    startActivity(intent)
                }else{
                    val loginRegister = Login_Fragment()
                    val fragmentManager : FragmentManager? = fragmentManager
                    val bundel = Bundle()
                    bundel.putString(Login_Fragment.KEY_AKSES, "Apoteker")
                    loginRegister.arguments = bundel
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.framContainer_Login, loginRegister)?.commit()

                }
            }
            R.id.bottonPengguna ->{
                if (session.isLoggedIn) {
                    val intent = Intent(context, Home_Main::class.java)
                    activity?.finish()
                    startActivity(intent)
                }else{
                    val loginRegister = Login_Fragment()
                    val fragmentManager : FragmentManager? = fragmentManager
                    val bundel = Bundle()
                    bundel.putString(Login_Fragment.KEY_AKSES, "Pengguna")
                    loginRegister.arguments = bundel
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.framContainer_Login, loginRegister)?.commit()

                }
            }
//            R.id.skip_loginAkses ->{
//                var intent = Intent(context, Home_Main::class.java)
//                startActivity(intent)
//            }
        }
    }


}
