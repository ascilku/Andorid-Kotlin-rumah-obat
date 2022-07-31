package com.example.rumahobat_.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Home
import com.example.rumahobat_.activity.Home_Main
import com.example.rumahobat_.activity.Home_Rumah_Obat
import com.example.rumahobat_.activity.SplashScreen
import com.example.rumahobat_.activity.ui_apoteker.Main_Home_Apoteker
import kotlinx.android.synthetic.main.loading__login.*
import kotlinx.android.synthetic.main.register_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class Loading_Login : Fragment() {
    companion object{
        private const val SPLESH = 3000
        const val KEY_AKSES_Loading = "akses_loading"

    }
    lateinit var aksesLoading : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loading__login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        bar.startProgress();
//        activity?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            aksesLoading = arguments?.getString(Register_Fragment.KEY_AKSES_REGISTER).toString()
//            Toast.makeText(view.context, aksesLoading, Toast.LENGTH_LONG).show()
            if (aksesLoading == "Apoteker"){
                val intent = Intent(view.context, Main_Home_Apoteker::class.java)
                activity?.finish()
                view.context.startActivity(intent)
            }else{
                val intent = Intent(view.context, Home_Main::class.java)
                activity?.finish()
                view.context.startActivity(intent)
            }
        }, SPLESH.toLong())
    }
}
