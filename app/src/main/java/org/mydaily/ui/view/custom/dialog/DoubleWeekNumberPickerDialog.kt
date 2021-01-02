package org.mydaily.ui.view.custom.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import org.mydaily.data.model.WeekNumber
import org.mydaily.databinding.DialogWeekNumberPickerBinding
import org.mydaily.databinding.DialogWeekNumberPickerFragmentBinding
import org.mydaily.ui.adapter.ViewPagerAdapter
import java.util.*


class DoubleWeekNumberPickerDialog: DialogFragment() {

    private lateinit var binding: DialogWeekNumberPickerBinding

    private val firstFragment : PickerFragment by lazy { PickerFragment(PickerFragment.DEFAULT)}
    private val secondFragment : PickerFragment by lazy { PickerFragment(PickerFragment.NOW)}

    private var positiveButtonClickListener: ((WeekNumber, WeekNumber) -> Unit) ?= null

    fun setOnPositiveButtonClickListener(listener: ((WeekNumber, WeekNumber) -> Unit)): DoubleWeekNumberPickerDialog{
        this.positiveButtonClickListener = listener
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWeekNumberPickerBinding.inflate(inflater, container, false)

        initViewPager()
        initPositiveButton()
        initNegativeButton()

        return binding.root
    }

    private fun initViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.setFragmentList(
            listOf(firstFragment, secondFragment)
        )

        binding.viewPager.adapter = viewPagerAdapter

        binding.tlDate.apply {
            setupWithViewPager(binding.viewPager)
            getTabAt(0)?.text = "시작"
            getTabAt(1)?.text = "종료"
        }
    }

    private fun initPositiveButton() {
        binding.btnPositive.setOnClickListener {
            positiveButtonClickListener?.invoke(firstFragment.getWeekNumber(), secondFragment.getWeekNumber())
            dismiss()
        }
    }

    private fun initNegativeButton() {
        binding.btnNegative.setOnClickListener {
            dismiss()
        }
    }

    class PickerFragment(private val number: Int): Fragment() {

        private lateinit var binding: DialogWeekNumberPickerFragmentBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = DialogWeekNumberPickerFragmentBinding.inflate(inflater, container, false)

            when(number){
                DEFAULT -> setCalendar(Calendar.getInstance())
                NOW -> setDefaultCalendar(Calendar.getInstance())
            }

            return binding.root
        }

        private fun setCalendar(calendar: Calendar) {
            binding.npYear.apply {
                minValue = 1
                maxValue = calendar.get(Calendar.YEAR)
                value = calendar.get(Calendar.YEAR)
                wrapSelectorWheel = false
            }

            binding.npMonth.apply {
                minValue = 1
                maxValue = calendar.get(Calendar.MONTH)+1
                value = calendar.get(Calendar.MONTH)+1
                wrapSelectorWheel = false
            }

            binding.npWeek.apply {
                minValue = 1
                maxValue = calendar.get(Calendar.WEEK_OF_MONTH)
                value = calendar.get(Calendar.WEEK_OF_MONTH)
                wrapSelectorWheel = false
            }
        }

        private fun setDefaultCalendar(calendar: Calendar) {
            binding.npYear.apply {
                minValue = 1
                maxValue = calendar.get(Calendar.YEAR)
                value = calendar.get(Calendar.YEAR)
                wrapSelectorWheel = false
            }

            binding.npMonth.apply {
                minValue = 1
                maxValue = 12
                value = calendar.get(Calendar.MONTH)+1
                wrapSelectorWheel = false
            }

            binding.npWeek.apply {
                minValue = 1
                maxValue = 6
                value = calendar.get(Calendar.WEEK_OF_MONTH)
                wrapSelectorWheel = false
            }
        }

        fun getWeekNumber() : WeekNumber{
            return WeekNumber(binding.npYear.value, binding.npMonth.value, binding.npWeek.value)
        }

        companion object{
            const val DEFAULT = 1
            const val NOW = 2
        }
    }
}