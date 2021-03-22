package org.mydaily.ui.view.custom

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mydaily.R
import org.mydaily.databinding.LayoutModalBottomSheetMyPageBinding

class MyPageBottomSheetDialog(mCallBack : OnPriorityClick) : BottomSheetDialogFragment() {

    private lateinit var binding: LayoutModalBottomSheetMyPageBinding
    private val mOnPriorityClick = mCallBack // 콜백

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutModalBottomSheetMyPageBinding.inflate(inflater, container, false)

        binding.llKeywordReset.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setTitle(getString(R.string.important_value_reset))
                setMessage(getString(R.string.important_value_reset_detail))
                setPositiveButton(
                    getString(R.string.change)
                ) { dialogInterface: DialogInterface?, i: Int -> }
                setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface?, i: Int -> dismiss() }
            }.show()
            dismiss()
        }
        binding.llPrioritySet.setOnClickListener {
            mOnPriorityClick.onClick(false)
            dismiss()
        }
        binding.llCancle.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    interface OnPriorityClick {
        fun onClick(value : Boolean)
    }
}