package org.mydaily.ui.view.keyword.popup

import android.view.View
import androidx.viewpager.widget.ViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordPopupBinding
import org.mydaily.ui.adapter.ViewPagerStateAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPopupActivity : BaseActivity<ActivityKeywordPopupBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_popup
    override val viewModel: KeywordViewModel by viewModel()

    private lateinit var viewpagerAdapter: ViewPagerStateAdapter

    override fun initView() {
        initBtnVisibility()
        initViewPager()
        initTabLayout()
        viewPagerChange()
        btnPopupFinishClicked()
        tvPopupSkipClicked()
    }

    private fun btnPopupFinishClicked() {
        binding.btnPopupFinish.setOnClickListener {
            finish()
        }
    }

    private fun tvPopupSkipClicked() {
        binding.tvPopupSkip.setOnClickListener {
            binding.vpKeywordPopup.currentItem = 2
        }
    }

    override fun initBeforeBinding() {}

    override fun initAfterBinding() {}

    private fun initBtnVisibility() {
        binding.btnPopupFinish.visibility = View.GONE
        binding.tvPopupSkip.visibility = View.VISIBLE
    }

    private fun initViewPager() {
        viewpagerAdapter = ViewPagerStateAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            KeywordPopupFirstFragment(),
            KeywordPopupSecondFragment(),
            KeywordPopupThirdFragment()
        )
        binding.vpKeywordPopup.adapter = viewpagerAdapter
    }

    private fun initTabLayout() {
        binding.tlKeywordPopup.setupWithViewPager(binding.vpKeywordPopup)
    }

    private fun btnOnOff() {
        if (binding.vpKeywordPopup.currentItem == 2) {
            binding.tvPopupSkip.visibility = View.GONE
            binding.btnPopupFinish.visibility = View.VISIBLE
        }
    }

    private fun viewPagerChange() {
        binding.vpKeywordPopup.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                btnOnOff()
            }

            override fun onPageSelected(position: Int) {
                btnOnOff()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

    }
}