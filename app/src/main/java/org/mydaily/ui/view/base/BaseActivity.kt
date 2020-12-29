package org.mydaily.ui.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(){
    lateinit var dataBinding: T

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    /**
     * initiate view and click event
     */
    abstract fun initView()

    /**
     * initiate viewmodel, lifecyclerowner and something else..
     */
    abstract fun initBeforeBinding()

    /**
     * initiate others (ex. observe Livedata)
     */
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initView()
        initBeforeBinding()
        initAfterBinding()
    }
}