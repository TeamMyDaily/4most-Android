package org.mydaily.ui.view.keyword.guide

import android.app.AlertDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordGuideSelectDeepBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel

class KeywordGuideSelectDeepFragment :
    BaseFragment<FragmentKeywordGuideSelectDeepBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_guide_select_deep

    override val viewModel: KeywordViewModel by viewModel()

    private val setlifewordlist = arrayListOf<String>()
    private val setworkwordlist = arrayListOf<String>()
    private val setmywordlist = arrayListOf<String>()

    private var clickedChipCount: Int = 0
    private var finalSelectedKeywordList = mutableListOf<String>()
    private val keywords: List<String> = finalSelectedKeywordList

    override fun initView() {
        initToolbar()
        addChipLife()
        addChipWork()
    }

    private fun initToolbar() {

    }

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun floatingDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.up_to_four)
            .setMessage(R.string.think_more)
            .setPositiveButton(getString(R.string.okay), null)
            .create()
            .show()
    }

    private fun addKeywordList(text: String) {
        finalSelectedKeywordList.add(text)
    }

    private fun removeKeywordList(text: String) {
        finalSelectedKeywordList.remove(text)
    }

    private fun addChipLife() {
        for (str in setlifewordlist) {
            binding.cgLifeFour.addView(createChip(str))
        }
        setlifewordlist.clear()
    }

    private fun addChipWork() {
        for (str in setworkwordlist) {
            binding.cgWorkFour.addView(createChip(str))
        }
        setworkwordlist.clear()
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
                if (isChecked) {
                    clickedChipCount++
                    if (clickedChipCount >= 5) {
                        binding.btnSelectFourFinish.isEnabled = true
                        it.isChecked = false
                        clickedChipCount--
                        floatingDialog()
                    } else {
                        addKeywordList(it.text as String)
                        binding.btnSelectFourFinish.isEnabled = true
                    }
                } else {
                    clickedChipCount--
                    if (clickedChipCount < 1) {
                        binding.btnSelectFourFinish.isEnabled = false
                    }
                    removeKeywordList(it.text as String)
                }
            }
        }
    }

}