package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.ActivityMypageSettingBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.UserViewModel

class MyPageSettingActivity : BaseActivity<ActivityMypageSettingBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mypage_setting
    override val viewModel: UserViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initNameEmail()
    }

    private fun initToolbar() {
        binding.tbMypageSetting.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initNameEmail() {
        binding.tvUserName.text = FourMostPreference.getUserName() + getString(R.string.sir)
        binding.tvUserEmail.text = FourMostPreference.getUserEmail()
    }
}