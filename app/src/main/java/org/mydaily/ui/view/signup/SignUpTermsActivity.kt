package org.mydaily.ui.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignUpBinding
import org.mydaily.databinding.ActivitySignUpTermsBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.SignUpViewModel

class SignUpTermsActivity : BaseActivity<ActivitySignUpTermsBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up_terms
    override val viewModel: SignUpViewModel by viewModel()

    override fun initView() {
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }


    private fun initClickEvent() {
        binding.btnSignUp.isActivated = false
        binding.tbSignUpTerms.setNavigationOnClickListener {
            finish()
        }


    }
}