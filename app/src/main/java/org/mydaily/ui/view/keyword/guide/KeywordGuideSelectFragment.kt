package org.mydaily.ui.view.keyword.guide

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordGuideSelectBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.createKeywordChip
import org.mydaily.util.extension.popBackStack
import org.mydaily.util.extension.replaceAndAddBackStack


class KeywordGuideSelectFragment :
    BaseFragment<FragmentKeywordGuideSelectBinding, KeywordViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_guide_select
    override val viewModel: KeywordViewModel by sharedViewModel()

    override fun initView() {
        initClickEvent()
        initChipGroup()
    }

    override fun initBeforeBinding() { }

    override fun initAfterBinding() { }

    private fun initClickEvent() {
        binding.btnSelectFinish.setOnClickListener {
            viewModel.viewPagerPosition.value = KeywordViewModel.KEYWORD_GUIDE_DEEP_POSITION
        }
    }

    private fun initChipGroup() {
        val listener : (it: Chip) -> (Unit) = {
            val clickedChipCount = viewModel.selectedLifeWordList.value!!.size + viewModel.selectedWorkWordList.value!!.size
            if (it.isChecked) {
                when (clickedChipCount) {
                    8 -> {
                        it.isChecked = false
                        showAlertDialog()
                    }
                    7 -> {
                        addKeywordList(it.text as String)
                        binding.btnSelectFinish.isEnabled = true
                    }
                    else -> {
                        addKeywordList(it.text as String)
                    }
                }
            } else {
                removeKeywordList(it.text as String)
                if (clickedChipCount < 8) {
                    binding.btnSelectFinish.isEnabled = false
                }
            }
        }

        for (life in KeywordViewModel.lifeWordList) {
            binding.cgLife.addView(createKeywordChip(life, listener) )
        }

        for (life in KeywordViewModel.workWordList) {
            binding.cgWork.addView(createKeywordChip(life, listener) )
        }
    }

    private fun addKeywordList(text: String) {
        if (KeywordViewModel.lifeWordList.contains(text)) {
            viewModel.selectedLifeWordList.value!!.add(text)
        } else if (KeywordViewModel.workWordList.contains(text)) {
            viewModel.selectedWorkWordList.value!!.add(text)
        }
    }

    private fun removeKeywordList(text: String) {
        if (KeywordViewModel.lifeWordList.contains(text)) {
            viewModel.selectedLifeWordList.value!!.remove(text)
        } else if (KeywordViewModel.workWordList.contains(text)) {
            viewModel.selectedWorkWordList.value!!.remove(text)
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.up_to_eight)
            .setMessage(R.string.too_many_keyword_selected)
            .setPositiveButton("확인", null)
            .create()
            .show()
    }
}