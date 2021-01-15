package org.mydaily.ui.view.remind

import android.content.Intent
import android.os.Handler
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.databinding.FragmentReportDetailBinding
import org.mydaily.ui.adapter.ReportDetailAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.goal.detail.GoalDetailActivity
import org.mydaily.ui.viewmodel.RemindViewModel
import org.mydaily.util.extension.popBackStack
import java.text.SimpleDateFormat
import java.util.*

class ReportDetailFragment : BaseFragment<FragmentReportDetailBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_report_detail
    override val viewModel: RemindViewModel by sharedViewModel()
    private lateinit var detailAdapter: ReportDetailAdapter
    private lateinit var name : String
    private lateinit var weekGoal : String
    private var totalKeywordId : Int = 0
    private var weekGoalId : Int = 0
    private var isGoalCompleted : Boolean = true
    private var isGoalExist : Boolean = false

    override fun initView() {
        requireArguments().getString("keyword")?.let { createDetailAdapter(it) }
        backButtonAction()
        goalClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = activity
    }

    override fun initAfterBinding() {
        observeReportDetailData()
        weekObserve()
    }

    private fun createDetailAdapter(keyWord : String) {
        detailAdapter = activity?.let { ReportDetailAdapter(it, keyWord) }!!
        binding.rcvDailyRecord.adapter = detailAdapter
        binding.rcvDailyRecord.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun observeReportDetailData() {
        viewModel.reportDetailList.observe(requireActivity(), {
            binding.reportdetaildata = it
            if(context != null) {
                weekGoal = it.goal
                if (it.goal != null) {
                    isGoalExist = true
                    binding.tvAchievement.visibility = View.VISIBLE
                    binding.ivArrowRight.visibility = View.VISIBLE
                    name = it.keywordName
                    totalKeywordId = it.totalKeywordId
                    weekGoalId = it.weekGoalId
                    isGoalCompleted = it.isGoalCompleted
                    if (it.isGoalCompleted) {
                        binding.tvAchievement.setBackgroundResource(R.drawable.achviement_container_orange)
                        binding.tvAchievement.text = getString(R.string.report_achieve)
                        binding.tvGoalContent.text = it.goal
                        binding.tvGoal.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.white, null
                            )
                        )
                        binding.tvGoalContent.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.white, null
                            )
                        )
                        binding.clGoal.setBackgroundResource(R.drawable.rectangle_fill_orange)
                        binding.tvAchievement.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.mainOrange, null
                            )
                        )
                        binding.ivArrowRight.setBackgroundResource(R.drawable.ic_arrow_report_detail_white)
                    } else {
                        binding.tvAchievement.setBackgroundResource(R.drawable.achievement_container)
                        binding.tvAchievement.text = getString(R.string.report_not_achieve)
                        binding.tvGoalContent.text = it.goal
                        binding.tvGoal.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.mainBlack, null
                            )
                        )
                        binding.tvGoalContent.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.mainBlack, null
                            )
                        )
                        binding.clGoal.setBackgroundResource(R.drawable.rectangle_fill_gray_white)
                        binding.tvAchievement.setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.white, null
                            )
                        )
                        binding.ivArrowRight.setBackgroundResource(R.drawable.ic_arrow_report_detail)
                    }
                } else {
                    isGoalExist = false
                    binding.ivArrowRight.visibility = View.INVISIBLE
                    binding.tvAchievement.visibility = View.INVISIBLE
                    binding.tvGoal.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.mainBlack, null
                        )
                    )
                    binding.tvGoalContent.text = getString(R.string.report_not_object)
                    binding.tvGoalContent.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.mainGray, null
                        )
                    )
                    binding.clGoal.setBackgroundResource(R.drawable.rectangle_fill_gray_white)
                }
                binding.tvTasknum.text = "총 " + it.tasks.size + "개"
                detailAdapter.setDetailList(it.tasks)
            }
        })
    }

    private fun backButtonAction(){
        requireActivity().tb_main.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_thin_arrow_back)
        requireActivity().tb_main.setNavigationOnClickListener {
            requireActivity().tb_main.navigationIcon = null
            popBackStack()
        }
    }


    private fun goalClickEvent() {
        binding.clGoal.setOnClickListener {
            if(isGoalExist)
                startGoalAddActivity()
        }
    }

    private fun startGoalAddActivity(){
        val intent: Intent = Intent(requireContext(), GoalDetailActivity::class.java).apply {
                putExtra("keyword", name)
                putExtra("weekGoal", weekGoal)
                putExtra("keywordId", totalKeywordId)
                putExtra("weekGoalId", weekGoalId)
                putExtra("isGoalCompleted", isGoalCompleted)
        }
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().tb_main.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_thin_arrow_back)
        Handler().postDelayed({

        }, 100)
        viewModel.startEnd.observe(requireActivity(), {
            viewModel.getReportDetail(ReqReportDetailGet(it[0], it[1], requireArguments().getInt("id")))
        })
    }

    private fun weekObserve() {
        viewModel.startEnd.observe(requireActivity(), {
            binding.tvDate.text = SimpleDateFormat("yy년 MM월 W주", Locale.KOREA).format(it[0])
        })
    }

    override fun onStop() {
        super.onStop()
        requireActivity().tb_main.navigationIcon = null
    }
}