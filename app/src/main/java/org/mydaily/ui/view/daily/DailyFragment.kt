package org.mydaily.ui.view.daily

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.expandablelayout.ExpandableLayout
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.adapter.DailyKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.DailyViewModel


/**
 * Expandable Layout does not support Databinding
 */
class DailyFragment : BaseFragment<FragmentDailyBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily
    override val viewModel: DailyViewModel by viewModel()

    private val dailyKeywordAdapter = listOf(
        DailyKeywordAdapter(), DailyKeywordAdapter(), DailyKeywordAdapter(), DailyKeywordAdapter()
    )

    override fun initView() {
        initDateView()
        initExpandableLayout()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getKeywordData()
    }

    override fun initAfterBinding() {
        observeKeywordData(binding.elFirst, 1)
        observeKeywordData(binding.elSecond, 2)
        observeKeywordData(binding.elThird, 3)
        observeKeywordData(binding.elFourth, 4)
    }

    private fun initDateView() {
        binding.ibDate.setOnClickListener {

        }
    }

    private fun initExpandableLayout() {
        initExpandableComponent(binding.elFirst, 1)
        initExpandableComponent(binding.elSecond, 2)
        initExpandableComponent(binding.elThird, 3)
        initExpandableComponent(binding.elFourth, 4)
    }

    private fun initExpandableComponent(expandableLayout: ExpandableLayout, number: Int) {
        initExpandableParentComponent(expandableLayout, number)
        initExpandableSecondComponent(expandableLayout, number)
    }

    private fun initExpandableParentComponent(expandableLayout: ExpandableLayout, number: Int) {
        val parentLayout: View = expandableLayout.parentLayout
        val resourceId = when (number) {
            1 -> R.drawable.ic_01
            2 -> R.drawable.ic_02
            3 -> R.drawable.ic_03
            else -> R.drawable.ic_04
        }
        parentLayout.findViewById<ImageView>(R.id.iv_number).setImageResource(resourceId)
        parentLayout.setOnClickListener {
            if (expandableLayout.isExpanded) {
                parentLayout.findViewById<ImageView>(R.id.iv_spinner).isSelected = false
                expandableLayout.collapse()
            } else {
                parentLayout.findViewById<ImageView>(R.id.iv_spinner).isSelected = true
                expandableLayout.expand()
            }
        }
    }

    private fun initExpandableSecondComponent(expandableLayout: ExpandableLayout, number: Int) {
        expandableLayout.secondLayout.findViewById<RecyclerView>(R.id.rv_weekly_goal).apply {
            adapter = dailyKeywordAdapter[number-1]
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeKeywordData(expandableLayout: ExpandableLayout, number: Int) {
        /* 임시 데이터 */
        viewModel.keywordList.observe(viewLifecycleOwner, {
            expandableLayout.parentLayout.findViewById<TextView>(R.id.tv_number_of_task).text =
                "${it.taskList.size}"
            expandableLayout.parentLayout.findViewById<TextView>(R.id.tv_report_name).text =
                it.keyword + number
            dailyKeywordAdapter[number-1].data = it.taskList
        })
    }

}