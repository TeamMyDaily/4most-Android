package org.mydaily.ui.view.daily

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.mydaily.R
import org.mydaily.databinding.FragmentDailyBinding
import org.mydaily.ui.view.base.BaseFragment
import org.mydaily.ui.viewmodel.MainViewModel


class DailyFragment : BaseFragment<FragmentDailyBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily
    override val viewModel: MainViewModel
        get() = MainViewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}