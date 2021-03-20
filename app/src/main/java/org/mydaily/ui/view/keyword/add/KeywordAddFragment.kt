package org.mydaily.ui.view.keyword.add

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordAddBinding
import org.mydaily.ui.adapter.KeywordAddAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack
import org.mydaily.util.extension.replaceAndAddBackStack

class KeywordAddFragment : BaseFragment<FragmentKeywordAddBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_add
    override val viewModel: KeywordViewModel by sharedViewModel()

    private val keywordAddAdapter = KeywordAddAdapter()
    override fun initView() {
        initPlusButton()
        setRecyclerView()
        keywordAddEvent()
        keywordSelectCompleteIsEnabled()
    }

    override fun initBeforeBinding() { }

    override fun initAfterBinding() { }

    private fun keywordAddEvent() {
        binding.clPlusValue.setOnClickListener {
            viewModel.viewPagerPosition.value = KeywordViewModel.KEYWORD_ADD_DETAIL_POSITION
        }
    }

    private fun setRecyclerView() {
        keywordAddAdapter.data = viewModel.addWordList
        binding.rcvImportantValue.apply {
            adapter = keywordAddAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun keywordSelectCompleteIsEnabled() {
        binding.btnAdd.isEnabled = viewModel.addWordList.isNotEmpty()
    }

    private fun initPlusButton() {
        if(viewModel.addWordList.size >= 4) {
            binding.clPlusValue.visibility = View.GONE
            binding.ivPlusBtn.visibility = View.GONE
        }
    }
}