package org.mydaily.ui.view.keyword

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordSelectBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordSelectFragment : BaseFragment<FragmentKeywordSelectBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_select
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initClickEvent() {
        binding.tvSelectMethod.setOnClickListener {

        }
    }
}