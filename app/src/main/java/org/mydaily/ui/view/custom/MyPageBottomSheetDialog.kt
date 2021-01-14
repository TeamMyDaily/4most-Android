package org.mydaily.ui.view.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mydaily.databinding.LayoutModalBottomSheetMyPageBinding
import org.mydaily.util.extension.shortToast

class MyPageBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: LayoutModalBottomSheetMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutModalBottomSheetMyPageBinding.inflate(inflater, container, false)

        binding.llTaskKeywordAdd.setOnClickListener {
            requireContext().shortToast("서비스 준비중입니다")
        }
        binding.llKeywordDefine.setOnClickListener {
            requireContext().shortToast("서비스 준비중입니다")
        }
        binding.llKeywordDelete.setOnClickListener {
            requireContext().shortToast("서비스 준비중입니다")
        }
        binding.llCancle.setOnClickListener {
            requireContext().shortToast("서비스 준비중입니다")
        }
        return binding.root
    }
}