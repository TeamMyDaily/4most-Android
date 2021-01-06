package org.mydaily.ui.view.keyword

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast


class KeywordListActivity : BaseActivity<ActivityKeywordListBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initAddButton()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getLifeWord()
        viewModel.getWorkWord()
        viewModel.getMyWord()
    }

    override fun initAfterBinding() {
        observeLifeWordList()
        observeWorkWordList()
        observeMyWordList()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordAddActivity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initAddButton() {
        binding.ibAdd.setOnClickListener {
            shortToast("추가 버튼 클릭됨")
            //여기서 키워드 추가 Activity로 이동
        }
    }

    private fun observeLifeWordList() {
        viewModel.lifeWordList.observe(this, { list ->
            for(str in list){
                binding.cgLife.addView(createChip(str))
            }
        })
    }

    private fun observeWorkWordList() {
        viewModel.workWordList.observe(this, { list ->
            for(str in list){
                binding.cgWork.addView(createChip(str))
            }
        })
    }

    private fun observeMyWordList() {
        viewModel.myWordList.observe(this, { list ->
            for(str in list){
                binding.cgMyWord.addView(createChip(str))
            }
        })
    }

    private fun createChip(str: String): Chip {
        val chipDrawable = ChipDrawable.createFromAttributes(
            this,
            null,
            0,
            R.style.Widget_MaterialComponents_Chip_Choice
        )
        return Chip(this).apply {
            text = str
            setChipDrawable(chipDrawable)
            setChipBackgroundColorResource(R.color.selector_chip)
            setTextAppearance(R.style.MyDailyChipTextStyleAppearance)
            setOnClickListener {
                // Click Event 처리
                shortToast("CHIP 클릭됨")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_help -> {
                shortToast("도움말 버튼 클릭됨")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_keyword_list, menu)
        return true
    }

}