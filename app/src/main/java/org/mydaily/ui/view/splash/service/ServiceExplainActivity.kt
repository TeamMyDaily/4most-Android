package org.mydaily.ui.view.splash.service

import android.content.Intent
import android.view.View
import androidx.viewpager.widget.ViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.ActivityServiceExplainBinding
import org.mydaily.ui.adapter.ServiceExplainViewPagerAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.popup.KeywordPopupFirstFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupSecondFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupThirdFragment
import org.mydaily.ui.view.user.SignInActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class ServiceExplainActivity : BaseActivity<ActivityServiceExplainBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_service_explain

    private lateinit var viewpagerAdapter: ServiceExplainViewPagerAdapter

    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initBtnVisibility()
        initViewPager()
        initTabLayout()
        viewPagerChange()
        btnExplainFinishClicked()
        tvExplainSkipClicked()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun btnExplainFinishClicked() {
        binding.btnExplainFinish.setOnClickListener {
            FourMostPreference.setFirstVisit(false)
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    private fun tvExplainSkipClicked() {
        binding.tvExplainSkip.setOnClickListener {
            binding.vpServiceExplain.currentItem = 2
        }
    }

    private fun initViewPager() {
        viewpagerAdapter = ServiceExplainViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            ServiceExplainFirstFragment(),
            ServiceExplainSecondFragment(),
            ServiceExplainThirdFragment()
        )
        binding.vpServiceExplain.adapter = viewpagerAdapter
    }

    private fun initBtnVisibility() {
        binding.btnExplainFinish.visibility = View.GONE
        binding.tvExplainSkip.visibility = View.VISIBLE
    }

    private fun initTabLayout() {
        binding.tlSplash.setupWithViewPager(binding.vpServiceExplain)
    }

    private fun viewPagerChange() {
        binding.vpServiceExplain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

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

    private fun btnOnOff() {
        if (binding.vpServiceExplain.currentItem == 2) {
            binding.tvExplainSkip.visibility = View.GONE
            binding.btnExplainFinish.visibility = View.VISIBLE
        }
    }
}