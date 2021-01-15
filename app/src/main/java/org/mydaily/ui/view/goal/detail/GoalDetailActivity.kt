package org.mydaily.ui.view.goal.detail

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityGoalDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.custom.GoalAchieveDialog
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.util.CalendarUtil
import java.util.*

class GoalDetailActivity : BaseActivity<ActivityGoalDetailBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_goal_detail
    override val viewModel: GoalViewModel by viewModel()

    private var intentKeyword: String = ""
    private var intentTotalKeywordId: Int = 0
    private var intentWeekGoal: String = ""
    private var intentWeekGoalId: Int = 0
    private var intentIsGoalCompleted: Boolean = false
    private var intentStartDate: Long = 0

    override fun initView() {
        getIntentData()
        initToolbar()
        initViewWithIntent()
        initClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this

    }

    override fun initAfterBinding() {

    }

    private fun getIntentData() {
        intentKeyword = intent.getStringExtra("keyword") ?: ""
        intentTotalKeywordId = intent.getIntExtra("keywordId", 0)
        intentWeekGoal = intent.getStringExtra("weekGoal") ?: ""
        intentWeekGoalId = intent.getIntExtra("weekGoalId", 0)
        intentIsGoalCompleted = intent.getBooleanExtra("isGoalCompleted", false)
        intentStartDate = intent.getLongExtra("startDate", Calendar.getInstance().timeInMillis)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbGoalDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbGoalDetail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initViewWithIntent() {
        binding.apply {
            tvDate.text = CalendarUtil.convertMilliSecToWeekString(intentStartDate)
            tvKeyword.text = intentKeyword
            tvGoal.text = intentWeekGoal
            isGoalCompleted = intentIsGoalCompleted
        }
    }

    private fun initClickEvent() {
        binding.btnAchieve.setOnClickListener {
            if (!intentIsGoalCompleted) {
                GoalAchieveDialog(this).show()
            } else {
                finish()
            }
            intentIsGoalCompleted = !intentIsGoalCompleted
            binding.isGoalCompleted = intentIsGoalCompleted
            viewModel.putGoalsCompletion(intentWeekGoalId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_modify, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_modify -> {
                val intent = Intent(this, GoalAddActivity::class.java).apply {
                    action = "MODIFY"
                    putExtra("keyword", intentKeyword)
                    putExtra("keywordId", intentTotalKeywordId)
                    putExtra("weekGoal", intentWeekGoal)
                    putExtra("weekGoalId", intentWeekGoalId)
                    putExtra("startDate", intentStartDate)
                }
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}