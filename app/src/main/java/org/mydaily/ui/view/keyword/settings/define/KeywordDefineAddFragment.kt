package org.mydaily.ui.view.keyword.settings.define

import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.FragmentKeywordDefineAddBinding
import org.mydaily.ui.base.BaseFragment
import org.mydaily.ui.viewmodel.KeywordViewModel
import org.mydaily.util.extension.popBackStack


class KeywordDefineAddFragment : BaseFragment<FragmentKeywordDefineAddBinding, KeywordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_keyword_define_add
    override val viewModel: KeywordViewModel by sharedViewModel()

    private var keyword : String ?= null
    private var position : Int = 0

    override fun initView() {
        getArgumentData()
        initToolbar()
        initEditText()
        initButton()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initToolbar() {
        binding.tbKeywordDefineAdd.setNavigationOnClickListener {
            popBackStack()
        }
    }

    private fun getArgumentData() {
        keyword = requireArguments().getString("keyword")
        position = requireArguments().getInt("position")
    }

    private fun initEditText() {
        binding.tvKeyword.text = keyword

        binding.etDefine.addTextChangedListener {
            val length = binding.etDefine.length()
            binding.tvDefineByte.text = length.toString()
            binding.btnSetPriority.isEnabled = length > 0
        }
    }

    private fun initButton() {
        binding.btnSetPriority.isEnabled = false
        binding.btnSetPriority.setOnClickListener {
            viewModel.postKeywordDefinition(name = keyword!!, definition = binding.etDefine.text.toString())
            viewModel.isDefineSet[position] = true
            popBackStack()
        }
    }

}