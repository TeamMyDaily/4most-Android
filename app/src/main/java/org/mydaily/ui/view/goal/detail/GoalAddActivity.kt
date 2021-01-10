package org.mydaily.ui.view.goal.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityGoalAddBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.GoalViewModel

class GoalAddActivity : BaseActivity<ActivityGoalAddBinding, GoalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_goal_add
    override val viewModel: GoalViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

}