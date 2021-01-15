package org.mydaily.ui.view.keyword.settings

import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordSettingsBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.replace

class KeywordSettingsActivity : BaseActivity<ActivityKeywordSettingsBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_settings
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        replace(R.id.container_keyword_settings, KeywordPriorityFragment())
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {

    }

}