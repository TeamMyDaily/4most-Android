package org.mydaily.ui.view.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import org.mydaily.databinding.LayoutGoalAchieveDialogBinding

class GoalAchieveDialog(context: Context) : Dialog(context) {

    private lateinit var binding: LayoutGoalAchieveDialogBinding

    private var listener: (() -> Unit)? = null
    fun setNegativeButtonClickListener(listener: (() -> Unit)): GoalAchieveDialog {
        this.listener = listener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParams = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.8f
        }

        window?.let {
            it.attributes = layoutParams
            it.setBackgroundDrawableResource(android.R.color.transparent);
        }

        binding= LayoutGoalAchieveDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        binding.ivCancel.setOnClickListener {
            listener?.invoke()
            dismiss()
        }
    }

}