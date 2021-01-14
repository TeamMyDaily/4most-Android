package org.mydaily.ui.view.mypage

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.FragmentMyPageKeywordListBinding
import org.mydaily.ui.adapter.MyPageCurrentKeywordAdapter
import org.mydaily.ui.adapter.MyPageKeywordListAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.custom.MyPageBottomSheetDialog
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast

class MyPageKeywordListFragment : BaseFragment<FragmentMyPageKeywordListBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    private val myPageKeywordListAdapter = MyPageKeywordListAdapter()

    override fun initView() {
        initRecyclerView()
        binding.tvName.text = FourMostPreference.getUserName()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getKeywordList()

    }

    override fun initAfterBinding() {
        observeKeywordList()
    }

    private fun initRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)

        binding.rvKeywordList.apply {
            adapter = myPageKeywordListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }
        myPageKeywordListAdapter.setClickListener {
            requireContext().shortToast("$it 클릭됨")
            MyPageBottomSheetDialog().show(childFragmentManager, "keyword")
        }
    }

    private fun observeKeywordList() {
        viewModel.keywordList.observe(viewLifecycleOwner, {
            myPageKeywordListAdapter.data = it
        })
    }
}