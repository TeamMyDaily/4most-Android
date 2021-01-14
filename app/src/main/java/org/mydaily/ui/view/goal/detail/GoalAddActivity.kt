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
            if (isGoalChanged) {
                AlertDialog.Builder(this)
                    .setTitle("정말 뒤로가시겠어요?")
                    .setMessage("뒤로가기를 누르시면 수정사항이 삭제되고 이전 페이지로 돌아갑니다.")
                    .setPositiveButton("뒤로가기") { _, _ ->
/*                        viewModel.postGoals(
                            System.currentTimeMillis(),
                            intentTotalKeywordId.toString(),
                            binding.etGoal.text.toString()
                        )*/
                        finish()
                    }
                    .setNegativeButton("취소하기") { _, _ ->

                    }
                    .create()
                    .show()

            }
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
            viewModel.putGoals(
                intentWeekGoalId,
                binding.etGoal.text.toString()
            )
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (intentAction == "MODIFY") {
            menuInflater.inflate(R.menu.menu_delete, menu)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                AlertDialog.Builder(this)
                    .setTitle("목표를 삭제하시겠어요?")
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