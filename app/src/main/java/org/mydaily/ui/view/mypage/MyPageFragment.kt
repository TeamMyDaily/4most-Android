package org.mydaily.ui.view.mypage

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.databinding.FragmentMyPageBinding
import org.mydaily.ui.adapter.KeywordPriorityAdapter
import org.mydaily.ui.adapter.MyPageCurrentKeywordAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.custom.MyPageBottomSheetDialog
import org.mydaily.ui.viewmodel.KeywordViewModel

class MyPageFragment : BaseFragment<FragmentMyPageBinding, KeywordViewModel>(), MyPageBottomSheetDialog.OnPriorityClick {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page
    override val viewModel: KeywordViewModel by viewModel()

    private val myPageCurrentKeywordAdapter = MyPageCurrentKeywordAdapter()
    private val myPageKeywordPriorityAdapter = KeywordPriorityAdapter()
    private val dividerItemDecoration: DividerItemDecoration by lazy{
        DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.divider_recyclerview)!!)
        }
    }

    override fun initView() {
        setHasOptionsMenu(true)
        initRecyclerView()
        initClickEvent()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initAfterBinding() {
        observeTaskKeyword()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTaskKeyword()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_my_page, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                requireContext().apply {
                    startActivity(Intent(this, MyPageSettingActivity::class.java))
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        binding.isCurrentKeywordListVisible = true

        binding.layoutMyPageCurrentKeywordList.rvCurrentKeywordList.apply {
            adapter = myPageCurrentKeywordAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }

        binding.layoutMyPageCurrentKeywordPriority.rvCurrentKeywordListPriority.apply {
            adapter = myPageKeywordPriorityAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun initClickEvent() {
        binding.ibModify.setOnClickListener {
            val myPageBottomSheetDialog = MyPageBottomSheetDialog()
            myPageBottomSheetDialog.callbackSetter(this)
            myPageBottomSheetDialog.show(childFragmentManager, "keyword")
        }

        binding.layoutMyPageCurrentKeywordPriority.tvAppointPriority.setOnClickListener {
            binding.ibModify.isEnabled = true
            val temp = mutableListOf<ReqKeywordPriority.Keyword>()
            var i=1
            for(data in myPageKeywordPriorityAdapter.data){
                temp.add(ReqKeywordPriority.Keyword(data, i++))
            }
            viewModel.postKeywordPriority(temp)
            viewModel.getTaskKeyword()
            binding.isCurrentKeywordListVisible = true
        }
    }

    override fun onClick(value: Boolean) {
        binding.isCurrentKeywordListVisible = value
        binding.ibModify.isEnabled = false
    }

    private fun observeTaskKeyword() {
        viewModel.taskKeywordList.observe(viewLifecycleOwner, {
            myPageCurrentKeywordAdapter.data = it
            binding.isKeywordEmpty = it.isEmpty()
        })
        viewModel.keywordStringList.observe(viewLifecycleOwner, {
            myPageKeywordPriorityAdapter.data = it
        })
    }

}