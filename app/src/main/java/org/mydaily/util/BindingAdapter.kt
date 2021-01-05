package org.mydaily.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setKeywordGoalButtonVisibility")
    fun setKeywordGoalButtonVisibility(imageView: ImageView, isGoalExist: Boolean) {
        if(isGoalExist){
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("setKeywordGoalTextVisibility")
    fun setKeywordGoalTextVisibility(textView: TextView, isGoalExist: Boolean) {
        if(isGoalExist){
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }
}