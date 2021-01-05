package org.mydaily.ui.view.goal

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentGoalBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.GoalViewModel

class GoalFragment : BaseFragment<FragmentGoalBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_goal
    override val viewModel: GoalViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}