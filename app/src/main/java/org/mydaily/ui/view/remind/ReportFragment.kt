package org.mydaily.ui.view.remind

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
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
import org.mydaily.util.CalendarUtil

class ReportFragment : BaseFragment<FragmentReportBinding, RemindViewModel>(), OnItemClick {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report
    override val viewModel: RemindViewModel by sharedViewModel()
    private lateinit var reportKeywordAdapter: ReportKeywordAdapter


    override fun initView() {
        createReportAdapter()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = activity
    }

    override fun initAfterBinding() {
        observeReportListData()
    }

    private fun observeReportListData() {
        viewModel.reportList.observe(requireActivity(), {
            if(it.keywordsExist)
                reportKeywordAdapter.setKeywordList(it.result.toMutableList())
            else {
            }
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
        viewModel.startEnd.observe(requireActivity(), {
            viewModel.getReportDetail(ReqReportDetailGet(it[0], it[1], value))
        })
        reportDetailFragment.arguments = bundle
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, reportDetailFragment)
            .addToBackStack(null).commit()
    }
}