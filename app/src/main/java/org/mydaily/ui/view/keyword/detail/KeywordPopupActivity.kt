package org.mydaily.ui.view.keyword.detail

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_keyword_popup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordPopupBinding
import org.mydaily.ui.adapter.KeywordPopupVPAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.Keyword_Popup_FirstFragment
import org.mydaily.ui.view.keyword.Keyword_Popup_SecondFragment
import org.mydaily.ui.view.keyword.Keyword_Popup_ThirdFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPopupActivity : BaseActivity<ActivityKeywordPopupBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_popup
    override val viewModel: KeywordViewModel by viewModel()

    private lateinit var viewpagerAdapter : KeywordPopupVPAdapter


    override fun initView() {
        initbtnvisibility()
        initViewPager()
        initTabLayout()
        btnOnOff()
    }

    override fun initBeforeBinding() {


    }

    override fun initAfterBinding() {
    }

    private fun initbtnvisibility() {
        btn_popup_finish.visibility = View.GONE
        btn_popup_skip.visibility = View.VISIBLE
    }

    private fun initViewPager() {
        viewpagerAdapter = KeywordPopupVPAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            Keyword_Popup_FirstFragment(),
            Keyword_Popup_SecondFragment(),
            Keyword_Popup_ThirdFragment()
        )
        vp_keyword_popup.adapter = viewpagerAdapter
    }
    private fun initTabLayout() {
        tl_keyword_popup.setupWithViewPager(vp_keyword_popup)
    }
    private fun btnOnOff() {
        if(vp_keyword_popup.currentItem == 2) {
            btn_popup_skip.visibility = View.GONE
            btn_popup_finish.visibility = View.VISIBLE
        }
    }
}