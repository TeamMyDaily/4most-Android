package org.mydaily.ui.view.keyword.guide

import android.app.AlertDialog
import android.content.Intent
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
        initToolbar()
        initClickEvent()
        initChipGroup()
    }

    override fun initBeforeBinding() { }

    override fun initAfterBinding() { }

    private fun initToolbar() {
        binding.tbKeywordGuideSelect.setNavigationOnClickListener { popBackStack() }
        binding.tbKeywordGuideSelect.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_help) {
                startActivity(Intent(requireActivity(), KeywordPopupActivity()::class.java))
            }
            true
        }
    }

    private fun initClickEvent() {
        binding.btnSelectFinish.setOnClickListener {
            replaceAndAddBackStack(
                R.id.container_keyword,
                KeywordGuideSelectDeepFragment(),
                "Guide2"
            )
        }
    }

    private fun initChipGroup() {
        val listener : (it: Chip) -> (Unit) = {
            val clickedChipCount = viewModel.selectedLifeWordList.size + viewModel.selectedWorkWordList.size
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

        for (life in viewModel.lifeWordList) {
            binding.cgLife.addView(createKeywordChip(life, listener) )
        }

        for (life in viewModel.workWordList) {
            binding.cgWork.addView(createKeywordChip(life, listener) )
        }
    }

    private fun addKeywordList(text: String) {
        if (viewModel.lifeWordList.contains(text)) {
            viewModel.selectedLifeWordList.add(text)
        } else if (viewModel.workWordList.contains(text)) {
            viewModel.selectedWorkWordList.add(text)
        }
    }

    private fun removeKeywordList(text: String) {
        if (viewModel.lifeWordList.contains(text)) {
            viewModel.selectedLifeWordList.remove(text)
        } else if (viewModel.workWordList.contains(text)) {
            viewModel.selectedWorkWordList.remove(text)
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