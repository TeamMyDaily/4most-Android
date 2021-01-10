package org.mydaily.ui.view.goal

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.domain.Goal
import org.mydaily.databinding.FragmentGoalBinding
import org.mydaily.ui.adapter.GoalReportAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.goal.detail.GoalAddActivity
import org.mydaily.ui.view.goal.detail.GoalDetailActivity
import org.mydaily.ui.viewmodel.GoalViewModel
import java.util.*


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
        binding.calendar = Calendar.getInstance()
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
            //추가
            startGoalAddActivityWithAction("ADD", it)
        }

        goalReportAdapter.setGoalClickListener {
            //상세 및 추가
            if (it.isGoalCreated) {
                startGoalDetailActivity(it)
            } else {
                startGoalAddActivityWithAction("ADD", it)
            }
        }
    }

    private fun observeGoalData() {
        viewModel.goalList.observe(viewLifecycleOwner, {
            goalReportAdapter.data = it
        })
        viewModel.notSetGoalCount.observe(viewLifecycleOwner, {
            val text = "$it"+getString(R.string.msg_goal_has_not_been_set)
            binding.layoutKeywordExist.tvGoalNumAlert.text = text
        })
    }

    private fun startGoalDetailActivity( goal: Goal) {
        val intent: Intent = Intent(requireContext(), GoalDetailActivity::class.java).apply {
            putExtra("keyword", goal.name)
            putExtra("weekGoal", goal.weekGoal)
            putExtra("weekGoalId", goal.weekGoalId)
            putExtra("isGoalCompleted", goal.isGoalCompleted)
        }
        startActivity(intent)
    }
    private fun startGoalAddActivityWithAction(action: String, goal: Goal) {
        val intent: Intent = Intent(requireContext(), GoalAddActivity::class.java).apply {
            this.action = action
            putExtra("keyword", goal.name)
            putExtra("weekGoal", goal.weekGoal)
            putExtra("weekGoalId", goal.weekGoalId)
            putExtra("isGoalCompleted", goal.isGoalCompleted)
        }
        startActivity(intent)
    }
}