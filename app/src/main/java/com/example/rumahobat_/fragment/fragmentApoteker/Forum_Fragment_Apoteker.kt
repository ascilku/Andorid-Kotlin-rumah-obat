package com.example.rumahobat_.fragment.fragmentApoteker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rumahobat_.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Forum_Fragment_Apoteker : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum_apoteker, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return Forum_Fragment_Apoteker()
        }
    }

}