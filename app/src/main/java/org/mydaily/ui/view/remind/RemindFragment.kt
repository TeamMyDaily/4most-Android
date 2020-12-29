package org.mydaily.ui.view.remind

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel


class RemindFragment : BaseFragment<FragmentRemindBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind
    override val viewModel: RemindViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}