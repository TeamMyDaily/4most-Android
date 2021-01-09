package org.mydaily.ui.view.signup

import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignUpBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()
    var passWordVisible = false
    var passWordConfirmVisible = false

    override fun initView() {
        etColorChange()
        visibleButtonChange()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
    
    private fun etColorChange() {
        binding.etName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.clName.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.tvName.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etName.text.isEmpty()) {
                    binding.clName.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvName.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clEmail.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.tvEmail.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etEmail.text.isEmpty()) {
                    binding.clEmail.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvEmail.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clPassword.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.ivVisibleButton.visibility = View.VISIBLE
                binding.tvPassword.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etPassword.text.isEmpty()) {
                    binding.clPassword.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvPassword.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                    binding.ivVisibleButton.visibility = View.INVISIBLE
                }
            }
        }

        binding.etPasswordConfirm.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clPasswordConfirm.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.ivVisibleButtonConfirm.visibility = View.VISIBLE
                binding.tvPasswordConfirm.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etPasswordConfirm.text.isEmpty()) {
                    binding.clPasswordConfirm.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvPasswordConfirm.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                    binding.ivVisibleButtonConfirm.visibility = View.INVISIBLE
                }
            }
        }

    }

    private fun etAlertChange() {
        binding.etName.addTextChangedListener {

        }
        binding.etEmail.addTextChangedListener {

        }
        binding.etPassword.addTextChangedListener {

        }
        binding.etPasswordConfirm.addTextChangedListener {

        }
    }

    private fun visibleButtonChange() {
        binding.ivVisibleButton.setOnClickListener {
            binding.ivVisibleButton.isSelected = !binding.ivVisibleButton.isSelected
            if(binding.ivVisibleButton.isSelected) {
                //binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_visible_button)
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT
                passWordVisible = true
            }
            else {
                //binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_invisible_button)
                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                passWordVisible = false
            }
        }

        binding.ivVisibleButtonConfirm.setOnClickListener {
            binding.ivVisibleButtonConfirm.isSelected = !binding.ivVisibleButtonConfirm.isSelected
            if(binding.ivVisibleButtonConfirm.isSelected) {
                //binding.ivVisibleButtonConfirm.setBackgroundResource(R.drawable.ic_visible_button)
                binding.etPasswordConfirm.inputType = InputType.TYPE_CLASS_TEXT
                passWordConfirmVisible = true
            }
            else {
                //binding.ivVisibleButtonConfirm.setBackgroundResource(R.drawable.ic_invisible_button)
                binding.etPasswordConfirm.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                passWordConfirmVisible = false
            }
        }
    }

}