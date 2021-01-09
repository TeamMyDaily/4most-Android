package org.mydaily.ui.view.signin

import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignInBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.view.signup.SignUpActivity
import org.mydaily.ui.view.signup.SignUpTermsActivity
import org.mydaily.ui.viewmodel.SignInViewModel
import org.mydaily.util.LoginPatternCheckUtil
import org.mydaily.util.extension.shortToast

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_sign_in
    override val viewModel: SignInViewModel by viewModel()

    override fun initView() {
        initClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initClickEvent() {
        binding.ibAutoLogin.isSelected = true
        binding.ibAutoLogin.setOnClickListener {
            it.isSelected = !it.isSelected
            shortToast("자동로그인 버튼 클릭" + it.isSelected)
        }
        binding.tvFindId.setOnClickListener {
            shortToast("아이디찾기 버튼 클릭")
        }
        binding.tvFindPassword.setOnClickListener {
            shortToast("비번찾기 버튼 클릭")
        }
        binding.tvSignUp.setOnClickListener {
            binding.tvSignUp.setOnClickListener {
                startActivity(Intent(this, SignUpTermsActivity::class.java))
            }
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.toString()
            val password = binding.etPassword.toString()

            if (LoginPatternCheckUtil.isNotValidEmailAndPassword(email, password)) {
                shortToast(R.string.msg_sign_in_error)
                startActivity(Intent(this, MainActivity::class.java))
            }else {
                viewModel.signIn(email, password)
            }
        }
    }
}