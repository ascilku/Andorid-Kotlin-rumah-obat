package com.example.rumahobat_.fragment


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager

import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Main_LoginRegister
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.model.Model_Login
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.shared_preferences.SessionManager
import com.example.rumahobat_.value.Value_Login
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class Login_Fragment : Fragment(), Main_LoginRegister.OnBackPressedListner {
    var dialog: AlertDialog.Builder? = null
    var inflater: LayoutInflater? = null
    var dialogView: View? = null
    companion object{
        const val KEY_AKSES = "akses"
    }
    var items : MutableList<Model_Login> = mutableListOf()
    lateinit var session : SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.login_fragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManager(view.context)

        val akses = arguments?.getString(KEY_AKSES)
//        sign_in.text = akses
//        progress_login.visibility = View.GONE
//        text_progress.visibility = View.GONE
//        icon_progress.visibility = View.GONE
        val nada = session.nama
        text_login.text = nada
        bottonLogin.setOnClickListener(View.OnClickListener {
            val nomorHp = no_Login.text.toString()
//            val password = password_Login.text.toString()
            if (akses != null) {
                login(nomorHp, akses)
            }
            no_Login.setText("")
//            password_Login.setText("")
        })

        klik_daftar.setOnClickListener {
            val registerFragment = Register_Fragment()
            val fragmentManager : FragmentManager? = fragmentManager
            val bundel = Bundle()
            bundel.putString(Register_Fragment.KEY_AKSES_REGISTER, akses)
            registerFragment.arguments = bundel
            fragmentManager?.beginTransaction()
                ?.replace(R.id.framContainer_Login, registerFragment)?.commit()
        }
    }

    private fun login(nomorHp: String, akses : String) {
//        progress_login.visibility = View.VISIBLE
//        text_progress.visibility = View.VISIBLE
//        icon_progress.visibility = View.VISIBLE
        val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
        val call : Call<Value_Login>
        call = gsonRetrofit.login(nomorHp,akses)
        call.enqueue(object : Callback<Value_Login>{
            override fun onFailure(call: Call<Value_Login>, t: Throwable) {
                t.printStackTrace()
//                progress_login.visibility = View.GONE
//                text_progress.visibility = View.GONE
//                icon_progress.visibility = View.GONE
                Toast.makeText(context,"Tidak Ada Internet", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Value_Login>, response: Response<Value_Login>) {
//                progress_login.visibility = View.GONE
//                text_progress.visibility = View.GONE
//                icon_progress.visibility = View.GONE
                val dataLogin = response.body()
//                items = dataLogin?.response as MutableList<Model_Login>

                val value = dataLogin?.value
                val pesan = dataLogin?.pesan
                val response = dataLogin?.response?.get(0)
                    if (value =="1"){
                        val loadingLogin = Loading_Login()
                        val fragmentManager : FragmentManager? = fragmentManager
                        val bundel = Bundle()
                        bundel.putString(Register_Fragment.KEY_AKSES_REGISTER, akses)
                        loadingLogin.arguments = bundel
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.framContainer_Login, loadingLogin)?.commit()
                        session.createLoginSession(response?.id_akun_apoteker, response?.nama_panjang, response?.no_hp, akses)


                    }else{


                        val registerFragment = Register_Fragment()
                        val fragmentManager : FragmentManager? = fragmentManager
                        val bundel = Bundle()
                        bundel.putString(Register_Fragment.KEY_AKSES_REGISTER, akses)
                        registerFragment.arguments = bundel
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.framContainer_Login, registerFragment)?.commit()
                    }
            }

        })
    }

    override fun onBackPressed(): Boolean {
        val aksesusersFragment = AksesUsers_Fragment()
        val fragmentManagerr : FragmentManager? = fragmentManager
        fragmentManagerr?.beginTransaction()?.replace(R.id.framContainer_Login, aksesusersFragment)
            ?.commit()
        return true
    }

//    private fun sukses() {
//        dialog = AlertDialog.Builder(view?.context)
//        inflater = layoutInflater
//        dialogView = inflater!!.inflate(R.layout.item_sukses, null)
//        dialog!!.setView(dialogView)
//        dialog!!.setCancelable(true)
//            val intent = Intent(view?.context, Home_Main::class.java)
//            activity?.finish()
//            startActivity(intent)
//        dialog!!.show()
//    }
}
