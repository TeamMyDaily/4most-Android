package org.mydaily.ui.view.goal.detail

import android.app.AlertDialog
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

    private var intentAction: String? = null
    private var intentKeyword: String? = null
    private var intentGoal: String? = null
    private var intentId: Int? = null

    override fun initView() {
        getIntentData()
        initViewByAction()
        initToolbar()
        binding.tvDate.text = CalendarUtil.convertCalendarToWeekString(Calendar.getInstance())
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this

    }

    override fun initAfterBinding() {

    }

    private fun getIntentData() {
        intentAction = intent.action
        intentKeyword = intent.getStringExtra("keyword")
        intentGoal = intent.getStringExtra("goal")
        intentId = intent.getIntExtra("id", 1)

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
            // 추가 후 종료
            finish()
        }
    }

    /* 목표 수정 */
    private fun stateModify() {
        binding.apply {
            tvKeyword.text = intentKeyword
            tvInfo1.text = "에 대한"
            tvInfo2.text = "목표를 수정하시겠어요?"
            etGoal.setText(intentGoal)
            tvByte.text = etGoal.text.length.toString()

            btnModifySave.isEnabled = false
            btnDelete.isEnabled = true

            btnAddSave.visibility = View.GONE
            btnModifySave.visibility = View.VISIBLE
            btnDelete.visibility = View.VISIBLE
        }


        binding.etGoal.addTextChangedListener {
            val length = binding.etGoal.length()
            binding.tvByte.text = length.toString()
            binding.btnModifySave.isEnabled = length > 0
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("이 목표를 삭제하시겠어요?")
                .setMessage("목표를 삭제 하면 다시 되돌릴 수 없어요.\n" +
                        "그래도 삭제 하시겠어요??")
                .setPositiveButton("삭제하기") { _, _ ->
                    // 삭제
                    // do something
                    shortToast("삭제 클릭됨")
                }
                .setNegativeButton("다음에하기") { _, _ ->
                    //취소
                    shortToast("다음에하기 클릭됨")
                }
                .create()
                .show()
        }

        binding.btnModifySave.setOnClickListener {
            //다시 저장 후 종료
            finish()
        }
    }
}