package org.mydaily.ui.view.keyword.settings

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordSettingsBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.daily.DailyFragment
import org.mydaily.ui.view.goal.GoalFragment
import org.mydaily.ui.view.mypage.MyPageFragment
import org.mydaily.ui.view.remind.RemindFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.replace

class KeywordSettingsActivity : BaseActivity<ActivityKeywordSettingsBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_settings
    override val viewModel: KeywordViewModel by viewModel()

    private val keywordPriorityFragment: KeywordPriorityFragment by lazy { KeywordPriorityFragment() }
    private val keywordDefineFragment: KeywordDefineFragment by lazy { KeywordDefineFragment() }
    private val keywordDefineAddFragment: KeywordDefineAddFragment by lazy { KeywordDefineAddFragment() }

    override fun initView() {
        replace(R.id.container_keyword_settings, keywordPriorityFragment)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}