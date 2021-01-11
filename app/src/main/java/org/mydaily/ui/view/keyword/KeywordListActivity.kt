package org.mydaily.ui.view.keyword

import android.app.AlertDialog
import android.content.Intent
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
    private var myWordChipCount: Int = 0
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
        onClickModifyButton()
        onClickCompleteButton()
        onClickSelectFinishButton()
    }

    private fun setSelectFinshButton() {
        if(clickedChipCount > 8) {
            binding.btnSelectFinish.isEnabled = false

            floatingDialog()
        } else if(clickedChipCount == 8) {
            binding.btnSelectFinish.isEnabled = true
        } else {
            binding.btnSelectFinish.isEnabled = false
        }
    }

    private fun onClickedCompleteButtonState() {
        //TODO -> 완료 버튼 눌렀을 때 상태 세팅
        //TODO -> for문 사용해서 viewmodel에 있는 myword 속성 싹 다 변경

    }

    private fun onClickModifyButtonState() {
        //TODO -> 수정 버튼 눌렀을 때 상태 세팅
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
            myWordChipCount++
            //val intent = Intent(this, KeywordAddActivity::class.java
            //startActivity(intent)
        }
        if (binding.cgMyWord.childCount > 0) {
            setModifyState()
        }
    }

    private fun observeLifeWordList() {
        viewModel.lifeWordList.observe(this, { list ->
            for (str in list) {
                binding.cgLife.addView(createChip(str))
            }
        })
    }

    private fun observeWorkWordList() {
        viewModel.workWordList.observe(this, { list ->
            for (str in list) {
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
                    clickedChipCount++
                    setSelectFinshButton()
                    addKeywordList(it.text as String)
                } else {
                    clickedChipCount--
                    setSelectFinshButton()
                    removeKeywordList(it.text as String)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_help -> {
//                val intent = Intent(this, KeywordPopupActivity::class.java)
//                startActivity(intent)
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



    private fun onClickSelectFinishButton() {
//        val intent = Intent(this, KeywordSelectActivity::class.java)
//        startActivity(intent)
//TODO -> selected list 보내줘야함

    }
}