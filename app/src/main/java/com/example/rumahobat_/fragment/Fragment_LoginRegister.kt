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
import kotlinx.android.synthetic.main.fragment_login_register.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_LoginRegister : Fragment(), View.OnClickListener {
    lateinit var session : SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManager(view.context)
        bottonSignIn.setOnClickListener(this)
        bottonSignUp.setOnClickListener(this)
        skip_loginRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bottonSignIn -> {
                if (session.isLoggedIn) {
                    var intent = Intent(context, Home_Main::class.java)
                    startActivity(intent)
                }else{
                    val loginRegister = Login_Fragment()
                    val fragmentManager : FragmentManager? = getFragmentManager()
                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction().replace(R.id.framContainer_Login, loginRegister).commit()
                    }
                }

            }
            R.id.bottonSignUp -> {
                val registerFragment = Register_Fragment()
                val fragmentManager : FragmentManager? = getFragmentManager()
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().replace(R.id.framContainer_Login, registerFragment).commit()
                }
            }
            R.id.skip_loginRegister -> {
                startActivity(Intent(view?.context, Home_Main::class.java))
                activity?.finish()
            }
        }
    }


}
