package org.mydaily.ui.view.remind.detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.text.Editable
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.databinding.ActivityRemindDetailWriteBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.RemindViewModel
import java.util.*

class RemindDetailWriteActivity : BaseActivity<ActivityRemindDetailWriteBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_remind_detail_write
    override val viewModel: RemindViewModel by viewModel()
    private var isContentEntered = false
    private var isWriten: Boolean = true

    override fun initView() {
        initViewSetting()
        initToolbar()
        initEditText()
        initWriteCompleteButton()
        initBtnSetting()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbRemindDetailWrite)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvTitle.text = getString(R.string.menu_remind)
        binding.tbRemindDetailWrite.setNavigationOnClickListener {
            if(isContentEntered) {
                val builder = AlertDialog.Builder(this)
                if(!isWriten) {
                    builder.setTitle(getString(R.string.remind_back_really))
                    builder.setMessage(getString(R.string.remind_back_alert))
                    builder.setPositiveButton(
                    getString(R.string.cancel)
                    ) { dialogInterface: DialogInterface?, i: Int -> }
                    builder.setNegativeButton(getString(R.string.remove)) { dialogInterface: DialogInterface?, i: Int ->
                        finish()
                    }
                }
                else {
                    builder.setTitle(getString(R.string.remind_back_really))
                    builder.setMessage(getString(R.string.remind_back_edit_alert))
                    builder.setPositiveButton(
                        getString(R.string.cancel)
                    ) { dialogInterface: DialogInterface?, i: Int -> }
                    builder.setNegativeButton(getString(R.string.back)) { dialogInterface: DialogInterface?, i: Int ->
                        finish()
                    }
                }
                builder.show()
            }
            else {
                finish()
            }
        }
    }

    private fun initEditText() {
        binding.tvRemindCount.text = binding.etRemindText.length().toString()
        binding.etRemindText.addTextChangedListener {
            val length = binding.etRemindText.length()
            binding.tvRemindCount.text = length.toString()
            if(!isWriten) {
                isContentEntered = length > 0
                binding.btWriteComplete.isEnabled = isSaveButtonEnabled()
            }
            else {
                isContentEntered = intent.getStringExtra("remind") != binding.etRemindText.text.toString()
                binding.btWriteComplete.isEnabled = isSaveButtonEnabled()
            }
            if(isContentEntered)
                binding.btWriteComplete.setBackgroundResource(R.drawable.btn_write_complete)
            else {
                binding.btWriteComplete.setBackgroundResource(R.drawable.btn_write_incomplete)
            }
        }
    }

    private fun isSaveButtonEnabled(): Boolean = isContentEntered

    private fun initWriteCompleteButton() {
        binding.btWriteComplete.setOnClickListener {
            viewModel.postReview(
                ReqReviewAdd(
                    intent.getLongExtra("start", 0),
                    intent.getLongExtra("end", 0),
                    Calendar.getInstance().timeInMillis,
                    intent.getIntExtra("subType", 0),
                    binding.etRemindText.text.toString()
                )
            )
            finish()
        }
    }

    private fun initViewSetting(){
        binding.tvRemindTitle.text = intent.getStringExtra("title")
        isWriten = intent.getBooleanExtra("isWriten", true)
        binding.etRemindText.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("remind"))
    }

    private fun initBtnSetting(){
        if(!isWriten)
            binding.btWriteComplete.text = getString(R.string.msg_add_complete)
        else {
            binding.btWriteComplete.text = getString(R.string.remind_restore)
        }
    }
}