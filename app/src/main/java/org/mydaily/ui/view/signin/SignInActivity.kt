package org.mydaily.ui.view.signin

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignInBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.view.signup.SignUpTermsActivity
import org.mydaily.ui.viewmodel.SignInViewModel
import org.mydaily.util.LoginPatternCheckUtil
import org.mydaily.util.extension.shortToast

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_sign_in
    override val viewModel: SignInViewModel by viewModel()
    private var passwordIsVisible = true
    private var isValidPassword = false
    private var isValidEmail = false

    override fun initView() {
        initClickEvent()
        visibleButtonChange()
        etColorChange()
        emailAndPasswordValid()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initClickEvent() {
        binding.ibAutoLogin.isSelected = true
        binding.ibAutoLogin.setOnClickListener {
            it.isSelected = !it.isSelected
            //shortToast("자동로그인 버튼 클릭" + it.isSelected)
        }
        binding.tvFindId.setOnClickListener {
            //shortToast("아이디찾기 버튼 클릭")
        }
        binding.tvFindPassword.setOnClickListener {
            //shortToast("비번찾기 버튼 클릭")
        }
        binding.tvSignUp.setOnClickListener {
            binding.tvSignUp.setOnClickListener {
                startActivity(Intent(this, SignUpTermsActivity::class.java))
            }
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.toString()
            val password = binding.etPassword.toString()
            if (isValidEmail && isValidPassword) {
                viewModel.signIn(email, password)
                startActivity(Intent(this, MainActivity::class.java))
            }else {
                shortToast(R.string.msg_sign_in_error)
            }
        }
    }

    private fun emailAndPasswordValid() {
        binding.etEmail.addTextChangedListener {
            isValidEmail = !LoginPatternCheckUtil.isNotValidEmail(binding.etEmail.text.toString())
            binding.btnSignIn.isEnabled = binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()
        }

        binding.etPassword.addTextChangedListener {
            isValidPassword = !LoginPatternCheckUtil.isNotValidPassword(binding.etPassword.text.toString())
            binding.btnSignIn.isEnabled = binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()
        }
    }

    private fun visibleButtonChange() {
        binding.ivVisibleButton.setOnClickListener {
            if(passwordIsVisible) {
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_visible_button)
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etPassword.letterSpacing = 0.0F
                passwordIsVisible = false
            }
            else {
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_invisible_button)
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etPassword.letterSpacing = 0.2F
                passwordIsVisible = true
            }
        }
    }

    private fun etColorChange() {
        binding.etEmail.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.etEmail.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
            }
            else {
                if(binding.etEmail.text.isEmpty()) {
                    binding.etEmail.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clPassword.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.ivVisibleButton.visibility = View.VISIBLE
            }
            else {
                if(binding.etPassword.text.isEmpty()) {
                    binding.clPassword.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.ivVisibleButton.visibility = View.INVISIBLE
                }
            }
        }
    }
}