package org.mydaily.ui.view.keyword.settings.priority

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.databinding.FragmentKeywordPriorityBinding
import org.mydaily.ui.adapter.ItemTouchHelperCallback
import org.mydaily.ui.adapter.KeywordPriorityAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.keyword.settings.define.KeywordDefineFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.replaceAndAddBackStack

class KeywordPriorityFragment : BaseFragment<FragmentKeywordPriorityBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_priority
    override val viewModel: KeywordViewModel by sharedViewModel()

    private val keywordPriorityAdapter = KeywordPriorityAdapter()

    private var keywordList : ArrayList<String>?= null

    override fun initView() {
        getIntent()
        initToolbar()
        initRecyclerView()
        initButton()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getTaskKeyword()
    }

    override fun initAfterBinding() {

    }

    private fun getIntent() {
        keywordList = requireActivity().intent.getStringArrayListExtra("keywords")
    }


    private fun initToolbar() {
        binding.tbKeywordPriority.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initRecyclerView() {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(keywordPriorityAdapter))

        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)

        binding.rvPriority.apply {
            adapter = keywordPriorityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            itemTouchHelper.attachToRecyclerView(this)
        }

        keywordPriorityAdapter.data = keywordList?.toList() ?: listOf()
    }

    private fun initButton() {
        binding.btnSetPriority.setOnClickListener {
            val temp = mutableListOf<ReqKeywordPriority.Keyword>()
            val stringList = arrayListOf<String>()
            var i=1
            for(data in keywordPriorityAdapter.data){
                stringList.add(data)
                temp.add(ReqKeywordPriority.Keyword(data, i++))
            }

            val keywordDefineFragment = KeywordDefineFragment()
            keywordDefineFragment.arguments = Bundle().apply {
                putStringArrayList("keywords", stringList)
            }

            replaceAndAddBackStack(R.id.container_keyword_settings, keywordDefineFragment, "priority")

            viewModel.postKeywordPriority(temp)
        }
    }

}