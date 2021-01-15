package org.mydaily.ui.view.splash.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentServiceExplainFirstBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.UserViewModel

class ServiceExplainFirstFragment : BaseFragment<FragmentServiceExplainFirstBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_service_explain_first
    override val viewModel: UserViewModel by viewModel()
    override fun initView() {
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }


}