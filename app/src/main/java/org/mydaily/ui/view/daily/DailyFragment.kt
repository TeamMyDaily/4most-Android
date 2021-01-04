package org.mydaily.ui.view.daily

import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.adapter.DailyKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.DailyViewModel


class DailyFragment : BaseFragment<FragmentDailyBinding, DailyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily
    override val viewModel: DailyViewModel by viewModel()

    override fun initView() {
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

}