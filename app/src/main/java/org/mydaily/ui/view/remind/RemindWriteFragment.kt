package org.mydaily.ui.view.remind

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.databinding.FragmentRemindWriteBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel

class RemindWriteFragment: BaseFragment<FragmentRemindWriteBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind_write
    override val viewModel: RemindViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}