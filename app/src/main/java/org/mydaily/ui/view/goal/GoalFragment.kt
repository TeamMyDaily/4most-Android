package org.mydaily.ui.view.goal

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.Goal
import org.mydaily.databinding.FragmentGoalBinding
import org.mydaily.ui.adapter.GoalReportAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.util.extension.shortToast

class GoalFragment : BaseFragment<FragmentGoalBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_goal
    override val viewModel: GoalViewModel by viewModel()

    private val goalReportAdapter = GoalReportAdapter()

    override fun initView() {
        initRecyclerView()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getGoalData()
    }

    override fun initAfterBinding() {
        observeGoalData()
    }

    private fun initRecyclerView() {
        binding.rvGoal.apply {
            adapter = goalReportAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        goalReportAdapter.setPlusButtonClickListener {
            requireContext().shortToast("+ 버튼 클릭 -> ${it.keyword}: 목표 존재=${it.isGoalExist}")
            /* 목표 상세 페이지로 이동(설정/추가) */

        }

        goalReportAdapter.setGoalClickListener {
            requireContext().shortToast("목표 클릭 -> ${it.keyword}: 목표 존재=${it.isGoalExist}")
            /* 목표 상세 페이지로 이동(수정) */

        }
    }

    private fun observeGoalData() {
        viewModel.goalList.observe(viewLifecycleOwner, {
            goalReportAdapter.data = it
            val info = "3" + getString(R.string.msg_goal_has_not_been_set)
            binding.tvGoalNumAlert.text = info
        })
    }
}