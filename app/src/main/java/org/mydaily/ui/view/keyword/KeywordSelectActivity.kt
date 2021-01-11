package org.mydaily.ui.view.keyword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.databinding.ActivityKeywordSelectBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast

class KeywordSelectActivity : BaseActivity<ActivityKeywordSelectBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_select

    override val viewModel: KeywordViewModel by viewModel()



    override fun initView() {
        initToolbar()
        getkeywordlistIntent()
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

    private fun getkeywordlistIntent() {
        var mykeywordlist = arrayListOf<String>()
//        val intent = intent
//        mykeywordlist = intent.getStringArrayListExtra("keywordList") as ArrayList<String>
//        shortToast(mykeywordlist[0])

    }



}