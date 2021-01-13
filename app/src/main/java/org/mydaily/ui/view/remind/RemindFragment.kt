package org.mydaily.ui.view.remind

import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_remind.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.ui.adapter.RemindViewPagerAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel
import java.text.SimpleDateFormat
import java.util.*


class RemindFragment : BaseFragment<FragmentRemindBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind
    override val viewModel: RemindViewModel by viewModel()
    private lateinit var dateString : String
    private var btnVisible : Boolean = true
    private val dateFormat = "yy년 MM월 W주"
    private val date = Calendar.getInstance()
    private var today = date.time
    private var start : Long = 0
    private var end : Long = 0

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
        todayInit()
        dateInit()
        //dateBtnClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {

    }

    private fun dateBtnClickEvent(){
        binding.ivArrowLeft.setOnClickListener {
            startCalendar.add(Calendar.DATE, -7)
            endCalendar.add(Calendar.DATE, -7)

            dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
            binding.tvDate.text = dateString
            convertDateStatus(dateFormat, today)
        }

        binding.ivArrowRight.setOnClickListener {
            date.set(Calendar.DATE, Calendar.MONDAY)
            startCalendar.add(Calendar.DATE, 7)
            endCalendar.add(Calendar.DATE, 7)
            if(!date.after(today)) {
                start = date.timeInMillis
                dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
                date.add(Calendar.DATE, 6)
                end = date.timeInMillis
                binding.tvDate.text = dateString
                convertDateStatus(dateFormat, today)
            }
            else
                date.set(Calendar.DATE, Calendar.SUNDAY)
        }

        binding.ivThisWeek.setOnClickListener {
            startCalendar.copyYMDFrom(nowCalendar)
            endCalendar.copyYMDFrom(nowCalendar)
            endCalendar.add(Calendar.DATE, 7)
            date.time = today
            dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
            binding.tvDate.text = dateString
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
            binding.ivThisWeek.visibility = View.INVISIBLE
            date.set(Calendar.DATE, Calendar.MONDAY)
            start = date.timeInMillis
            date.add(Calendar.DATE, 6)
            end = date.timeInMillis
        }
    }

    private fun convertDateStatus(format : String, today : Date) {
        if(dateString == SimpleDateFormat(format, Locale.KOREA).format(today)) {
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
            if(btnVisible)
                binding.ivThisWeek.visibility = View.INVISIBLE
        }
        else {
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
            if(btnVisible)
                binding.ivThisWeek.visibility = View.VISIBLE
        }
    }

    private fun createViewPager() {
        var tab_label = listOf("리포트", "회고")
        var fragmentList = listOf(ReportFragment(), RemindFragment())
        val remindAdapter = RemindViewPagerAdapter(this)
        remindAdapter.fragmentList = fragmentList

        vp_remind.adapter = remindAdapter

        TabLayoutMediator(tb_remind, vp_remind){tab, position->
            tab.text = tab_label[position]
        }.attach()

        binding.vpRemind.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0) {
                    btnVisible = true
                    convertDateStatus(dateFormat, today)
                }
                if(position == 1) {
                    binding.ivThisWeek.visibility = View.INVISIBLE
                    btnVisible = false
                }
            }
        })
    }

    private fun dateInit() {
        dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(today)
        binding.tvDate.text = dateString

        date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        start = date.timeInMillis

        date.add(Calendar.DAY_OF_WEEK, 6)
        end = date.timeInMillis
    }

    private fun todayInit() {
        date.set(Calendar.HOUR_OF_DAY, 0)
        date.set(Calendar.MINUTE, 0)
        date.set(Calendar.SECOND, 0)
        date.set(Calendar.MILLISECOND, 0)
        today = date.time
    }
}