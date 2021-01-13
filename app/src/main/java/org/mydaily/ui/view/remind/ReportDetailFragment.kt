package org.mydaily.ui.view.remind

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.databinding.FragmentReportDetailBinding
import org.mydaily.ui.adapter.ReportDetailAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel
import org.mydaily.ui.viewmodel.ReportViewModel
import org.mydaily.util.extension.shortToast

class ReportDetailFragment: BaseFragment<FragmentReportDetailBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report_detail
    override val viewModel: RemindViewModel by viewModel()
    private lateinit var detailAdapter: ReportDetailAdapter

    override fun initView() {
        createDetailAdapter()
        showBackButton()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getReportDetail(ReqReportDetailGet(1610290800000, 1610982000000,17))
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
        viewModel.reportDetailList.observe(this, {
                binding.reportdetaildata = it
                detailAdapter.setDetailList(it.tasks)
        })
    }

    private fun showBackButton() {
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
        this.setHasOptionsMenu(true)
    }

    private fun disappearBackButton(){
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onPause() {
        super.onPause()
        disappearBackButton()
    }

}