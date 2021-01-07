package org.mydaily.ui.view.remind

import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityRemindDetailWriteBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.RemindViewModel

class RemindDetailWriteActivity : BaseActivity<ActivityRemindDetailWriteBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_remind_detail_write
    override val viewModel: RemindViewModel by viewModel()
    private var isTitleEntered = false

    override fun initView() {
        initToolbar()
        initEditText()
        initWriteCompleteButton()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbRemindDetailWrite)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvTitle.text = "회고"
        binding.tbRemindDetailWrite.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initEditText() {
        binding.etRemindText.addTextChangedListener {
            val length = binding.etRemindText.length()
            isTitleEntered = length > 0
            binding.tvRemindCount.text = length.toString()
            binding.btWriteComplete.isEnabled = isSaveButtonEnabled()
        }
    }

    private fun isSaveButtonEnabled(): Boolean = isTitleEntered

    private fun initWriteCompleteButton() {
        /* TODO : 저장 버튼 */
        binding.btWriteComplete.setOnClickListener {

        }
    }
}