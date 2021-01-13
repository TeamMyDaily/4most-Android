package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.KeywordListItemBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordListFragment : BaseFragment<KeywordListItemBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}