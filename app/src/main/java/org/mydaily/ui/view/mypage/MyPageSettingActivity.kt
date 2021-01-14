package org.mydaily.ui.view.mypage

import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.ActivityMypageSettingBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.view.user.SignInActivity
import org.mydaily.ui.viewmodel.UserViewModel

class MyPageSettingActivity : BaseActivity<ActivityMypageSettingBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mypage_setting
    override val viewModel: UserViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initNameEmail()
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initNameEmail() {
        binding.tvUserName.text = FourMostPreference.getUserName() + getString(R.string.sir)
        binding.tvUserEmail.text = FourMostPreference.getUserEmail()
    }

    private fun initToolbar() {
        binding.tbMypageSetting.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initClickEvent() {
        binding.tvLogout.setOnClickListener {
            FourMostPreference.apply {
                setUserToken("")
                setUserEmail("")
                setUserName("")
            }

            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}