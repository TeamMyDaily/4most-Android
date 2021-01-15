package org.mydaily.ui.view.splash.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentServiceExplainSecondBinding
import org.mydaily.databinding.FragmentServiceExplainThirdBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.UserViewModel


class ServiceExplainThirdFragment : BaseFragment<FragmentServiceExplainThirdBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_service_explain_third
    override val viewModel: UserViewModel by viewModel()
    override fun initView() {
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

}