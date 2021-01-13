package org.mydaily.ui.view.daily.detail

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityDailyDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.TaskViewModel

class TaskDetailActivity : BaseActivity<ActivityDailyDetailBinding, TaskViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_daily_detail
    override val viewModel: TaskViewModel by viewModel()

    private var intentKeywordName : String = ""
    private var intentTaskId : Int = 0

    override fun initView() {
        getIntentData()
        initToolbar()
        initViewEnable()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getTaskById(intentTaskId)
    }

    override fun initAfterBinding() {
        observeTaskDetail()
    }
    private fun getIntentData() {
        intentKeywordName = intent.getStringExtra("keywordName") ?: "NULL"
        intentTaskId = intent.getIntExtra("taskId", 0)

        binding.tvKeyword.text = intentKeywordName
    }

    //보기만 할때
    private fun initViewEnable() {
        binding.etTitle.isEnabled = false
        binding.etDescription.isEnabled = false
        binding.bubbleSeekBar.isEnabled = false
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbDailyDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbDailyDetail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun observeTaskDetail() {
        viewModel.task.observe(this, {
            binding.etTitle.setText(it.title)
            binding.etDescription.setText(it.detail)
            binding.bubbleSeekBar.setProgress(it.satisfaction.toFloat())
            binding.tvSatisfactionScore.text = it.satisfaction.toString()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_modify, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_modify -> {
                val intent = Intent(this, TaskAddActivity::class.java)
                intent.apply {
                    action = "MODIFY"
                    putExtra("taskId",intentTaskId)
                    putExtra("keywordName",intentKeywordName)
                }
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}