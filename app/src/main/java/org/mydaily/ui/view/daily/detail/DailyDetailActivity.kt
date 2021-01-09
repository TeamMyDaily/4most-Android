package org.mydaily.ui.view.daily.detail

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.xw.repo.BubbleSeekBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityDailyDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.DailyViewModel
import org.mydaily.util.extension.shortToast

class DailyDetailActivity : BaseActivity<ActivityDailyDetailBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_daily_detail
    override val viewModel: DailyViewModel by viewModel()

    private var isTitleEntered = false
    private var isDescriptionEntered = false
    private var keywordId : Int = 0
    private var tasksId : Int = 0

    override fun initView() {
        getIntentData()
        initToolbar()

        initStateByAction()

        initSaveButton()
    }

    override fun initBeforeBinding() {
        viewModel.getTaskById(1)
    }

    override fun initAfterBinding() {

    }
    private fun getIntentData() {
        keywordId = intent.getIntExtra("keywordId", 0)
        tasksId = intent.getIntExtra("taskId", 0)

        binding.tvKeyword.text = "taskId : $taskId keywordId : $keywordId"
    }

    private fun initStateByAction() {
        when(intent.action){
            "WRITE"->stateWrite()
            "DETAIL"->stateDetail()
            else -> stateWrite()
        }
    }

    //추가, 수정
    private fun stateWrite() {
        binding.btnSave.visibility = View.VISIBLE
        binding.tvDescByte.visibility = View.VISIBLE
        binding.tvDescByteInfo.visibility = View.VISIBLE
        binding.tvTitleByte.visibility = View.VISIBLE
        binding.tvTitleByteInfo.visibility = View.VISIBLE

        binding.btnSave.isEnabled = false
        binding.tvSatisfactionScore.text = binding.bubbleSeekBar.progress.toString()

        binding.etTitle.isEnabled = true
        binding.etDescription.isEnabled = true
        binding.bubbleSeekBar.isEnabled = true

        initEditText()
        initBubbleSeekBar()
    }

    //보기만 할때
    private fun stateDetail() {
        binding.btnSave.visibility = View.GONE
        binding.tvDescByte.visibility = View.GONE
        binding.tvDescByteInfo.visibility = View.GONE
        binding.tvTitleByte.visibility = View.GONE
        binding.tvTitleByteInfo.visibility = View.GONE

        binding.etTitle.isEnabled = false
        binding.etDescription.isEnabled = false
        binding.bubbleSeekBar.isEnabled = false

        viewModel.task.observe(this, {
            binding.etTitle.setText(it.title)
            binding.etDescription.setText(it.detail)
            binding.bubbleSeekBar.setProgress(it.satisfaction.toFloat())
            binding.tvSatisfactionScore.text = it.satisfaction.toString()
        })
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbDailyDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvTitle.text = "기록"
        binding.tbDailyDetail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initEditText() {
        binding.etTitle.addTextChangedListener {
            val length = binding.etTitle.length()
            isTitleEntered = length > 0
            binding.tvTitleByte.text = length.toString()
            binding.btnSave.isEnabled = isSaveButtonEnabled()
        }
        binding.etDescription.addTextChangedListener {
            val length = binding.etDescription.length()
            isDescriptionEntered = length > 0
            binding.tvDescByte.text = length.toString()
            binding.btnSave.isEnabled = isSaveButtonEnabled()
        }
    }

    private fun initBubbleSeekBar() {
        binding.bubbleSeekBar.onProgressChangedListener = bubbleSeekBarListener
    }

    private fun initSaveButton() {
        /* TODO : 저장 버튼 */
        binding.btnSave.setOnClickListener {
            viewModel.postTask(keywordId.toString(), binding.etTitle.text.toString(),
                binding.etDescription.text.toString(), binding.bubbleSeekBar.progress)
            finish()
        }
    }

    private fun isSaveButtonEnabled(): Boolean = isTitleEntered && isDescriptionEntered

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


}