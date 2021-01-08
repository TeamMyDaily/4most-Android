package org.mydaily.ui.view.remind

import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_remind.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
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
    lateinit var dateString : String
    var btnVisible : Boolean = true
    val dateFormat = "yy년 MM월 W주"
    val date = Calendar.getInstance()
    val today = date.time

    override fun initView() {
        createViewPager()
        dateConvert()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    fun dateConvert(){
        dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(today)
        binding.tvDate.text = dateString

        binding.ivArrowLeft.setOnClickListener {
            date.add(Calendar.DATE, (2-date.get(Calendar.DAY_OF_WEEK)))
            date.add(Calendar.DATE, -7)
            dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
            binding.tvDate.text = dateString
            convertDateStatus(dateFormat, today)
        }

        binding.ivArrowRight.setOnClickListener {
            date.add(Calendar.DATE, (2-date.get(Calendar.DAY_OF_WEEK)))
            date.add(Calendar.DATE, 7)
            dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
            binding.tvDate.text = dateString
            convertDateStatus(dateFormat, today)
        }

        binding.ivThisWeek.setOnClickListener {
            date.time = today
            dateString = SimpleDateFormat(dateFormat, Locale.KOREA).format(date.time)
            binding.tvDate.text = dateString
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
            binding.ivThisWeek.visibility = View.INVISIBLE
        }
    }

    fun convertDateStatus(format : String, today : Date) {
        if(dateString == SimpleDateFormat(format, Locale.KOREA).format(today)) {
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
            if(btnVisible != false)
                binding.ivThisWeek.visibility = View.INVISIBLE
        }
        else {
            binding.tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
            if(btnVisible != false)
                binding.ivThisWeek.visibility = View.VISIBLE
        }
    }

    fun createViewPager() {
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

}