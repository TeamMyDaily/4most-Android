package org.mydaily.ui.view.remind

import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_remind.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.ui.adapter.RemindViewPagerAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel
import org.mydaily.util.CalendarUtil
import org.mydaily.util.CalendarUtil.isWeekSame
import java.util.*


class RemindFragment : BaseFragment<FragmentRemindBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind
    override val viewModel: RemindViewModel by sharedViewModel()
    private var btnVisible: Boolean = true
    private var start: Long = 0
    private var end: Long = 0

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
        createViewPager()
        dateInit()
        dateBtnClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = activity
        viewModel.setStartEnd(startCalendar.timeInMillis, endCalendar.timeInMillis)
        viewModel.getReport(startCalendar.timeInMillis, endCalendar.timeInMillis)
        viewModel.getReview(startCalendar.timeInMillis, endCalendar.timeInMillis)
    }

    override fun initAfterBinding() {

    }

    private fun dateBtnClickEvent() {
        binding.ivArrowLeft.setOnClickListener {
            startCalendar.add(Calendar.DATE, -7)
            endCalendar.add(Calendar.DATE, -7)
            binding.tvDate.text = CalendarUtil.convertCalendarToWeekString(startCalendar)
            convertDateStatus()
            //값전달
            viewModel.setStartEnd(startCalendar.timeInMillis, endCalendar.timeInMillis)
            viewModel.getReport(startCalendar.timeInMillis, endCalendar.timeInMillis)
            viewModel.getReview(startCalendar.timeInMillis, endCalendar.timeInMillis)
        }

        binding.ivArrowRight.setOnClickListener {
            startCalendar.add(Calendar.DATE, 7)
            endCalendar.add(Calendar.DATE, 7)
            if (CalendarUtil.convertCalendarToWeekString(startCalendar) <= CalendarUtil.convertCalendarToWeekString(
                    nowCalendar
                )
            ) {
                start = startCalendar.timeInMillis
                end = endCalendar.timeInMillis
                binding.tvDate.text = CalendarUtil.convertCalendarToWeekString(startCalendar)
                convertDateStatus()
                //값전
                viewModel.setStartEnd(startCalendar.timeInMillis, endCalendar.timeInMillis)
                viewModel.getReport(startCalendar.timeInMillis, endCalendar.timeInMillis)
                viewModel.getReview(startCalendar.timeInMillis, endCalendar.timeInMillis)
            } else {
                startCalendar.add(Calendar.DATE, -7)
                endCalendar.add(Calendar.DATE, -7)
            }
            //값전
        }

        binding.ivThisWeek.setOnClickListener {
            startCalendar = Calendar.getInstance(Locale.KOREA)
            startCalendar.apply {
                set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            endCalendar = Calendar.getInstance(Locale.KOREA)
            endCalendar.apply {
                set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                add(Calendar.DATE, 7)
            }
            binding.tvDate.text = CalendarUtil.convertCalendarToWeekString(startCalendar)
            binding.tvDate.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.mainOrange
                )
            )
            binding.ivThisWeek.visibility = View.INVISIBLE
            start = startCalendar.timeInMillis
            end = endCalendar.timeInMillis
            //값
            viewModel.getReport(startCalendar.timeInMillis, endCalendar.timeInMillis)
            viewModel.setStartEnd(startCalendar.timeInMillis, endCalendar.timeInMillis)
            viewModel.getReview(startCalendar.timeInMillis, endCalendar.timeInMillis)
        }
    }

    private fun convertDateStatus() {
        if (startCalendar.isWeekSame(nowCalendar)) {
            binding.tvDate.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.mainOrange
                )
            )
            if (btnVisible)
                binding.ivThisWeek.visibility = View.INVISIBLE
        } else {
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
            if (btnVisible)
                binding.ivThisWeek.visibility = View.VISIBLE
        }
    }

    private fun createViewPager() {
        var tab_label = listOf("리포트", "회고")
        var fragmentList = listOf(ReportFragment(), RemindFragment())
        val remindAdapter = RemindViewPagerAdapter(this)
        remindAdapter.fragmentList = fragmentList

        vp_remind.adapter = remindAdapter

        TabLayoutMediator(tb_remind, vp_remind) { tab, position ->
            tab.text = tab_label[position]
        }.attach()

        binding.vpRemind.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    btnVisible = true
                    convertDateStatus()
                }
                if (position == 1) {
                    binding.ivThisWeek.visibility = View.INVISIBLE
                    btnVisible = false
                }
            }
        })

    }

    private fun dateInit() {
        binding.tvDate.text = CalendarUtil.convertCalendarToWeekString(startCalendar)
        convertDateStatus()

        //값전달
    }

    override fun onStart() {
        super.onStart()
        viewModel.setStartEnd(startCalendar.timeInMillis, endCalendar.timeInMillis)
        viewModel.getReview(startCalendar.timeInMillis, endCalendar.timeInMillis)
    }
}