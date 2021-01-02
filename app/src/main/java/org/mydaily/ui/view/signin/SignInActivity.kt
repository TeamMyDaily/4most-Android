package org.mydaily.ui.view.signin

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignInBinding
import org.mydaily.ui.base.BaseActivity
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
            shortToast(this, "자동로그인 버튼 클릭" + it.isSelected)
        }
        binding.tvFindId.setOnClickListener {
            shortToast(this, "아이디찾기 버튼 클릭")
        }
        binding.tvFindPassword.setOnClickListener {
            shortToast(this, "비번찾기 버튼 클릭")
        }
        binding.tvSignUp.setOnClickListener {
            shortToast(this, "회원가입 버튼 클릭")
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etPassword.toString()
            val password = binding.etPassword.toString()

            if (LoginPatternCheckUtil.isNotValidEmailAndPassword(email, password)) {
                shortToast(this, R.string.msg_sign_in_error)
            }else {
                viewModel.signIn(email, password)
            }
        }
    }
}