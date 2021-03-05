package org.mydaily.ui.view.keyword.settings.define

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordDefineDetailBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack

class KeywordDefineDetailFragment :
    BaseFragment<FragmentKeywordDefineDetailBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_define_detail
    override val viewModel: KeywordViewModel by sharedViewModel()

    private var totalKeywordId : Int = 0

    override fun initView() {
        getArgumentData()
        initToolbar()
    }

    override fun initBeforeBinding() {
        viewModel.getKeywordDefinition(totalKeywordId)
    }

    override fun initAfterBinding() {
        observeKeywordDefinition()
    }

    private fun getArgumentData() {
        totalKeywordId = requireArguments().getInt("totalKeywordId")
    }

    private fun initToolbar() {
        binding.tbKeywordDefineDetail.setNavigationOnClickListener {
            popBackStack()
        }
    }

    private fun observeKeywordDefinition() {
        viewModel.keywordDefinition.observe(viewLifecycleOwner, {
            binding.tvKeyword.text = it.name
            binding.etDefine.setText(it.definition)
        })
    }

}