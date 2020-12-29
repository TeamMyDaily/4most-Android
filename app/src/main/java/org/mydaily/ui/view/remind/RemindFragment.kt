package org.mydaily.ui.view.remind

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.ui.view.base.BaseFragment
import org.mydaily.ui.viewmodel.MainViewModel


class RemindFragment : BaseFragment<FragmentRemindBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind
    override val viewModel: MainViewModel
        get() = MainViewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}