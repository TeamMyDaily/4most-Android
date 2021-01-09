package org.mydaily.ui.view.keyword.detail

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_keyword_popup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordPopupBinding
import org.mydaily.ui.adapter.KeywordPopupViewPagerAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.KeywordPopupFirstFragment
import org.mydaily.ui.view.keyword.KeywordPopupSecondFragment
import org.mydaily.ui.view.keyword.KeywordPopupThirdFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPopupActivity : BaseActivity<ActivityKeywordPopupBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_popup
    override val viewModel: KeywordViewModel by viewModel()

    private lateinit var viewpagerAdapter : KeywordPopupViewPagerAdapter


    override fun initView() {
        initBtnVisibility()
        initViewPager()
        initTabLayout()
        viewPagerChange()
    }

    override fun initBeforeBinding() {


    }

    override fun initAfterBinding() {
    }

    private fun initBtnVisibility() {
        btn_popup_next.visibility = View.GONE
        btn_popup_skip.visibility = View.VISIBLE
    }

    private fun initViewPager() {
        viewpagerAdapter = KeywordPopupViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            KeywordPopupFirstFragment(),
            KeywordPopupSecondFragment(),
            KeywordPopupThirdFragment()
        )
        vp_keyword_popup.adapter = viewpagerAdapter
    }
    private fun initTabLayout() {
        tl_keyword_popup.setupWithViewPager(vp_keyword_popup)
    }
    private fun btnOnOff() {
        if(vp_keyword_popup.currentItem == 2) {
            btn_popup_skip.visibility = View.GONE
            btn_popup_next.visibility = View.VISIBLE
        }
    }

    private fun viewPagerChange() {
        vp_keyword_popup?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                btnOnOff()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }
}