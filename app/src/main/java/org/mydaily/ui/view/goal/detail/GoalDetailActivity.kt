package org.mydaily.ui.view.goal.detail

import android.view.View
import androidx.core.widget.addTextChangedListener
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityGoalDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.GoalViewModel
import org.mydaily.util.extension.shortToast

class GoalDetailActivity : BaseActivity<ActivityGoalDetailBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_goal_detail
    override val viewModel: GoalViewModel by viewModel()

    private var intentAction: String? = null
    private var intentKeyword: String? = null
    private var intentGoal: String? = null

    override fun initView() {
        getIntentData()
        initViewByAction()
        initToolbar()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun getIntentData() {
        intentAction = intent.action
        intentKeyword = intent.getStringExtra("keyword")
        intentGoal = intent.getStringExtra("goal")

        shortToast("action = $intentAction, keyword = $intentKeyword, goal = $intentGoal")
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbGoalDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbGoalDetail.setNavigationOnClickListener {
            finish()
        }
        binding.tvTitle.text = when (intentAction) {
            "ADD" -> "목표 설정"
            "MODIFY" -> "목표 수정"
            else -> ""
        }
    }

    private fun initViewByAction() {
        when (intentAction) {
            "ADD" -> stateAdd()
            "MODIFY" -> stateModify()
        }
    }

    /* 목표 설정 */
    private fun stateAdd() {
        binding.apply {
            tvKeyword.text = intentKeyword
            tvInfo1.text = "에"
            tvInfo2.text = "가까워지기 위한 목표"
            btnAddSave.isEnabled = false

            btnAddSave.visibility = View.VISIBLE
            btnModifySave.visibility = View.GONE
            btnDelete.visibility = View.GONE
        }

        binding.etGoal.addTextChangedListener {
            val length = binding.etGoal.length()
            binding.tvByte.text = length.toString()
            binding.btnAddSave.isEnabled = length > 0
        }

        binding.btnAddSave.setOnClickListener {
            //저장 후 종료
            finish()
        }
    }

    /* 목표 수정 */
    private fun stateModify() {
        binding.apply {
            tvKeyword.text = intentKeyword
            tvInfo1.text = "에"
            tvInfo2.text = "목표를 수정하시겠어요?"
            etGoal.setText(intentGoal)

            btnModifySave.isEnabled = false
            btnDelete.isEnabled = false

            btnAddSave.visibility = View.GONE
            btnModifySave.visibility = View.VISIBLE
            btnDelete.visibility = View.VISIBLE
        }

        binding.btnDelete.setOnClickListener {
            //삭제
        }

        binding.btnModifySave.setOnClickListener {
            //저장 후 종료
            finish()
        }
    }
}