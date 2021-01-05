package org.mydaily.ui.view.mypage

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentMyPageBinding
import org.mydaily.ui.adapter.MyPageKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.MyPageViewModel
import org.mydaily.util.extension.shortToast

class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page
    override val viewModel: MyPageViewModel by viewModel()

    private val myPageKeywordAdapter = MyPageKeywordAdapter()

    override fun initView() {
        setHasOptionsMenu(true)
        initRecyclerView()
        initClickListener()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getKeywordData()
    }

    override fun initAfterBinding() {
        observeKeywordData()
    }

    private fun initRecyclerView(){
        binding.rvMyKeyword.apply {
            adapter = myPageKeywordAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun initClickListener() {
        binding.tvKeywordModify.setOnClickListener {
            requireContext().shortToast("키워드 수정버튼 클릭됨")
        }
        myPageKeywordAdapter.setClickListener {
            requireContext().shortToast("$it 클릭됨")
        }
    }

    private fun observeKeywordData(){
        viewModel.keywordList.observe(viewLifecycleOwner, {
            myPageKeywordAdapter.data = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_my_page, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                requireContext().shortToast("설정 버튼 클릭됨")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}