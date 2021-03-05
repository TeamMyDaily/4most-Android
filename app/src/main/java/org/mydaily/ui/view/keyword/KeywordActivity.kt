package org.mydaily.ui.view.keyword

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.method.KeywordMethodFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.replace

class KeywordActivity : BaseActivity<ActivityKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword
    override val viewModel: KeywordViewModel by viewModel()

    private val selectFragment: KeywordMethodFragment by lazy { KeywordMethodFragment() }

    override fun initView() {
        replace(R.id.container_keyword, selectFragment)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}