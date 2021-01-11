package org.mydaily.ui.view.keyword.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.databinding.FragmentKeywordDefineBinding
import org.mydaily.ui.adapter.KeywordDefineAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack
import org.mydaily.util.extension.replaceAndAddBackStack

class KeywordDefineFragment : BaseFragment<FragmentKeywordDefineBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_define
    override val viewModel: KeywordViewModel by viewModel()

    private val keywordDefineAdapter = KeywordDefineAdapter()

    override fun initView() {
        initToolbar()
        initClickListener()
        initRecyclerView()
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

    private fun initRecyclerView() {
        binding.rvKeyword.apply {
            adapter = keywordDefineAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        keywordDefineAdapter.setClickListener {
            val bundle = Bundle().apply {
                putString("keyword", it)
            }
            val keywordDefineAddFragment = KeywordDefineAddFragment().apply {
                arguments = bundle
            }
            replaceAndAddBackStack(R.id.container_keyword_settings, keywordDefineAddFragment, "priority")
        }

        /** 임시데이터
        * 나중에 KeywordViewModel에서 data 가져오는걸로 수정해야함
        */
        keywordDefineAdapter.data = listOf(
            KeywordPriority("열정", 1),
            KeywordPriority("아웃풋", 2),
            KeywordPriority("영향력", 3),
            KeywordPriority("경청", 4),
        )
    }

}