package org.mydaily.ui.view.daily.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityDailyDetailBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.DailyViewModel

class DailyDetailActivity : BaseActivity<ActivityDailyDetailBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_daily_detail
    override val viewModel: DailyViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}