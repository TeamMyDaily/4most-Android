package org.mydaily.ui.view.keyword

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_keyword_add.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordAddBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast
import java.util.regex.Pattern


class KeywordAddActivity : BaseActivity<ActivityKeywordAddBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_add
    override val viewModel: KeywordViewModel by viewModel()

    private var keywordList = mutableListOf<String>()

    override fun initView() {
        initToolbar()
        initEditText()
        keywordInput()
        keywordAdd()
    }

    private fun initEditText() {
        val filterKorea = InputFilter { source, start, end, dest, dstart, dend ->
            val ps = Pattern.compile(getString(R.string.korean_keyboard))
            if (!ps.matcher(source).matches()) {
                ""
            } else null
        }
        binding.etKeywordInput.filters = arrayOf(filterKorea)
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        viewModel.getLifeWord()
        viewModel.getWorkWord()
        viewModel.getMyWord()
        //viewModel.addMyWord(String())
    }

    override fun initAfterBinding() {
        observeLifeWordList()
        observeWorkWordList()
        observeMyWordList()
        //observeAddMyWord(String())
    }

    private fun observeLifeWordList() {
        viewModel.lifeWordList.observe(this, {
            keywordList.addAll(it)
        })
    }
    private fun observeWorkWordList() {
        viewModel.workWordList.observe(this, {
            keywordList.addAll(it)
        })
    }
    private fun observeMyWordList() {
        viewModel.myWordList.observe(this, {
            keywordList.addAll(it)
        })
    }

    private fun observeAddMyWord(keyword : String) {
//        viewModel.addMyWord.observe(this, {
//            keywordAdd().addMyWord(keyword)
//            keywordList.addAll(it)
//        })
    }
    private fun initToolbar() {
        setSupportActionBar(binding.tbKeywordAddActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        binding.tbKeywordAddActivity.setNavigationOnClickListener {
            finish()
        }

    }
    private fun keywordInput() {
        var sameKeyword : String
        binding.etKeywordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etKeywordInput.text.toString().isEmpty()) {
                    binding.btnAdd.isEnabled = false
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
                } else if (keywordList.contains(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    sameKeyword =
                        "'" + binding.etKeywordInput.text.toString() + "' 은(는) " + getString(R.string.already_exist)
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.text = sameKeyword
                } else if(binding.etKeywordInput.text.toString().contains("a")) {
                    //TODO -> 알파벳이랑 특수문자 입력됐을 때 공백 들어갔을 때처럼 로직 작성하기 -> 아예 입력만 못하게 하면 안되나
                    binding.btnAdd.isEnabled = false
                    binding.tvErrortext.visibility = View.VISIBLE
                    binding.btnErrorIcon.visibility = View.VISIBLE
                    binding.tvErrortext.setText("영어존재")

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
        binding.btnAdd.setOnClickListener{
            observeAddMyWord(et_keyword_input.text.toString())
            shortToast("키워드 추가 버튼 클릭됨")
            finish()
        }
    }

}