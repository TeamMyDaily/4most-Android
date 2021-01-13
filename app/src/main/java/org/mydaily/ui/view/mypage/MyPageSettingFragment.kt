package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentMyPageSettingBinding
import org.mydaily.databinding.KeywordListItemBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.UserViewModel
import org.mydaily.util.extension.popBackStack

class MyPageSettingFragment : BaseFragment<FragmentMyPageSettingBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_setting
    override val viewModel: UserViewModel by viewModel()

    override fun initView() {
        initToolbar()
    }

    private fun initToolbar() {
        binding.tbMypageSetting.setNavigationOnClickListener {
            popBackStack() //TODO -> 맞나?
        }
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}