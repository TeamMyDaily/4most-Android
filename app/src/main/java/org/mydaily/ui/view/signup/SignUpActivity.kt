package org.mydaily.ui.view.signup

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignUpBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()

    override fun initView() {
        
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}