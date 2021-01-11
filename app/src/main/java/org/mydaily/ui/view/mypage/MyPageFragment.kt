package org.mydaily.ui.view.mypage

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentMyPageBinding
import org.mydaily.ui.adapter.MyPageViewPagerAdapter
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.MyPageViewModel
import org.mydaily.util.extension.shortToast

class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page
    override val viewModel: MyPageViewModel by viewModel()


    override fun initView() {
        setHasOptionsMenu(true)
        initRecyclerView()
        initClickListener()
        createViewPager()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getKeywordData()
        viewModel.getUser()
    }

    override fun initAfterBinding() {
        observeKeywordData()
        observeUserName()
    }

    private fun initRecyclerView() {
        /*binding.rvMyKeyword.apply {
            adapter = myPageKeywordAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }*/
    }

    private fun initClickListener() {
        /*binding.tvKeywordModify.setOnClickListener {
            requireContext().shortToast("키워드 수정버튼 클릭됨")
        }
        myPageKeywordAdapter.setClickListener {
            requireContext().shortToast("$it 클릭됨")
        }*/
    }

    private fun observeKeywordData() {
        //viewModel.keywordList.observe(viewLifecycleOwner, {
            //myPageKeywordAdapter.data = it
        //})
    }

    private fun observeUserName() {
        viewModel.userName.observe(viewLifecycleOwner, {
            binding.tvUser.text = it + getString(R.string.of_user)
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

    fun createViewPager() {
        var tab_label = listOf("기록키워드", "키워드 목록")
        var fragmentList = listOf(RecordKeywordFragment(), KeywordListFragment())
        val myPageKeywordAdapter = MyPageViewPagerAdapter(this)
        myPageKeywordAdapter.fragmentList = fragmentList

        binding.vpMypage.adapter = myPageKeywordAdapter

        TabLayoutMediator(binding.tbMypage, binding.vpMypage){tab, position->
            tab.text = tab_label[position]
        }.attach()

        binding.vpMypage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}