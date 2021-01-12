package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentMyPageCurrentKeywordBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class MyPageCurrentKeywordFragment : BaseFragment<FragmentMyPageCurrentKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_current_keyword
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initClickEvent()
        binding.isCurrentKeywordListVisible = true

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initClickEvent(){
        binding.layoutMyPageCurrentKeywordList.btnChangePriority.setOnClickListener {
            binding.isCurrentKeywordListVisible = false
        }
        binding.layoutMyPageCurrentKeywordPriority.tvAppointPriority.setOnClickListener {
            binding.isCurrentKeywordListVisible = true
        }
    }
}