package org.mydaily.ui.view.daily

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.adapter.DailyKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.DailyViewModel


class DailyFragment : BaseFragment<FragmentDailyBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily
    override val viewModel: DailyViewModel by viewModel()

    private val dailyKeywordAdapter = DailyKeywordAdapter()

    override fun initView() {
        initExpandableLayout()
        initRecyclerView()
    }

    override fun initBeforeBinding() {
        viewModel.getKeywordData()
    }

    override fun initAfterBinding() {
        observeKeywordData()
    }

    private fun initExpandableLayout() {
        binding.elGoal.parentLayout.setOnClickListener {
            if(binding.elGoal.isExpanded) binding.elGoal.collapse()
            else binding.elGoal.expand()
        }
    }

    private fun initRecyclerView() {
        binding.rvGoal.apply {
            adapter = dailyKeywordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeKeywordData() {
        viewModel.keywordList.observe(viewLifecycleOwner, {
            dailyKeywordAdapter.data = it
        })
    }
}