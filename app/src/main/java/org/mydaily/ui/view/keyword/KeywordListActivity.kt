package org.mydaily.ui.view.keyword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_keyword_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordListActivity : BaseActivity<ActivityKeywordListBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

}