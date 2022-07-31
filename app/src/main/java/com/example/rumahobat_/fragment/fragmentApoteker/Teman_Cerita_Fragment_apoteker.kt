package com.example.rumahobat_.fragment.fragmentApoteker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rumahobat_.R


class Teman_Cerita_Fragment_apoteker : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teman__cerita__apoteker, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return Teman_Cerita_Fragment_apoteker()
        }
    }


}