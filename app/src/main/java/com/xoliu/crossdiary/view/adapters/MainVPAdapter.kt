package com.xoliu.crossdiary.view.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xoliu.crossdiary.view.fragments.MineFragment
import com.xoliu.crossdiary.view.fragments.MemoFragment
import com.xoliu.crossdiary.view.fragments.NoteFragment

class MainVPAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        // 返回页面的数量
        return NUM_PAGES
    }

    private var fragments: ArrayList<Fragment> = ArrayList();
    init {
        fragments.add(NoteFragment())
        fragments.add(MemoFragment())
        fragments.add(MineFragment())

    }
    override fun createFragment(position: Int): Fragment {
        // 根据position创建相应的Fragment
        return fragments.get(position);
    }

    companion object {
        private const val NUM_PAGES = 3 // 假设我们有3个页面
    }

}