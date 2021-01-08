package org.mydaily.ui.view.keyword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_keyword_add.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivityKeywordAddBinding
import org.mydaily.databinding.ActivityKeywordListBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.shortToast

class KeywordAddActivity : BaseActivity<ActivityKeywordAddBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_keyword_add
    override val viewModel: KeywordViewModel by viewModel()

    private var keywordList = mutableListOf<String>()

    override fun initView() {
        initToolbar()
        keywordInput()
        keywordAdd()
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
        et_keyword_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(et_keyword_input.text.toString().isEmpty()) {
                    btn_add.isEnabled = false
                    et_keyword_input.error = "비어있다!"
                } else if(et_keyword_input.length() > 5) {
                    btn_add.isEnabled = false
                    et_keyword_input.error = "5글자 초과!"
                } else if(et_keyword_input.text.toString().contains(" ")) {
                    btn_add.isEnabled = false
                    et_keyword_input.error = "공백이다!"
                } else if(keywordList.contains(et_keyword_input.text.toString())) {
                    btn_add.isEnabled = false
                    sameKeyword = et_keyword_input.text.toString()
                    et_keyword_input.error = "$sameKeyword 잡았다 요놈!!"
                } else {
                    btn_add.isEnabled = true
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun keywordAdd() {
        btn_add.setOnClickListener{
            //TODO 추가 로직 작성
            shortToast("키워드 추가 버튼 클릭됨")
            finish()
        }
    }

}