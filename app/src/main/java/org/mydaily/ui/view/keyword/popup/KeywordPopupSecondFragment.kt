package org.mydaily.ui.view.keyword.popup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordPopupFirstBinding
import org.mydaily.databinding.FragmentKeywordPopupSecondBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordPopupSecondFragment : BaseFragment<FragmentKeywordPopupSecondBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_popup_second
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }


}