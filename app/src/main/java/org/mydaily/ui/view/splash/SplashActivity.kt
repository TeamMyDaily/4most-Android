package org.mydaily.ui.view.splash

import android.animation.Animator
import android.content.Intent
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.local.FourMostPreference
import org.mydaily.databinding.ActivitySplashBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.splash.service.ServiceExplainActivity
import org.mydaily.ui.view.user.SignInActivity
import org.mydaily.ui.viewmodel.KeywordViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initLottie()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initLottie() {
        binding.splash.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if (FourMostPreference.getFirstVisit()) {
                    startActivity(Intent(this@SplashActivity, ServiceExplainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
                }
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }
}