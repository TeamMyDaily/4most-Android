package org.mydaily.ui.view

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityMainBinding
import org.mydaily.extension.replace
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.daily.DailyFragment
import org.mydaily.ui.view.mypage.MyPageFragment
import org.mydaily.ui.view.remind.RemindFragment
import org.mydaily.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    private val dailyFragment: DailyFragment by lazy { DailyFragment() }
    private val remindFragment: RemindFragment by lazy { RemindFragment() }
    private val myFragment: MyPageFragment by lazy { MyPageFragment() }

    override fun initView() {
        initBottomNavigation()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initBottomNavigation() {
        setSupportActionBar(dataBinding.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        dataBinding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_daily -> {
                    replace(R.id.container_main, dailyFragment)
                    dataBinding.tvToolbarTitle.text = getString(R.string.menu_daily)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_remind -> {
                    replace(R.id.container_main, remindFragment)
                    dataBinding.tvToolbarTitle.text = getString(R.string.menu_remind)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_my -> {
                    replace(R.id.container_main, myFragment)
                    dataBinding.tvToolbarTitle.text = getString(R.string.menu_my)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}