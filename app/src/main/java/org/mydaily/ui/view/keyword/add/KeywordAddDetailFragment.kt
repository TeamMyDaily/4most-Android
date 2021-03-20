package org.mydaily.ui.view.keyword.add

import android.text.Editable
import android.text.TextWatcher
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordAddDetailBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.KoreanInWordCheckUtil
import org.mydaily.util.extension.popBackStack

class KeywordAddDetailFragment : BaseFragment<FragmentKeywordAddDetailBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_add_detail
    override val viewModel: KeywordViewModel by sharedViewModel()

    private val addedMyWord = arrayListOf<String>()

    override fun initView() {
        initSetting()
        keywordInput()
        keywordAdd()
    }

    override fun initBeforeBinding() { }

    override fun initAfterBinding() { }

    private fun keywordInput() {
        var sameKeyword: String
        binding.isNotVisible = true
        binding.etKeywordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etKeywordInput.text.toString().isEmpty()) {
                    binding.btnAdd.isEnabled = false
                    binding.isNotVisible = true
                } else if (binding.etKeywordInput.length() > 5) {
                    binding.btnAdd.isEnabled = false
                    binding.isNotVisible = false
                    binding.tvErrorText.setText(R.string.maximum_five_word)
                } else if (binding.etKeywordInput.text.toString().contains(" ")) {
                    binding.btnAdd.isEnabled = false
                    binding.isNotVisible = false
                    binding.tvErrorText.setText(R.string.there_is_space)
                } else if (addedMyWord.contains(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    sameKeyword =
                        "'" + binding.etKeywordInput.text.toString() + "' 은(는) " + getString(R.string.already_exist)
                    binding.isNotVisible = false
                    binding.tvErrorText.text = sameKeyword
                } else if (KoreanInWordCheckUtil.isKoreanInWord(binding.etKeywordInput.text.toString())) {
                    binding.btnAdd.isEnabled = false
                    binding.isNotVisible = false
                    binding.tvErrorText.text = "특수문자, 숫자, 영어를 제외하고 입력해주세요."
                } else {
                    binding.btnAdd.isEnabled = true
                    binding.isNotVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun keywordAdd() {
        binding.btnAdd.setOnClickListener {
            viewModel.addWordList.add(binding.etKeywordInput.text.toString())
            popBackStack()
        }
    }

    private fun initSetting() {
        addedMyWord.addAll(viewModel.addWordList)
    }
}