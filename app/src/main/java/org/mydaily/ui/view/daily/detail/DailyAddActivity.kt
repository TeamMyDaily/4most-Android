package org.mydaily.ui.view.daily.detail

import android.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.xw.repo.BubbleSeekBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityDailyAddBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.DailyViewModel

class DailyAddActivity : BaseActivity<ActivityDailyAddBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_daily_add
    override val viewModel: DailyViewModel by viewModel()

    private var isTitleEntered = false
    private var isDescriptionEntered = false

    private var intentKeywordId: Int = 0
    private var intentKeywordName: String = ""
    private var intentTasksId: Int = 0


    private var isInputChanged: Boolean = false

    private fun isSaveButtonEnabled(): Boolean = isTitleEntered && isDescriptionEntered && isInputChanged

    override fun initView() {
        getIntentData()
        initToolbar()

        initEditText()
        initViewAttribute()
        initBubbleSeekBar()

        initSaveButton()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getTaskById(intentTasksId)
    }

    override fun initAfterBinding() {
        observeTaskDetail()
    }

    private fun getIntentData() {
        intentKeywordId = intent.getIntExtra("keywordId", 0)
        intentKeywordName = intent.getStringExtra("keywordName") ?: "NULL"
        intentTasksId = intent.getIntExtra("taskId", 0)

        binding.tvKeyword.text = intentKeywordName
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbDailyDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbDailyDetail.setNavigationOnClickListener {//수정 시도했을때
            if (isInputChanged) {
                AlertDialog.Builder(this)
                    .setTitle("타이틀")
                    .setMessage("수정사항 저장?")
                    .setPositiveButton("확인") { _, _ ->
                        viewModel.postTask(
                            intentKeywordId.toString(), binding.etTitle.text.toString(),
                            binding.etDescription.text.toString(), binding.bubbleSeekBar.progress
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

    private fun initViewAttribute() {
        binding.btnSave.isEnabled = false
        binding.tvSatisfactionScore.text = binding.bubbleSeekBar.progress.toString()
    }

    private fun initEditText() {
        binding.etTitle.addTextChangedListener {
            val length = binding.etTitle.length()
            isTitleEntered = length > 0
            isInputChanged = true
            binding.tvTitleByte.text = length.toString()
            binding.btnSave.isEnabled = isSaveButtonEnabled()
        }
        binding.etDescription.addTextChangedListener {
            val length = binding.etDescription.length()
            isDescriptionEntered = length > 0
            isInputChanged = true
            binding.tvDescByte.text = length.toString()
            binding.btnSave.isEnabled = isSaveButtonEnabled()
        }
    }

    private fun initBubbleSeekBar() {
        binding.bubbleSeekBar.onProgressChangedListener = bubbleSeekBarListener
    }

    private fun initSaveButton() {
        if(intent.action == "MODIFY"){ //수정
            binding.btnSave.setOnClickListener {
                viewModel.putTask(
                    intentTasksId, binding.etTitle.text.toString(),
                    binding.etDescription.text.toString(), binding.bubbleSeekBar.progress
                )
                finish()
            }
        }
        else {  //저장
            binding.btnSave.setOnClickListener {
                viewModel.postTask(
                    intentKeywordId.toString(), binding.etTitle.text.toString(),
                    binding.etDescription.text.toString(), binding.bubbleSeekBar.progress
                )
                finish()
            }
        }
    }

    private fun observeTaskDetail() {
        if(intent.action == "MODIFY"){
            viewModel.task.observe(this, {
                binding.etTitle.setText(it.title)
                binding.etDescription.setText(it.detail)
                binding.bubbleSeekBar.setProgress(it.satisfaction.toFloat())
                binding.tvSatisfactionScore.text = it.satisfaction.toString()
                binding.tvTitleByte.text = binding.etTitle.text.length.toString()
                binding.tvDescByte.text = binding.etDescription.text.length.toString()
            })
        }
    }

    private val bubbleSeekBarListener = object : BubbleSeekBar.OnProgressChangedListener {
        override fun onProgressChanged(
            bubbleSeekBar: BubbleSeekBar?,
            progress: Int,
            progressFloat: Float,
            fromUser: Boolean
        ) {
            binding.tvSatisfactionScore.text = progress.toString()
        }

        override fun getProgressOnActionUp(
            bubbleSeekBar: BubbleSeekBar?,
            progress: Int,
            progressFloat: Float
        ) {

        }

        override fun getProgressOnFinally(
            bubbleSeekBar: BubbleSeekBar?,
            progress: Int,
            progressFloat: Float,
            fromUser: Boolean
        ) {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(intent.action=="MODIFY"){
            menuInflater.inflate(R.menu.menu_delete, menu)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                AlertDialog.Builder(this)
                    .setMessage("이 기록을 삭제하시겠어요?")
                    .setPositiveButton("삭제하기"){ _, _ ->
                        viewModel.deleteTask(intentTasksId)
                        finish()
                    }
                    .setNegativeButton("취소하기"){ _, _ ->

                    }
                    .create()
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}