package org.mydaily.ui.view.keyword

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.view.keyword.popup.KeywordPopupActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast


class KeywordListActivity : BaseActivity<ActivityKeywordListBinding, KeywordViewModel>() {

    private var clickedChipCount: Int = 0
    private var myWordChipCount: Int = 0
    private var addedMyWord = arrayListOf<String>()
    private var selectedLifeWord = arrayListOf<String>()
    private var selectedWorkWord = arrayListOf<String>()
    private var selectedMyWord = arrayListOf<String>()

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

    private fun onClickedCompleteButtonState() {
        //TODO -> 완료 버튼 눌렀을 때 상태 세팅
    }

    private fun onClickModifyButtonState() {
        //TODO -> 수정 버튼 눌렀을 때 상태 세팅
    }

    private fun addKeywordList(text: String) {
        if (viewModel.lifeWordList.value!!.contains(text)) {
            selectedLifeWord.add(text)
        } else if (viewModel.workWordList.value!!.contains(text)) {
            selectedWorkWord.add(text)
        } else if (addedMyWord.contains(text)) {
            selectedMyWord.add(text)
        }
    }

    private fun removeKeywordList(text: String) {
        if (viewModel.lifeWordList.value!!.contains(text)) {
            selectedLifeWord.remove(text)
        } else if (viewModel.workWordList.value!!.contains(text)) {
            selectedWorkWord.remove(text)
        } else if (addedMyWord.contains(text)) {
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
        binding.tvModify.visibility = View.GONE
        binding.tvComplete.visibility = View.VISIBLE
    }

    private fun setModifyState() {
        if (myWordChipCount > 0) {
            binding.tvModify.visibility = View.VISIBLE
            binding.tvComplete.visibility = View.GONE
        }
    }

    private fun initModifyCompleteButton() {
        binding.tvModify.visibility = View.GONE
        binding.tvComplete.visibility = View.GONE
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getLifeWord()
        viewModel.getWorkWord()
    }

    override fun initAfterBinding() {
        observeLifeWordList()
        observeWorkWordList()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordListActivity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.white)))
        binding.tbKeywordListActivity.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initAddButton() {
        binding.chipAdd.setOnClickListener {
            myWordChipCount++
            val intent = Intent(this, KeywordAddActivity::class.java)
            intent.putStringArrayListExtra("MyWordList", addedMyWord)
            startActivityForResult(intent, 1004)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1004) {
            if (resultCode == 1005) {
                if (data?.getStringExtra("MyWord") != null) {
                    addedMyWord.add(data.getStringExtra("MyWord")!!)
                }
            } else {
                shortToast("잘못된 resultCode1")
            }
        } else {
            shortToast("잘못된 requestCode2")
        }
        addMyWordList()
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

    private fun addMyWordList() {
        binding.cgMyWord.addView(
            createChip(addedMyWord.last().toString()),
            binding.cgMyWord.childCount - 1
        )
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
                if (isChecked) { //주황색일 때
                    clickedChipCount++
                    if (clickedChipCount >= 9) {
                        binding.btnSelectFinish.isEnabled = true
                        it.isChecked = false
                        clickedChipCount--
                        floatingDialog()
                    } else if (clickedChipCount == 8) {
                        addKeywordList(it.text as String)
                        binding.btnSelectFinish.isEnabled = true
                    } else {
                        addKeywordList(it.text as String)
                        binding.btnSelectFinish.isEnabled = false
                    }
                } else {
                    clickedChipCount--
                    if (clickedChipCount < 8) {
                        binding.btnSelectFinish.isEnabled = false
                    }
                    removeKeywordList(it.text as String)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_help -> {
                val intent = Intent(this, KeywordPopupActivity::class.java)
                startActivity(intent)
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
        binding.btnSelectFinish.setOnClickListener {
            val intent = Intent(this, KeywordSelectActivity::class.java)
            intent.putStringArrayListExtra("selectedlifeword", selectedLifeWord)
            intent.putStringArrayListExtra("selectedworkword", selectedWorkWord)
            intent.putStringArrayListExtra("selectedmyword", selectedMyWord)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        setModifyState()

    }
}