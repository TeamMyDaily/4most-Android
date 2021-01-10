package org.mydaily.ui.view.keyword.settings

import android.app.AlertDialog
import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordDefineBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack

class KeywordDefineFragment : BaseFragment<FragmentKeywordDefineBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_define
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initClickListener()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initToolbar() {
        binding.tbKeywordDefine.setNavigationOnClickListener {
            popBackStack()
        }
    }

    private fun initClickListener() {
        binding.btnSkip.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("키워드정의를 건너뛰시겠어요?")
                .setMessage("MY>나의 현재 키워드> 키워드 정의에서 설정 할 수 있어요.")
                .setPositiveButton("확인"){ _, _ ->
                    requireActivity().apply {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                .setNegativeButton("취소"){ _, _ ->


                }
                .create()
                .show()
        }
    }
}