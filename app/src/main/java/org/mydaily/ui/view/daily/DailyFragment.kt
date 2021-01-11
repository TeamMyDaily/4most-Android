package org.mydaily.ui.view.daily

import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.adapter.DailyExpandableAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.daily.detail.DailyAddActivity
import org.mydaily.ui.view.daily.detail.DailyDetailActivity
import org.mydaily.ui.viewmodel.DailyViewModel
import org.mydaily.util.CalendarUtil
import org.mydaily.util.CalendarUtil.compareDateTo
import java.util.*


class DailyFragment : BaseFragment<FragmentDailyBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily
    override val viewModel: DailyViewModel by viewModel()

    private val dailyExpandableAdapter = DailyExpandableAdapter()

    private var changeableCalendar: Calendar = Calendar.getInstance()

    override fun initView() {
        stateCurrentDate()
        initUserData()
        initDateView()
        initTaskRecyclerView()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initAfterBinding() {
        observeKeywordData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTasks(System.currentTimeMillis())
        binding.rvTasks.invalidate()
    }

    private fun initUserData() {
        binding.tvUser.text = FourMostPreference.getUserName() + getString(R.string.your_daily_report)
    }

    private fun initTaskRecyclerView() {
        dailyExpandableAdapter.setAddButtonListener { id, name->
            requireContext().apply {
                val intent = Intent(this, DailyAddActivity::class.java).apply {
                    putExtra("keywordId",id)
                    putExtra("keywordName",name)
                }
                startActivity(intent)
            }
        }
        dailyExpandableAdapter.setTaskClickListener { id, name->
            requireContext().apply {
                val intent = Intent(this, DailyDetailActivity::class.java).apply {
                    putExtra("taskId",id)
                    putExtra("keywordName",name)
                }
                startActivity(intent)
            }
        }
        binding.rvTasks.apply {
            adapter = dailyExpandableAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initDateView() {
        binding.ibDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    changeableCalendar.set(year, month, day, 23, 59, 59)
                    if (nowCalendar.compareDateTo(changeableCalendar)) {
                        stateCurrentDate()
                    } else {
                        stateNotCurrentDate(changeableCalendar)
                    }
                    viewModel.getTasks(changeableCalendar.timeInMillis)
                    Log.e("SEULGI", "clicked ${changeableCalendar.timeInMillis}")
                },
                nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), nowCalendar.get(
                    Calendar.DAY_OF_MONTH
                )
            )
            datePickerDialog.datePicker.maxDate = nowCalendar.timeInMillis
            datePickerDialog.show()
        }

        binding.ivToday.setOnClickListener {
            stateCurrentDate()
            viewModel.getTasks(nowCalendar.timeInMillis)
        }
    }

    private fun stateCurrentDate() {
        binding.date = CalendarUtil.convertCalendarToString(nowCalendar)
        binding.ibDate.isSelected = true
        binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
        binding.ivToday.visibility = View.INVISIBLE
    }

    private fun stateNotCurrentDate(calendar: Calendar) {
        binding.date = CalendarUtil.convertCalendarToString(calendar)
        binding.ibDate.isSelected = false
        binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainGray))
        binding.ivToday.visibility = View.VISIBLE
    }

    private fun observeKeywordData() {
        viewModel.taskList.observe(viewLifecycleOwner, {
            if(it.isNullOrEmpty()){
                if (nowCalendar.compareDateTo(changeableCalendar)) {
                    binding.isNotKeywordExistThisWeek = true
                    binding.isNotKeywordExist = false
                    binding.isKeywordExist = false
                } else {
                    binding.isNotKeywordExistThisWeek = false
                    binding.isNotKeywordExist = true
                    binding.isKeywordExist = false
                }
            }else {
                binding.isNotKeywordExistThisWeek = false
                binding.isNotKeywordExist = false
                binding.isKeywordExist = true
                dailyExpandableAdapter.data = it
            }
        })
    }

    companion object {
        val nowCalendar: Calendar = Calendar.getInstance()
    }

}