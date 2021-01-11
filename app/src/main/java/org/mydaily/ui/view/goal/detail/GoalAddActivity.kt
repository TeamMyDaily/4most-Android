package org.mydaily.ui.view.goal.detail

import android.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityGoalAddBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.GoalViewModel

class GoalAddActivity : BaseActivity<ActivityGoalAddBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_goal_add
    override val viewModel: GoalViewModel by viewModel()

    private var intentAction: String? = null
    private var intentKeyword: String = ""
    private var intentTotalKeywordId: Int = 0
    private var intentWeekGoal: String = ""
    private var intentWeekGoalId: Int = 0
    private var intentStartDate: Long = 0

    private var isGoalChanged: Boolean = false

    override fun initView() {
        getIntentData()
        initToolbar()
        initViewWithAction()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun getIntentData() {
        intentAction = intent.action
        intentKeyword = intent.getStringExtra("keyword") ?: ""
        intentTotalKeywordId = intent.getIntExtra("keywordId", 0)
        intentWeekGoal = intent.getStringExtra("weekGoal") ?: ""
        intentWeekGoalId = intent.getIntExtra("weekGoalId", 0)
        intentStartDate = intent.getLongExtra("startDate", 0)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbGoalAdd)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbGoalAdd.setNavigationOnClickListener {
            //수정 시도했을때
            if (isGoalChanged) {
                AlertDialog.Builder(this)
                    .setTitle("타이틀")
                    .setMessage("수정사항 저장?")
                    .setPositiveButton("확인") { _, _ ->
                        viewModel.postGoals(
                            System.currentTimeMillis(),
                            intentTotalKeywordId.toString(),
                            binding.etGoal.text.toString()
                        )
                        finish()
                    }
                    .setNegativeButton("취소") { _, _ ->

                    }
                    .create()
                    .show()

            }
            //수정 시도하지 않은 경우
            else {
                finish()
            }
        }
    }

    private fun initViewWithAction() {
        binding.tvKeyword.text = intentKeyword
        binding.btnAddSave.isEnabled = false

        when (intentAction) {
            "ADD" -> stateAdd()
            "MODIFY" -> stateModify()
        }
    }

    private fun stateAdd() {
        binding.etGoal.addTextChangedListener {
            val length = binding.etGoal.length()
            isGoalChanged = true
            binding.tvByte.text = length.toString()
            binding.btnAddSave.isEnabled = isGoalChanged && length > 0
        }
        binding.btnAddSave.setOnClickListener {
            //추가 완료 후 메인으로 돌아감
            viewModel.postGoals(
                intentStartDate,
                intentTotalKeywordId.toString(),
                binding.etGoal.text.toString()
            )
            finish()
        }
    }


    private fun stateModify() {
        binding.etGoal.setText(intentWeekGoal)
        binding.etGoal.addTextChangedListener {
            val length = binding.etGoal.length()
            isGoalChanged = true
            binding.tvByte.text = length.toString()
            binding.btnAddSave.isEnabled = isGoalChanged && length > 0
        }
        binding.btnAddSave.setOnClickListener {
            //수정 완료 후 메인으로 돌아감
            viewModel.putGoals(
                intentWeekGoalId,
                binding.etGoal.text.toString()
            )
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (intentAction == "MODIFY") {
            menuInflater.inflate(R.menu.menu_goal_add, menu)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                AlertDialog.Builder(this)
                    .setMessage("목표를 삭제하시겠어요?")
                    .setPositiveButton("삭제하기") { _, _ ->
                        viewModel.deleteGoal(intentWeekGoalId)
                        finish()
                    }
                    .setNegativeButton("취소하기") { _, _ ->

                    }
                    .create()
                    .show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}