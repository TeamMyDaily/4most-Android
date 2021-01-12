package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.FragmentMyPageKeywordListBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class MyPageKeywordListFragment : BaseFragment<FragmentMyPageKeywordListBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {

        binding.tvName.text = FourMostPreference.getUserName()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}