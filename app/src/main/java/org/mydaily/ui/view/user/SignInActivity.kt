package org.mydaily.ui.view.user

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.ActivitySignInBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.view.keyword.KeywordListActivity
import org.mydaily.ui.viewmodel.UserViewModel
import org.mydaily.util.EventObserver
import org.mydaily.util.LoginPatternCheckUtil
import org.mydaily.util.extension.setupToast
import org.mydaily.util.extension.shortToast

class SignInActivity : BaseActivity<ActivitySignInBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_sign_in
    override val viewModel: UserViewModel by viewModel()
    private var passwordIsVisible = true
    private var isValidPassword = false
    private var isValidEmail = false

    override fun initView() {
        initClickEvent()
        visibleButtonChange()
        etColorChange()
        emailAndPasswordValid()
        setupToast(this, viewModel.toastMessage)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeSignInResult()
    }

    override fun onStart() {
        super.onStart()
        if(FourMostPreference.getAutoLogin() && FourMostPreference.getUserToken() != ""){
            startMainActivity()
        }
    }

    private fun initClickEvent() {
        binding.ibAutoLogin.isSelected = true
        binding.ibAutoLogin.setOnClickListener {
            it.isSelected = !it.isSelected
            FourMostPreference.setAutoLogin(it.isSelected)
        }
        binding.tvFindId.setOnClickListener {
            shortToast(R.string.msg_service_is_being_prepared)
        }
        binding.tvFindPassword.setOnClickListener {
            shortToast(R.string.msg_service_is_being_prepared)
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpTermsActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (isValidEmail && isValidPassword) {
                viewModel.postSignIn(email, password)
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
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_visible)
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etPassword.letterSpacing = 0.0F
                passwordIsVisible = false
            }
            else {
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_invisible)
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

    private fun observeSignInResult() {
        viewModel.signInEvent.observe(this, EventObserver{
            if(it == "keywordsExist"){
                startMainActivity()
            }else {
                startKeywordListActivity()
            }
        })
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startKeywordListActivity() {
        startActivity(Intent(this, KeywordListActivity::class.java))
        finish()
    }
}