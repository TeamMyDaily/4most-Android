package org.mydaily.ui.view.signup

import android.content.Intent
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
        initStartView()
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initStartView() {
        binding.btnSignUp.isEnabled = false
    }

    private fun initClickEvent() {
        binding.tbSignUpTerms.setNavigationOnClickListener {
            finish()
        }
        binding.ibAllowPersonalInformation.setOnClickListener {
            it.isSelected = !it.isSelected
            checkTermsSelected()
        }
        binding.ibAllowServiceTerms.setOnClickListener {
            it.isSelected = !it.isSelected
            checkTermsSelected()
        }
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun checkTermsSelected() {
        binding.btnSignUp.isEnabled = binding.ibAllowPersonalInformation.isSelected && binding.ibAllowServiceTerms.isSelected
    }
}