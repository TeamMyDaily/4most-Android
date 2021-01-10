package org.mydaily.ui.view.keyword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.databinding.ActivityKeywordSelectBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordSelectActivity : BaseActivity<ActivityKeywordSelectBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_select

    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
    }
    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordSelectActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        binding.tbKeywordSelectActivity.setNavigationOnClickListener {
            finish()
        }
    }
    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }
    //TODO -> 굳이 뷰 모델 사용안하고 리스트 별로 startactivityforresult로 리스트 넘겨줘서 해도 되지 않나



}