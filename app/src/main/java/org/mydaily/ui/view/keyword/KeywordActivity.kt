package org.mydaily.ui.view.keyword

import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordBinding
import org.mydaily.ui.adapter.ViewPagerStateAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.add.KeywordAddDetailFragment
import org.mydaily.ui.view.keyword.add.KeywordAddFragment
import org.mydaily.ui.view.keyword.guide.KeywordGuideSelectDeepFragment
import org.mydaily.ui.view.keyword.guide.KeywordGuideSelectFragment
import org.mydaily.ui.view.keyword.method.KeywordMethodFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordActivity : BaseActivity<ActivityKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword
    override val viewModel: KeywordViewModel by viewModel()

    private lateinit var viewPagerAdapter: ViewPagerStateAdapter

    private val methodFragment: KeywordMethodFragment by lazy { KeywordMethodFragment() }
    private val guideFragment1: KeywordGuideSelectFragment by lazy { KeywordGuideSelectFragment() }
    private val guideFragment2: KeywordGuideSelectDeepFragment by lazy { KeywordGuideSelectDeepFragment() }
    private val addFragment: KeywordAddFragment by lazy { KeywordAddFragment() }
    private val addDetailFragment: KeywordAddDetailFragment by lazy { KeywordAddDetailFragment() }


    override fun initView() {
        initViewPager()
        initToolbar()
        observeViewPagerPosition()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun initAfterBinding() {}

    private fun initToolbar() {
        binding.tbKeyword.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_help) {
                startActivity(Intent(this, KeywordPopupActivity::class.java))
            }
            true
        }
        binding.tbKeyword.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        when (binding.vpKeyword.currentItem) {
            0 -> super.onBackPressed()
            1 -> binding.vpKeyword.currentItem = 0
            2 -> binding.vpKeyword.currentItem = 1
            3 -> binding.vpKeyword.currentItem = 0
            4 -> binding.vpKeyword.currentItem = 3
        }
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerStateAdapter(supportFragmentManager)
        viewPagerAdapter.fragments = listOf(
            methodFragment,
            guideFragment1,
            guideFragment2,
            addFragment,
            addDetailFragment
        )
        binding.vpKeyword.adapter = viewPagerAdapter
    }

    private fun observeViewPagerPosition() {
        viewModel.viewPagerPosition.observe(this) {
            binding.vpKeyword.currentItem = it
            binding.tbKeyword.menu.findItem(R.id.menu_help).isVisible = (it == 1 || it == 2)
        }
    }
}