package org.mydaily.ui.view.remind

import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_remind.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRemindBinding
import org.mydaily.ui.adapter.RemindViewPagerAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.RemindViewModel


class RemindFragment : BaseFragment<FragmentRemindBinding, RemindViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_remind
    override val viewModel: RemindViewModel by viewModel()

    override fun initView() {
        createViewpager()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    fun createViewpager() {
        var tab_label = listOf("리포트", "회고")
        var fragmentList = listOf(ReportFragment(), RemindFragment())
        val remindAdapter = RemindViewPagerAdapter(this)
        remindAdapter.fragmentList = fragmentList

        vp_remind.adapter = remindAdapter

        TabLayoutMediator(tb_remind, vp_remind){tab, position->
            tab.text = tab_label[position]
        }.attach()
    }

}