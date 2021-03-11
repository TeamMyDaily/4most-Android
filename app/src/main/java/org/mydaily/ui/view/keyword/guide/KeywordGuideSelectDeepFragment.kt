package org.mydaily.ui.view.keyword.guide

import android.app.AlertDialog
import android.content.Intent
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordGuideSelectDeepBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.view.keyword.popup.KeywordPopupActivity
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.createKeywordChip
import org.mydaily.util.extension.popBackStack

class KeywordGuideSelectDeepFragment :
    BaseFragment<FragmentKeywordGuideSelectDeepBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_guide_select_deep

    override val viewModel: KeywordViewModel by sharedViewModel()

    override fun initView() {
        initToolbar()
        initChipGroup()
    }

    override fun initBeforeBinding() {}

    override fun initAfterBinding() {}

    private fun initToolbar() {
        binding.tbKeywordGuideDeep.setNavigationOnClickListener { popBackStack() }
        binding.tbKeywordGuideDeep.setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_help) {
                startActivity(Intent(requireActivity(), KeywordPopupActivity()::class.java))
            }
            true
        }
    }

    private fun initChipGroup() {
        val listener: (it: Chip) -> (Unit) = {
            if (it.isChecked) {
                if (viewModel.selectedWordList.size >= 5) {
                    binding.btnSelectFourFinish.isEnabled = true
                    it.isChecked = false
                    showAlertDialog()
                } else {
                    viewModel.selectedWordList.add(it.text as String)
                    binding.btnSelectFourFinish.isEnabled = true
                }
            } else {
                viewModel.selectedWordList.remove(it.text as String)
                if (viewModel.selectedWordList.size < 1) {
                    binding.btnSelectFourFinish.isEnabled = false
                }
            }
        }

        for (life in viewModel.selectedLifeWordList) {
            binding.cgLifeFour.addView(createKeywordChip(life, listener))
        }

        for (life in viewModel.selectedWorkWordList) {
            binding.cgWorkFour.addView(createKeywordChip(life, listener))
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.up_to_four)
            .setMessage(R.string.think_more)
            .setPositiveButton(getString(R.string.okay), null)
            .create()
            .show()
    }

}