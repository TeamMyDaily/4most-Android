package org.mydaily.ui.view.keyword.guide

import android.app.AlertDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordGuideSelectBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel


class KeywordGuideSelectFragment : BaseFragment<FragmentKeywordGuideSelectBinding, KeywordViewModel>() {

    private var clickedChipCount: Int = 0
    private var addedMyWord = arrayListOf<String>()
    private var selectedLifeWord = arrayListOf<String>()
    private var selectedWorkWord = arrayListOf<String>()
    private var selectedMyWord = arrayListOf<String>()

    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_guide_select
    override val viewModel: KeywordViewModel by viewModel()

    override fun initView() {
        initToolbar()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getLifeWord()
        viewModel.getWorkWord()
    }

    override fun initAfterBinding() {
        observeLifeWordList()
        observeWorkWordList()
    }

    private fun initToolbar() {

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
        AlertDialog.Builder(context)
            .setTitle(R.string.up_to_eight)
            .setMessage(R.string.too_many_keyword_selected)
            .setPositiveButton("확인", null)
            .create()
            .show()
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

    private fun createChip(str: String): Chip {
        val chipDrawable = ChipDrawable.createFromAttributes(
            requireContext(),
            null,
            0,
            R.style.Widget_MaterialComponents_Chip_Choice
        )
        return Chip(requireContext()).apply {
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
}