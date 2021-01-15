package org.mydaily.ui.view.splash

import android.animation.Animator
import android.content.Intent
import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityServiceExplainBinding
import org.mydaily.databinding.ActivitySplashBinding
import org.mydaily.ui.adapter.KeywordPopupViewPagerAdapter
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.view.keyword.popup.KeywordPopupFirstFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupSecondFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupThirdFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash
    override val viewModel: KeywordViewModel by viewModel()
    private val SPLASH_VIEW_TIME: Long = 2200
    override fun initView() {
        initLottie()
    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initLottie() {
        binding.splash.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                //finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }
}