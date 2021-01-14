package org.mydaily.ui.view.goal

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.databinding.FragmentGoalBinding
import org.mydaily.ui.adapter.GoalReportAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.goal.detail.GoalAddActivity
import org.mydaily.ui.view.goal.detail.GoalDetailActivity
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.util.CalendarUtil
import org.mydaily.util.CalendarUtil.isWeekSame
import org.mydaily.util.CalendarUtil.copyYMDFrom
import java.util.*


class GoalFragment : BaseFragment<FragmentGoalBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_goal
    override val viewModel: GoalViewModel by viewModel()

    private val goalReportAdapter = GoalReportAdapter()

    private val nowCalendar = Calendar.getInstance(Locale.KOREA)

    private var startCalendar: Calendar = Calendar.getInstance(Locale.KOREA).apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    private var endCalendar = Calendar.getInstance(Locale.KOREA).apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.DATE, 7)
    }

    override fun initView() {
        initRecyclerView()
        initClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.calendar = Calendar.getInstance()
    }

    override fun initAfterBinding() {
        observeGoalData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getGoals(startCalendar.timeInMillis, endCalendar.timeInMillis)
    }

    private fun initClickEvent() {
        binding.ivArrowLeft.setOnClickListener {
            startCalendar.add(Calendar.DATE, -7)
            endCalendar.add(Calendar.DATE, -7)
            notifyDateChanged()
        }

        binding.ivArrowRight.setOnClickListener {
            startCalendar.add(Calendar.DATE, 7)
            endCalendar.add(Calendar.DATE, 7)
            notifyDateChanged()
        }

        binding.ivThisWeek.setOnClickListener {
            startCalendar.copyYMDFrom(nowCalendar)
            endCalendar.copyYMDFrom(nowCalendar)
            endCalendar.add(Calendar.DATE, 7)
            notifyDateChanged()
        }
    }

    private fun notifyDateChanged() {
        binding.tvDate.apply {
            text = CalendarUtil.convertCalendarToWeekString(startCalendar)
            if (nowCalendar.isWeekSame(startCalendar)) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
                binding.ivThisWeek.visibility = View.GONE
            } else {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                binding.ivThisWeek.visibility = View.VISIBLE
            }
        }
        viewModel.getGoals(startCalendar.timeInMillis, endCalendar.timeInMillis)
    }

    private fun initRecyclerView() {
        binding.layoutKeywordExist.rvGoal.apply {
            adapter = goalReportAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        goalReportAdapter.setAddButtonClickListener {
            startGoalAddActivityWithAction("ADD", it)
        }

        goalReportAdapter.setGoalClickListener {
            if (it.isGoalCreated) {
                startGoalDetailActivity(it)
            } else {
                startGoalAddActivityWithAction("ADD", it)
            }
        }
    }

    private fun observeGoalData() {
        viewModel.goalList.observe(viewLifecycleOwner, {
            Log.e("SEULGI", "goalList = ${it?.size}")
            if(it.isNullOrEmpty()){
                if (nowCalendar.isWeekSame(startCalendar)) {
                    binding.isNotGoalExistThisWeek = true
                    binding.isNotGoalExist = false
                    binding.isGoalExist = false
                } else {
                    binding.isNotGoalExistThisWeek = false
                    binding.isNotGoalExist = true
                    binding.isGoalExist = false
                }
            }else {
                binding.isNotGoalExistThisWeek = false
                binding.isNotGoalExist = false
                binding.isGoalExist = true
                goalReportAdapter.data = it
            }
        })
        viewModel.notSetGoalCount.observe(viewLifecycleOwner, {
            val text = "$it" + getString(R.string.msg_goal_has_not_been_set)
            binding.layoutKeywordExist.tvGoalNumAlert.text = text
        })
    }

    private fun startGoalDetailActivity(goal: ResGoalGet.Data.Result.Keyword) {
        val intent: Intent = Intent(requireContext(), GoalDetailActivity::class.java).apply {
            putExtra("keyword", goal.name)
            putExtra("weekGoal", goal.weekGoal)
            putExtra("keywordId", goal.totalKeywordId)
            putExtra("weekGoalId", goal.weekGoalId)
            putExtra("isGoalCompleted", goal.isGoalCompleted)
        }
        startActivity(intent)
    }

    private fun startGoalAddActivityWithAction(action: String, goal: ResGoalGet.Data.Result.Keyword) {
        val intent: Intent = Intent(requireContext(), GoalAddActivity::class.java).apply {
            this.action = action
            putExtra("keyword", goal.name)
            putExtra("keywordId", goal.totalKeywordId)
            putExtra("weekGoal", goal.weekGoal)
            putExtra("weekGoalId", goal.weekGoalId)
            putExtra("startDate", startCalendar.timeInMillis)
        }
        startActivity(intent)
    }
}