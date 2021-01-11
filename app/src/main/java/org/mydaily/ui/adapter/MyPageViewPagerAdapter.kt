package org.mydaily.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.mydaily.ui.view.mypage.KeywordListFragment
import org.mydaily.ui.view.mypage.RecordKeywordFragment

class MyPageViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment.activity!!) {
    var fragmentList = listOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.count()
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> KeywordListFragment()
            else -> RecordKeywordFragment()
        }
    }
}