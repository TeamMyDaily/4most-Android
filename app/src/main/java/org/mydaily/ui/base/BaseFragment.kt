package org.mydaily.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment(){
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBeforeBinding()
        initAfterBinding()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,layoutResourceId, container, false)

        return dataBinding.root
    }
}