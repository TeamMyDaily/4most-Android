package org.mydaily.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.mydaily.ui.view.mypage.MyPageKeywordListFragment
import org.mydaily.ui.view.mypage.MyPageCurrentKeywordFragment

class ViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment.activity!!) {
    var fragmentList = listOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.count()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}