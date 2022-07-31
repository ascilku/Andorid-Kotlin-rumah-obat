package com.example.rumahobat_.activity.ui_apoteker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import com.example.rumahobat_.R
import com.example.rumahobat_.fragment.fragmentApoteker.Chat_Fragment_apoteker
import com.example.rumahobat_.fragment.fragmentApoteker.Forum_Fragment_Apoteker
import com.example.rumahobat_.fragment.fragmentApoteker.Teman_Cerita_Fragment_apoteker
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_home_apoteker.*

var mTabs: TabLayout? = null
var mIndicator: View? = null
//var mViewPager: ViewPager? = null
private var indicatorWidth = 0
class Main_Home_Apoteker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_home_apoteker)
        //Assign view reference
//        mTabs = findViewById(R.id.tab)
//        mIndicator = findViewById(R.id.indicator)
//        mViewPager = findViewById(R.id.viewPager)

        //Set up the view pager and fragments
        val adapter = Tab_Apoteker_Home(supportFragmentManager)
        adapter.addFragment(Chat_Fragment_apoteker.newInstance(), "Chats")
        adapter.addFragment(Teman_Cerita_Fragment_apoteker.newInstance(), "Teman Cerita")
        adapter.addFragment(Forum_Fragment_Apoteker.newInstance(), "Forum")
        viewPager_homeApoteker.setAdapter(adapter)
        tab_homeApoteker.setupWithViewPager(viewPager_homeApoteker)

        //Determine indicator width at runtime
        tab_homeApoteker.post(Runnable {
            indicatorWidth = tab_homeApoteker.getWidth() / tab_homeApoteker.getTabCount()

            //Assign new width
            val indicatorParams =
                indicator_homeApoteker.getLayoutParams() as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            indicator_homeApoteker.setLayoutParams(indicatorParams)
        })
        viewPager_homeApoteker.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            override fun onPageScrolled(
                i: Int,
                positionOffset: Float,
                positionOffsetPx: Int
            ) {
                val params =
                    indicator_homeApoteker.getLayoutParams() as FrameLayout.LayoutParams

                //Multiply positionOffset with indicatorWidth to get translation
                val translationOffset = (positionOffset + i) * indicatorWidth
                params.leftMargin = translationOffset.toInt()
                indicator_homeApoteker.setLayoutParams(params)
            }

            override fun onPageSelected(i: Int) {}
            override fun onPageScrollStateChanged(i: Int) {}
        })
    }
}