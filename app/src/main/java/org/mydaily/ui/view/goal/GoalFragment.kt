package org.mydaily.ui.view.goal

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.Goal
import org.mydaily.databinding.FragmentGoalBinding
import org.mydaily.ui.adapter.GoalReportAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.goal.detail.GoalDetailActivity
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
        binding.layoutKeywordExist.rvGoal.apply {
            adapter = goalReportAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        goalReportAdapter.setAddButtonClickListener {
            requireContext().shortToast("+ 버튼 클릭 -> ${it.keyword}: 목표 존재=${it.isGoalExist}")
/*            *//* 목표 상세 페이지로 이동(설정/추가) *//*
            startGoalDetailActivityWithAction("ADD", it)*/

        }

        goalReportAdapter.setGoalClickListener {
            requireContext().shortToast("목표 클릭 -> ${it.keyword}: 목표 존재=${it.isGoalExist}")
/*            *//* 목표 상세 페이지로 이동(수정) *//*
            startGoalDetailActivityWithAction("MODIFY", it)*/
        }
    }

    private fun observeGoalData() {

        viewModel.goalList.observe(this, {
            goalReportAdapter.data = it
        })
    }

    private fun startGoalDetailActivityWithAction(action: String, goal: Goal) {
        val intent: Intent = Intent(requireActivity(), GoalDetailActivity::class.java).apply {
            this.action = action
            putExtra("keyword", goal.keyword)
            putExtra("goal", goal.goal)
        }
        startActivity(intent)
    }
}