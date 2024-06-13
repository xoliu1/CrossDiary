package com.xoliu.crossdiary.view.activities

import android.R
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.xoliu.crossdiary.base.BaseActivity
import com.xoliu.crossdiary.databinding.ActivityMainBinding
import com.xoliu.crossdiary.view.adapters.MainVPAdapter


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }


    override fun setup() {
        super.setup()


        setupVP2()



    }

    private fun setupVP2() {
        


        binding.mainViewPager.adapter = MainVPAdapter(this)
        binding.mainViewPager.offscreenPageLimit = 3
        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text =  when (position) {
                0 -> "条目"
                1 -> "树洞"
                2 -> "穿梭"
                else -> ""
            } }.attach()
//        binding.tabLayout.getTabAt(1)?.let { tab ->
//            tab.orCreateBadge.backgroundColor = ContextCompat.getColor(this, R.color.red1)
//        }

    }


}
