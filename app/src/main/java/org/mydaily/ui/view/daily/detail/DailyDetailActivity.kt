package org.mydaily.ui.view.daily.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun initView() {
        getIntentData()
        initToolbar()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun getIntentData() {
        val num = intent.getIntExtra("keyword",0)
        shortToast("$num 번째 키워드 클릭해서 들어옴")

        binding.tvKeyword.text = "열정"+num
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbDailyDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvTitle.text = "기록"
        binding.tbDailyDetail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initIntentData() {

    }

}