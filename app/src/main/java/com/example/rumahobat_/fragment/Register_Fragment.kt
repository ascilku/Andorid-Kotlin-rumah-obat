package com.example.rumahobat_.fragment


import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.rumahobat_.R
import com.example.rumahobat_.activity.Main_LoginRegister
import com.example.rumahobat_.api.Api_Interface
import com.example.rumahobat_.retorfit.Gson_Retrofit
import com.example.rumahobat_.value.Value_List_Chat_Apoteker
import com.example.rumahobat_.value.Value_Register_Akun_Apoteker
import kotlinx.android.synthetic.main.register_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class Register_Fragment : Fragment() , Main_LoginRegister.OnBackPressedListner ,
    View.OnClickListener {
    companion object{
        const val KEY_AKSES_REGISTER = "akses_register"
    }
    lateinit var namaPanjang_RegisterTampung : String
    lateinit var aksesRegister : String
    lateinit var email_RegisterTampung : String
    lateinit var no_RegisterTampung : String
    lateinit var password_RegisterTampung : String
    lateinit var c_password_RegisterTampung : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeface3 = Typeface.createFromAsset(view.context.assets,"nunito_sans/NunitoSans-Regular.ttf")
        namaPanjang_Register.typeface = typeface3
        email_Register.typeface = typeface3
        no_Register.typeface = typeface3
        password_Register.typeface = typeface3
        c_password_Register.typeface = typeface3
        namaPanjang_Register1.typeface = typeface3
        aksesRegister = arguments?.getString(KEY_AKSES_REGISTER).toString()
            if (aksesRegister == "Apoteker"){
                namaPanjang_Register.visibility = View.VISIBLE
                namaPanjang_Register1.visibility = View.INVISIBLE
            }else{
                namaPanjang_Register.visibility = View.INVISIBLE
                namaPanjang_Register1.visibility = View.VISIBLE

            }

        progress_Register.visibility = View.GONE
        bottonRegister.setOnClickListener(this)



    }

    override fun onBackPressed(): Boolean {
        val aksesusersFragment = AksesUsers_Fragment()
        val fragmentManagerr : FragmentManager? = fragmentManager
        fragmentManagerr?.beginTransaction()?.replace(R.id.framContainer_Login, aksesusersFragment)
            ?.commit()
        return true
    }

    override fun onClick(v: View?) {
        Toast.makeText(context, aksesRegister, Toast.LENGTH_LONG).show()
        namaPanjang_RegisterTampung     = namaPanjang_Register.text.toString().trim()
        namaPanjang_RegisterTampung     = namaPanjang_Register1.text.toString().trim()
        email_RegisterTampung           = email_Register.text.toString().trim()
        no_RegisterTampung              =  no_Register.text.toString().trim()
        password_RegisterTampung              =  password_Register.text.toString().trim()
        c_password_RegisterTampung              =  c_password_Register.text.toString().trim()
        var benar : Boolean = true
        if (namaPanjang_RegisterTampung.isEmpty()){
            benar = false
            namaPanjang_Register.setError("Nama Panjang Tidak Boleh Kosong")
        }
        if (email_RegisterTampung.isEmpty()){
            benar = false
            email_Register.setError("Email Tidak Boleh Kosong")
        }
        if (no_RegisterTampung.isEmpty()){
            benar = false
            no_Register.setError("No HP Panjang Tidak Boleh Kosong")
        }
        if (password_RegisterTampung.isEmpty()){
            benar = false
            password_Register.setError("No HP Panjang Tidak Boleh Kosong")
        }
        if (c_password_RegisterTampung.isEmpty()){
            benar = false
            c_password_Register.setError("No HP Panjang Tidak Boleh Kosong")
        }
        if (benar == true){
            progress_Register.visibility = View.VISIBLE
            val gsonRetrofit = Gson_Retrofit.buildService(Api_Interface::class.java)
            val call : Call<Value_Register_Akun_Apoteker>
            call = gsonRetrofit.getRegisterAkunApoteker(
                namaPanjang_RegisterTampung,
                email_RegisterTampung,
                no_RegisterTampung,
                password_RegisterTampung,
                c_password_RegisterTampung,
                aksesRegister

            )
            call.enqueue(object :Callback<Value_Register_Akun_Apoteker>{
                override fun onFailure(call: Call<Value_Register_Akun_Apoteker>, t: Throwable) {
                    t.printStackTrace()
                    progress_Register.visibility = View.GONE
                    Toast.makeText(view?.context, "Koneksi Tidak Ada", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Value_Register_Akun_Apoteker>,
                    response: Response<Value_Register_Akun_Apoteker>
                ) {
                    progress_Register.visibility = View.GONE
                    val dataInputDataRegister = response.body()
                    val value = dataInputDataRegister?.value
                    val pesan = dataInputDataRegister?.pesan
                        if (value.equals("1")){
                            val aksesusersFragment = AksesUsers_Fragment()
                            val fragmentManagerr : FragmentManager? = fragmentManager
                            fragmentManagerr?.beginTransaction()?.replace(R.id.framContainer_Login, aksesusersFragment)
                                ?.commit()
                        }else{
                            Toast.makeText(view?.context, pesan, Toast.LENGTH_SHORT).show()
                        }
                }

            })
        }
    }
}
