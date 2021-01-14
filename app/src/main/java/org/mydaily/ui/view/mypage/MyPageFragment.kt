package org.mydaily.ui.view.mypage

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.FragmentMyPageBinding
import org.mydaily.ui.adapter.ViewPagerAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.UserViewModel
import org.mydaily.util.extension.shortToast

class MyPageFragment : BaseFragment<FragmentMyPageBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page
    override val viewModel: UserViewModel by viewModel()


    override fun initView() {
        setHasOptionsMenu(true)
        initViewPager()
        binding.tvUser.text = FourMostPreference.getUserName() + getString(R.string.of_user)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_my_page, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                requireContext().shortToast("설정 버튼 클릭됨")
                requireContext().apply {
                    startActivity(Intent(this, MyPageSettingActivity::class.java))
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewPager() {
        val tabLabel = listOf("기록키워드", "키워드 목록")
        val myPageKeywordAdapter = ViewPagerAdapter(this)
        myPageKeywordAdapter.fragmentList = listOf(MyPageCurrentKeywordFragment(), MyPageKeywordListFragment())

        binding.vpMypage.adapter = myPageKeywordAdapter

        TabLayoutMediator(binding.tbMypage, binding.vpMypage){tab, position->
            tab.text = tabLabel[position]
        }.attach()

        binding.vpMypage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}