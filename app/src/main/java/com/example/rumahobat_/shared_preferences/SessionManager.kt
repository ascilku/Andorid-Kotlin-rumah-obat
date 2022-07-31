package com.example.rumahobat_.shared_preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.rumahobat_.activity.Main_LoginRegister
import com.example.rumahobat_.activity.Slide_Awal
import java.util.*

/**
 * Created by Ady on 26/08/2015.
 */
class SessionManager(// Context
    var _context: Context
) {
    // Shared Preferences
    var pref: SharedPreferences

    // Editor for Shared preferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0

    /**
     * Create login session
     */
    fun createLoginSession(
        id: String?,
        nama: String?,
        no_hp: String?,
        akses : String?
    ) {
        // Storing login value as TRUE
        editor.putBoolean(
            IS_LOGIN,
            true
        )

        // Storing email in pref
        editor.putString(
            KEY_ID,
            id
        )
        editor.putString(
            KEY_NAMA,
            nama
        )

        editor.putString(
            KEY_NOMOR,
            no_hp
        )

        editor.putString(
            KEY_AKSES,
            akses
        )
        //        editor.putString(KEY_REK, rek);

        // commit changes
        editor.commit()
    }

    val id: String?
        get() = pref.getString(
            KEY_ID,
            null
        )

    val nama: String?
        get() = pref.getString(
            KEY_NAMA,
            null
        )

    val nomor: String?
        get() = pref.getString(
            KEY_NOMOR,
            null
        )

    val akses: String?
        get() = pref.getString(
            KEY_AKSES,
            null
        )

    //    public String getRek(){ return pref.getString(KEY_REK,null); }
    fun checkLogin() {
        // Check login status
        if (!isLoggedIn) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(_context, Main_LoginRegister::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            // Staring Login Activity
            _context.startActivity(i)
        }
    }// user email id
    // return user

    /**
     * Get stored session data
     */
    val userDetails: HashMap<String, String?>
        get() {
            val user =
                HashMap<String, String?>()
            // user email id
            user[KEY_ID] = pref.getString(
                KEY_ID,
                null
            )
            // return user
            return user
        }

    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.commit()

        // After logout redirect user to Loing Activity
        val i = Intent(_context, Slide_Awal::class.java)
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Staring Login Activity
        _context.startActivity(i)
    }

    /**
     * Quick check for login
     */
    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(
            IS_LOGIN,
            false
        )

    companion object {
        // Sharedpref file name
        private const val PREF_NAME = "RumahObatPref"

        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"

        // Email address (make variable public to access from outside)
        const val KEY_ID = "id"
        const val KEY_NAMA = "nama"
        const val KEY_NOMOR = "nomor"
        const val KEY_AKSES = "akses"

        //    public static final String KEY_REK= "rek";
        private var instance: SessionManager? = null
        fun with(context: Context): SessionManager? {
            if (instance == null) {
                instance =
                    SessionManager(context)
            }
            return instance
        }
    }

    // Constructor
    init {
        pref = _context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = pref.edit()
    }
}