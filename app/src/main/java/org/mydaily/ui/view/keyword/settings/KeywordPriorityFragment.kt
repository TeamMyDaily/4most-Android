package org.mydaily.ui.view.keyword.settings

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_remind_write.view.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.databinding.FragmentKeywordPriorityBinding
import org.mydaily.ui.adapter.ItemTouchHelperCallback
import org.mydaily.ui.adapter.KeywordPriorityAdapter
import org.mydaily.ui.adapter.MyPageKeywordPriorityAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.replace
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
            replaceAndAddBackStack(R.id.container_keyword_settings, KeywordDefineFragment(), "priority")
            val temp = mutableListOf<ReqKeywordPriority.Keyword>()
            var i=1
            for(data in keywordPriorityAdapter.data){
                temp.add(ReqKeywordPriority.Keyword(data, i++))
            }
            viewModel.postKeywordPriority(temp)
            FourMostPreference.setFirstVisit(false)
        }
    }

}