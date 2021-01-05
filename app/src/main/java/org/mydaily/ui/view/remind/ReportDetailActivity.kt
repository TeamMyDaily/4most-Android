package org.mydaily.ui.view.remind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityReportDetailBinding
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.databinding.FragmentRemindWriteBinding
import org.mydaily.databinding.FragmentReportBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel

class ReportDetailActivity: BaseFragment<ActivityReportDetailBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_report_detail
    override val viewModel: RemindViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}