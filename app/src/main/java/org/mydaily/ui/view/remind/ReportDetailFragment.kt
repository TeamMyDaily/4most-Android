package org.mydaily.ui.view.remind

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.databinding.FragmentReportDetailBinding
import org.mydaily.ui.adapter.ReportDetailAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.ReportViewModel

class ReportDetailFragment: BaseFragment<FragmentReportDetailBinding, ReportViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report_detail
    override val viewModel: ReportViewModel by viewModel()
    private lateinit var detailAdapter: ReportDetailAdapter

    override fun initView() {
        createDetailAdapter()
    }

    override fun initBeforeBinding() {
        viewModel.getReportData()
    }

    override fun initAfterBinding() {
        observeReportDetailData()
    }

    private fun createDetailAdapter() {
        detailAdapter = activity?.let { ReportDetailAdapter(it) }!!
        binding.rcvDailyRecord.adapter = detailAdapter
        binding.rcvDailyRecord.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
    }

    private fun observeReportDetailData() {
        /* 임시 데이터 */
        val args = arguments

        val priority = args?.getInt("data_priority",0)
        viewModel.reportList.observe(this, Observer<List<ReportListData>> {
            val data = priority?.let { it1 -> it[it1] }
            if (data != null) {
                binding.reportdetaildata = data
                detailAdapter.data = data.daily_record.toMutableList()
            }
        })
    }
}