package org.mydaily.ui.view.remind

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.databinding.FragmentReportBinding
import org.mydaily.ui.adapter.ReportKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.viewmodel.RemindViewModel
import org.mydaily.ui.viewmodel.ReportViewModel

class ReportFragment : BaseFragment<FragmentReportBinding, RemindViewModel>(), OnItemClick {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report
    override val viewModel: RemindViewModel by viewModel()
    private lateinit var reportKeywordAdapter: ReportKeywordAdapter


    override fun initView() {
        createReportAdapter()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getReport(1610290800000, 1610982000000)
    }

    override fun initAfterBinding() {
        observeReportListData()
    }

    private fun observeReportListData() {
        viewModel.reportList.observe(this, {
            reportKeywordAdapter.setKeywordList(it.toMutableList())
        })
    }

    private fun createReportAdapter() {
        reportKeywordAdapter = activity?.let { ReportKeywordAdapter(it, this) }!!
        binding.rcvReport.adapter = reportKeywordAdapter
        binding.rcvReport.layoutManager = LinearLayoutManager(activity)
    }

    override fun onClick(value: Int) {
        val bundle = Bundle()
        var reportDetailFragment = ReportDetailFragment()
        bundle.putInt("data_priority", value);
        reportDetailFragment.arguments = bundle

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, reportDetailFragment)
            .addToBackStack(null).commit()
    }
}