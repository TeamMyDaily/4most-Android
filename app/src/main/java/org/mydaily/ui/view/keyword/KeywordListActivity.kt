package org.mydaily.ui.view.keyword

import android.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast


class KeywordListActivity : BaseActivity<ActivityKeywordListBinding, KeywordViewModel>() {

    private var clickedChipCount: Int = 0
    private var selectedLifeWord = mutableListOf<String>()
    private var selectedWorkWord = mutableListOf<String>()
    private var selectedMyWord = mutableListOf<String>()

    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_list
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initAddButton()
        initModifyCompleteButton()
        initSelectFinishButton()
        setModifyState()
        setCompleteState()
        onClickModifyButton()
        onClickCompleteButton()
    }

    private fun onClickedCompleteButtonState() {
        //TODO -> for문 사용해서 viewmodel에 있는 myword 속성 싹 다 변경

    }

    private fun onClickModifyButtonState() {
        //TODO -> for문 사용해서 viewmodel에 있는 myword 속성 싹 다 변경
    }

    private fun addKeywordList(text: String) {
        if (viewModel.lifeWordList.value!!.contains(text)) {
            selectedLifeWord.add(text)
        } else if (viewModel.workWordList.value!!.contains(text)) {
            selectedWorkWord.add(text)
        } else if (viewModel.myWordList.value!!.contains(text)) {
            selectedMyWord.add(text)
        }
    }

    private fun removeKeywordList(text: String) {
        if (viewModel.lifeWordList.value!!.contains(text)) {
            selectedLifeWord.remove(text)
        } else if (viewModel.workWordList.equals(text)) {
            selectedWorkWord.remove(text)
        } else if (viewModel.myWordList.equals(text)) {
            selectedMyWord.remove(text)
        }
    }


    private fun floatingDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.up_to_eight)
            .setMessage(R.string.too_many_keyword_selected)
            .setPositiveButton("확인", null)
            .create()
            .show()
    }

    private fun setCompleteState() {
        binding.tvModify.visibility = View.VISIBLE
        binding.tvComplete.visibility = View.GONE
    }

    private fun setModifyState() {
        binding.tvModify.visibility = View.VISIBLE
        binding.tvComplete.visibility = View.GONE
    }

    private fun initModifyCompleteButton() {
        binding.tvModify.visibility = View.GONE
        binding.tvComplete.visibility = View.GONE
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
        binding.chipAdd.setOnClickListener {
            shortToast("추가 버튼 클릭됨")
            clickedChipCount++

            //예시 : 키워드 추가 시 맨 앞에 chip 생성 TODO -> 키워드 추가 뷰에서 작성한 텍스트를
            //TODO -> Viewmodel에 저장한 후 observe~에서 추가하기

        }
        if (binding.cgMyWord.childCount > 0) {
            setModifyState()
        }
    }


    private fun observeLifeWordList() {
        viewModel.lifeWordList.observe(this, { list ->
            for (str in list) {
                clickedChipCount++
                binding.cgLife.addView(createChip(str))
            }
        })
    }

    private fun observeWorkWordList() {
        viewModel.workWordList.observe(this, { list ->
            for (str in list) {
                clickedChipCount++
                binding.cgWork.addView(createChip(str))
            }
        })
    }

    private fun observeMyWordList() {
        viewModel.myWordList.observe(this, { list ->
            for (str in list) {
                binding.cgMyWord.addView(
                    createChip(str),
                    binding.cgMyWord.childCount - 1
                )
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
            setRippleColorResource(android.R.color.transparent)
            setOnClickListener {
                it as Chip
                if (isChecked) {
                    addKeywordList(it.text as String)
                    shortToast("${it.text} CHIP 클릭됨")
                } else {
                    removeKeywordList(it.text as String)
                    shortToast("$tag Chip 클릭해제")
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_help -> {
                shortToast("도움말 버튼 클릭됨")
                //TODO -> intent 넘겨주기
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_keyword_list, menu)
        return true
    }

    private fun onClickModifyButton() {
        binding.tvModify.setOnClickListener {
            setCompleteState()
            onClickModifyButtonState()
            binding.btnSelectFinish.visibility = View.GONE
        }
    }

    private fun onClickCompleteButton() {
        binding.tvComplete.setOnClickListener {
            setModifyState()
            onClickedCompleteButtonState()
            binding.btnSelectFinish.visibility = View.VISIBLE
        }
    }

    private fun initSelectFinishButton() {
        when {
            clickedChipCount > 8 -> {
                binding.btnSelectFinish.isClickable = false
                floatingDialog()
            }
            clickedChipCount == 8 -> {
                binding.btnSelectFinish.isClickable = true
                onClickSelectFinishButton()
            }
            else -> {
                binding.btnSelectFinish.isClickable = false
            }
        }
    }

    private fun onClickSelectFinishButton() {
        //TODO -> intent 넘겨주기기
    }

}