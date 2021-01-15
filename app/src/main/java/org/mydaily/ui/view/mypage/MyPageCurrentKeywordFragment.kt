package org.mydaily.ui.view.mypage

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.databinding.FragmentMyPageCurrentKeywordBinding
import org.mydaily.ui.adapter.ItemTouchHelperCallback
import org.mydaily.ui.adapter.KeywordPriorityAdapter
import org.mydaily.ui.adapter.MyPageCurrentKeywordAdapter
import org.mydaily.ui.adapter.MyPageKeywordPriorityAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast

class MyPageCurrentKeywordFragment : BaseFragment<FragmentMyPageCurrentKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_current_keyword
    override val viewModel: KeywordViewModel by viewModel()

    private val myPageCurrentKeywordAdapter = MyPageCurrentKeywordAdapter()
    //private val myPageKeywordPriorityAdapter = MyPageKeywordPriorityAdapter()
    private val myPageKeywordPriorityAdapter = KeywordPriorityAdapter()
    private val dividerItemDecoration: DividerItemDecoration by lazy{
        DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)
        }
    }

    override fun initView() {
        initListLayout()
        initPriorityLayout()
        binding.isCurrentKeywordListVisible = true
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initAfterBinding() {
        observeTaskKeyword()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTaskKeyword()
    }

    private fun initListLayout() {
        binding.layoutMyPageCurrentKeywordList.btnChangePriority.setOnClickListener {
            //binding.isCurrentKeywordListVisible = false
            requireContext().shortToast(R.string.msg_service_is_being_prepared)
        }

        binding.layoutMyPageCurrentKeywordList.rvCurrentKeywordList.apply {
            adapter = myPageCurrentKeywordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun initPriorityLayout() {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(myPageKeywordPriorityAdapter))

        binding.layoutMyPageCurrentKeywordPriority.rvCurrentKeywordListPriority.apply {
            adapter = myPageKeywordPriorityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
            itemTouchHelper.attachToRecyclerView(this)
        }

        binding.layoutMyPageCurrentKeywordPriority.tvAppointPriority.setOnClickListener {
            val temp = mutableListOf<ReqKeywordPriority.Keyword>()
            var i=1
            for(data in myPageKeywordPriorityAdapter.data){
                temp.add(ReqKeywordPriority.Keyword(data, i++))
            }
            viewModel.postKeywordPriority(temp)
            viewModel.getTaskKeyword()
            binding.isCurrentKeywordListVisible = true
        }
    }

    private fun observeTaskKeyword() {
        viewModel.taskKeywordList.observe(viewLifecycleOwner, {
            myPageCurrentKeywordAdapter.data = it
        })
        viewModel.keywordStringList.observe(viewLifecycleOwner, {
            myPageKeywordPriorityAdapter.data = it
        })
    }

}