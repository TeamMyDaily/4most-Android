package org.mydaily.ui.view.mypage

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentRecordKeywordBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class RecordKeywordFragment : BaseFragment<FragmentRecordKeywordBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_record_keyword
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}