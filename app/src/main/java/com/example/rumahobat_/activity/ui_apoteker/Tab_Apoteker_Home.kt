package com.example.rumahobat_.activity.ui_apoteker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

internal class Tab_Apoteker_Home(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val fragmentList: MutableList<Fragment> =
        ArrayList()
    private val fragmentTitleList: MutableList<String> =
        ArrayList()

    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}