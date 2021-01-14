package org.mydaily.ui.view.keyword

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordAddBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import java.util.regex.Pattern


class KeywordAddActivity : BaseActivity<ActivityKeywordAddBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_add
    override val viewModel: KeywordViewModel by viewModel()

    private var keywordListForDuplicated = mutableListOf<String>()
    private val addedMyWord = arrayListOf<String>()

    override fun initView() {
        initToolbar() //TODO -> 뒤로가는 툴바 버튼 눌렀을 때 오류!
        keywordInput()
        keywordAdd()
        getMyWordList()
    }

    private fun getMyWordList() {
        addedMyWord.addAll(intent.getStringArrayListExtra("MyWordList") as ArrayList<String>)
    }

    private fun isKoreanInWord(keyword: String): Boolean {
        val keywordPattern: Pattern = Pattern.compile(getString(R.string.korean_keyboard))
        return !keywordPattern.matcher(keyword).matches()
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

    private fun observeLifeWordList() {
        viewModel.lifeWordList.observe(this, {
            keywordListForDuplicated.addAll(it)
        })
    }

    private fun observeWorkWordList() {
        viewModel.workWordList.observe(this, {
            keywordListForDuplicated.addAll(it)
        })
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordAddActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbKeywordAddActivity.setNavigationOnClickListener {
            finish()
        }
    }

    private fun keywordInput() {
        var sameKeyword: String
        binding.etKeywordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etKeywordInput.text.toString().isEmpty()) {
                    binding.btnAdd.isEnabled = false
                    binding.tvErrortext.visibility = View.INVISIBLE
                    binding.btnErrorIcon.visibility = View.INVISIBLE
                } else if (binding.etKeywordInput.length() > 5) {
                    binding.btnAdd.isEnabled = false
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.setText(R.string.maximum_five_word)
                } else if (binding.etKeywordInput.text.toString().contains(" ")) {
                    binding.btnAdd.isEnabled = false
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.setText(R.string.there_is_space)
                } else if (keywordListForDuplicated.contains(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    sameKeyword =
                        "'" + binding.etKeywordInput.text.toString() + "' 은(는) " + getString(R.string.already_exist)
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.text = sameKeyword
                } else if (addedMyWord.contains(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    sameKeyword =
                        "'" + binding.etKeywordInput.text.toString() + "' 은(는) " + getString(R.string.already_exist)
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.text = sameKeyword
                } else if (isKoreanInWord(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.text = "한글 이외의 단어는 안된다 임마!"
                } else {
                    binding.btnAdd.isEnabled = true
                    binding.tvErrortext.visibility = View.GONE
                    binding.btnErrorIcon.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun keywordAdd() {
        binding.btnAdd.setOnClickListener {
            addedMyWord.clear()
            val temp = Intent()
            temp.putExtra("MyWord", binding.etKeywordInput.text.toString())
            setResult(1005, temp)
            finish()
        }
    }

}