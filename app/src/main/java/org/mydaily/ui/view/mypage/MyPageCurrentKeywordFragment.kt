package org.mydaily.ui.view.mypage

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentMyPageCurrentKeywordBinding
import org.mydaily.ui.adapter.MyPageCurrentKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast

class MyPageCurrentKeywordFragment : BaseFragment<FragmentMyPageCurrentKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_current_keyword
    override val viewModel: KeywordViewModel by viewModel()

    private val myPageCurrentKeywordAdapter = MyPageCurrentKeywordAdapter()

    override fun initView() {
        initClickEvent()
        initRecyclerView()
        binding.isCurrentKeywordListVisible = true
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getTaskKeyword()
    }

    override fun initAfterBinding() {
        observeTaskKeyword()
    }

    private fun initClickEvent(){
        binding.layoutMyPageCurrentKeywordList.btnChangePriority.setOnClickListener {
            binding.isCurrentKeywordListVisible = false
            requireContext().shortToast("우선순위변경하기 버튼 클릭")
        }
        binding.layoutMyPageCurrentKeywordPriority.tvAppointPriority.setOnClickListener {
            binding.isCurrentKeywordListVisible = true
            requireContext().shortToast("우선순위 지정 버튼 클릭")
        }
    }

    private fun initRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)

        binding.layoutMyPageCurrentKeywordList.rvCurrentKeywordList.apply {
            adapter = myPageCurrentKeywordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun observeTaskKeyword() {
        viewModel.taskKeywordList.observe(viewLifecycleOwner, {
            myPageCurrentKeywordAdapter.data = it
        })

    }

}