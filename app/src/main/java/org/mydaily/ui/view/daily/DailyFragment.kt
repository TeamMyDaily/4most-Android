package org.mydaily.ui.view.daily

import android.app.DatePickerDialog
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.expandablelayout.ExpandableLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.adapter.DailyExpandableAdapter
import org.mydaily.ui.adapter.DailyKeywordAdapter
import org.mydaily.ui.base.BaseFragment
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

    override fun initView() {
        stateCurrentDate()
        initUserData()
        initDateView()
        initTaskRecyclerView()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getKeywordData()
    }

    override fun initAfterBinding() {
        /* TODO(통신) : 해당 날짜의 키워드, task 데이터 가져오는 부분 */
        observeKeywordData()
    }

    private fun initUserData() {
        binding.tvUser.text = FourMostPreference.getUserName() + getString(R.string.your_daily_report)
    }

    private fun initTaskRecyclerView() {
        dailyExpandableAdapter.setAddButtonListener {
            requireContext().apply {
                val intent = Intent(this, DailyDetailActivity::class.java).apply {
                    putExtra("keywordId",it)
                    action = "WRITE"
                }
                startActivity(intent)
            }
        }
        dailyExpandableAdapter.setTaskClickListener {
            requireContext().apply {
                val intent = Intent(this, DailyDetailActivity::class.java).apply {
                    putExtra("taskId",it)
                    action = "DETAIL"
                }
                startActivity(intent)
            }
        }
        binding.rvTasks.apply {
            adapter = dailyExpandableAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initDateView() {
        binding.ibDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val newCalendar: Calendar = Calendar.getInstance().apply {
                        set(year, month, day)
                    }
                    if (nowCalendar.compareDateTo(newCalendar)) {
                        stateCurrentDate()
                    } else {
                        stateNotCurrentDate(newCalendar)
                    }
                    //통신
                    //viewModel.getTask(newCalendar.timeInMillis)
                },
                nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), nowCalendar.get(
                    Calendar.DAY_OF_MONTH
                )
            ).show()
        }

        binding.ivToday.setOnClickListener {
            stateCurrentDate()
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
        /* 임시 데이터 */
        viewModel.keywordList.observe(viewLifecycleOwner, {
            dailyExpandableAdapter.data = it
        })
        /* 서버 데이터 */
/*        viewModel.taskList.observe(viewLifecycleOwner, {
            dailyExpandableAdapter.data = it
        })*/
    }

    companion object {
        val nowCalendar: Calendar = Calendar.getInstance()
    }

}