package org.mydaily.ui.view.remind

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentReportBinding
import org.mydaily.ui.adapter.ReportKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel

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
            if(it.keywordsExist) {
                if(it.result.isNotEmpty()) {
                    binding.tvTitle.visibility = View.VISIBLE
                    binding.rcvReport.visibility = View.VISIBLE
                    binding.tvReportEmpty.visibility = View.INVISIBLE
                    binding.iv4mostChar.visibility = View.INVISIBLE
                    reportKeywordAdapter.setKeywordList(it.result.toMutableList())
                }
                else {
                    binding.tvReportEmpty.visibility = View.VISIBLE
                    binding.iv4mostChar.visibility = View.VISIBLE
                    binding.tvTitle.visibility = View.INVISIBLE
                    binding.rcvReport.visibility = View.INVISIBLE
                }
            }
            else {
                binding.tvReportEmpty.visibility = View.VISIBLE
                binding.iv4mostChar.visibility = View.VISIBLE
                binding.tvTitle.visibility = View.INVISIBLE
                binding.rcvReport.visibility = View.INVISIBLE
            }
        })
    }

    private fun createReportAdapter() {
        reportKeywordAdapter = activity?.let { ReportKeywordAdapter(it, this) }!!
        binding.rcvReport.adapter = reportKeywordAdapter
        binding.rcvReport.layoutManager = LinearLayoutManager(activity)
    }

    override fun onClick(value: Int, keyword: String) {
        val bundle = Bundle()
        var reportDetailFragment = ReportDetailFragment()
        bundle.putInt("id", value)
        bundle.putString("keyword", keyword)
        reportDetailFragment.arguments = bundle
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, reportDetailFragment)
            .addToBackStack(null).commit()
    }
}