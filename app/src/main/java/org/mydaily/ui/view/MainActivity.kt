package org.mydaily.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityMainBinding
import org.mydaily.util.extension.replace
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.task.TaskFragment
import org.mydaily.ui.view.goal.GoalFragment
import org.mydaily.ui.view.mypage.MyPageFragment
import org.mydaily.ui.view.remind.RemindFragment
import org.mydaily.ui.viewmodel.UserViewModel

class MainActivity : BaseActivity<ActivityMainBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: UserViewModel by viewModel()

    private val dailyFragment: TaskFragment by lazy { TaskFragment() }
    private val remindFragment: RemindFragment by lazy { RemindFragment() }
    private val myFragment: MyPageFragment by lazy { MyPageFragment() }
    private val goalFragment: GoalFragment by lazy { GoalFragment() }

    override fun initView() {
        initToolbar()
        initBottomNavigation()

        replaceDailyFragment()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_daily -> {
                    replaceDailyFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_remind -> {
                    replaceRemindFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_my -> {
                    replaceMyPageFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_goal -> {
                    replaceGoalFragment()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun replaceDailyFragment() {
        replace(R.id.container_main, dailyFragment)
        binding.tvTitle.visibility = View.GONE
        binding.ivLogo.visibility = View.VISIBLE
        binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun replaceRemindFragment() {
        replace(R.id.container_main, remindFragment)
        binding.tvTitle.text =getString(R.string.menu_remind)
        binding.tvTitle.visibility = View.VISIBLE
        binding.ivLogo.visibility = View.GONE
        binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun replaceMyPageFragment() {
        replace(R.id.container_main, myFragment)
        binding.tvTitle.text = getString(R.string.menu_my)
        binding.tvTitle.visibility = View.VISIBLE
        binding.ivLogo.visibility = View.GONE
        binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.mainOrange)))
    }

    private fun replaceGoalFragment() {
        replace(R.id.container_main, goalFragment)
        binding.tvTitle.text = getString(R.string.menu_goal)
        binding.ivLogo.visibility = View.GONE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvTitle.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}