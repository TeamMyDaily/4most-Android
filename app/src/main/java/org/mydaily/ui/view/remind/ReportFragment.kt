package org.mydaily.ui.view.remind

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.databinding.FragmentReportBinding
import org.mydaily.ui.adapter.ReportKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.ReportViewModel

class ReportFragment : BaseFragment<FragmentReportBinding, ReportViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report
    override val viewModel: ReportViewModel by viewModel()
    private lateinit var reportKeywordAdapter: ReportKeywordAdapter


    override fun initView() {
        createReportAdapter()
    }

    override fun initBeforeBinding() {
        viewModel.getReportData()
    }

    override fun initAfterBinding() {
        observeReportListData()
    }

    private fun observeReportListData() {
        /* 임시 데이터 */
        viewModel.reportList.observe(this, Observer<List<ReportListData>> {
            reportKeywordAdapter.data = it.toMutableList()
        })
    }

    private fun createReportAdapter() {
        reportKeywordAdapter = activity?.let { ReportKeywordAdapter(it) }!!
        binding.rcvReport.adapter = reportKeywordAdapter
        binding.rcvReport.layoutManager = LinearLayoutManager(activity)
    }
}