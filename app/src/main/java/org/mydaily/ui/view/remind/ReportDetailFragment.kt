package org.mydaily.ui.view.remind

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
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
    override val viewModel: RemindViewModel by sharedViewModel()
    private lateinit var detailAdapter: ReportDetailAdapter

    override fun initView() {
        createDetailAdapter()
        showBackButton()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = activity
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
        viewModel.reportDetailList.observe(requireActivity(), {
                binding.reportdetaildata = it
                if(it.goal != null) {
                    binding.tvAchievement.visibility = View.VISIBLE
                    binding.ivArrowRight.visibility = View.VISIBLE
                    if (it.isGoalCompleted) {
                        binding.tvAchievement.setBackgroundResource(R.drawable.achviement_container_orange)
                        binding.tvAchievement.text = "달성"
                        binding.tvGoalContent.text = it.goal
                        binding.tvGoal.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvGoalContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.clGoal.setBackgroundResource(R.drawable.report_detail_goal_container_orange)
                        binding.tvAchievement.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainOrange))
                        binding.ivArrowRight.setBackgroundResource(R.drawable.ic_arrow_report_detail_white)
                    } else {
                        binding.tvAchievement.setBackgroundResource(R.drawable.achievement_container)
                        binding.tvAchievement.text = "미달성"
                        binding.tvGoalContent.text = it.goal
                        binding.tvGoal.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                        binding.tvGoalContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                        binding.clGoal.setBackgroundResource(R.drawable.report_detail_goal_container)
                        binding.tvAchievement.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.ivArrowRight.setBackgroundResource(R.drawable.ic_arrow_report_detail)
                    }
                }
                else {
                    binding.ivArrowRight.visibility = View.INVISIBLE
                    binding.tvAchievement.visibility = View.INVISIBLE
                    binding.tvGoal.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                    binding.tvGoalContent.text = "이번주 작성된 목표가 없어요!"
                    binding.tvGoalContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainGray))
                    binding.clGoal.setBackgroundResource(R.drawable.report_detail_goal_container)
                }
                binding.tvTasknum.text = "총 " + it.tasks.size +"개"
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