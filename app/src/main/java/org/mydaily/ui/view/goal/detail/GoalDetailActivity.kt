package org.mydaily.ui.view.goal.detail

import android.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityGoalDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.util.CalendarUtil
import org.mydaily.util.extension.shortToast
import java.util.*

class GoalDetailActivity : BaseActivity<ActivityGoalDetailBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_goal_detail
    override val viewModel: GoalViewModel by viewModel()

    private var intentKeyword: String = ""
    private var intentWeekGoal: String = ""
    private var intentWeekGoalId: Int = 0
    private var intentIsGoalCompleted: Boolean = false

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
        intentWeekGoal = intent.getStringExtra("weekGoal") ?: ""
        intentWeekGoalId = intent.getIntExtra("weekGoalId", 0)
        intentIsGoalCompleted = intent.getBooleanExtra("isGoalCompleted", false)
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
            tvDate.text = CalendarUtil.convertCalendarToWeekString(Calendar.getInstance())
            tvKeyword.text = intentKeyword
            tvGoal.text = intentWeekGoal
            isGoalCompleted = intentIsGoalCompleted
        }
    }

    private fun initClickEvent() {
        binding.btnAchieve.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("타이틀")
                .setMessage("달성여부 변경할거임?")
                .setPositiveButton("확인"){ _, _ ->
                    viewModel.putGoalsCompletion(intentWeekGoalId)
                }
                .setNegativeButton("취소"){ _, _ ->

                }
                .create()
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_goal_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_modify -> {
                val intent = Intent(this, GoalAddActivity::class.java).apply {
                    action = "MODIFY"
                    putExtra("keyword", intentKeyword)
                    putExtra("weekGoal", intentWeekGoal)
                    putExtra("weekGoalId", intentWeekGoalId)
                }
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}