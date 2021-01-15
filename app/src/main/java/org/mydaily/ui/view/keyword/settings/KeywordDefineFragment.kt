package org.mydaily.ui.view.keyword.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.data.model.domain.KeywordDefine
import org.mydaily.databinding.FragmentKeywordDefineBinding
import org.mydaily.ui.adapter.KeywordDefineAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.MainActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack
import org.mydaily.util.extension.replaceAndAddBackStack
import org.mydaily.util.extension.shortToast

class KeywordDefineFragment : BaseFragment<FragmentKeywordDefineBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_define
    override val viewModel: KeywordViewModel by sharedViewModel()

    private val keywordDefineAdapter = KeywordDefineAdapter()
    private var argumentsKeyword = arrayListOf<String>()
    private var keywordsList = mutableListOf<KeywordDefine>()

    override fun initView() {
        getArgumentsData()
        initToolbar()
        initClickListener()
        initRecyclerView()
        initButtonVisible()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getTaskKeyword()
    }

    override fun initAfterBinding() {
        observeKeywordList()
    }


    private fun getArgumentsData() {
        argumentsKeyword = arguments?.getStringArrayList("keywords") as ArrayList<String>

        keywordsList.clear()
        for((i, arg) in argumentsKeyword.withIndex()){
            keywordsList.add(KeywordDefine(arg, viewModel.isDefineSet[i]))
        }
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
                    startMainActivity()
                }
                .setNegativeButton("취소"){ _, _ ->


                }
                .create()
                .show()
        }
        binding.btnSetPriority.setOnClickListener {
            startMainActivity()
        }
        binding.btnSetPriorityWithSkip.setOnClickListener {
            startMainActivity()
        }
    }

    private fun initRecyclerView() {
        keywordDefineAdapter.data = keywordsList

        binding.rvKeyword.apply {
            adapter = keywordDefineAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        keywordDefineAdapter.setKeywordNotExistListener {
            //정의 있을때
            requireContext().shortToast("정의를 이미 작성한 키워드에요!")
/*            Log.e("SEULGI", "pos :"+it)

            val bundle = Bundle().apply {
                putInt("totalKeywordId", viewModel.selectedKeywordIds[it])
            }
            val keywordDefineDetailFragment = KeywordDefineDetailFragment().apply {
                arguments = bundle
            }
            replaceAndAddBackStack(
                R.id.container_keyword_settings,
                keywordDefineDetailFragment,
                "detail"
            )*/
        }
        keywordDefineAdapter.setKeywordExistListener { keyword, position ->
            //정의 없을때
            val bundle = Bundle().apply {
                putString("keyword", keyword.name)
                putInt("position", position)
            }
            val keywordDefineAddFragment = KeywordDefineAddFragment().apply {
                arguments = bundle
            }
            replaceAndAddBackStack(
               R.id.container_keyword_settings,
                keywordDefineAddFragment,
                "add"
            )
        }

    }

    private fun initButtonVisible(){
        var isDefinedKeywordExist : Boolean = false
        for(isDefined in viewModel.isDefineSet){
            if(isDefined){
                isDefinedKeywordExist = true
                break
            }
        }
        binding.isDefinedKeywordExist = isDefinedKeywordExist
    }

    private fun observeKeywordList() {
/*        viewModel.taskKeywordList.observe(viewLifecycleOwner, {
            keywordDefineAdapter.data = it
        })*/
    }

    private fun startMainActivity() {
        requireActivity().apply {
            val intent =Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }


}