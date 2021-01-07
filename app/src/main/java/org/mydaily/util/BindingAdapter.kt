package org.mydaily.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.mydaily.R
import org.mydaily.data.model.Goal

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setKeywordWithGoal")
    fun setKeywordWithGoal(textView: TextView, goal: Goal) {
        val color = if (goal.isGoalExist) {
            textView.resources.getColor(R.color.mainBlack, null)
        } else {
            textView.resources.getColor(R.color.gray, null)
        }
        textView.setTextColor(color)
        textView.text = goal.keyword
    }

    @JvmStatic
    @BindingAdapter("setVisibilityWithGoalCompleted")
    fun setVisibilityWithGoalCompleted(view: View, isGoalCompleted: Boolean) {
        if (isGoalCompleted) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


    @JvmStatic
    @BindingAdapter("setBackgroundWithGoalCompleted")
    fun setBackgroundWithGoalCompleted(view: View, isGoalCompleted: Boolean) {
        if (isGoalCompleted) {
            view.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
        } else {
            view.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
        }
    }

    @JvmStatic
    @BindingAdapter("setVisibilityWithGoalExist")
    fun setVisibilityWithGoalExist(view: View, isGoalExist: Boolean) {
        if (isGoalExist) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }


    @JvmStatic
    @BindingAdapter("setGoalWithGoal")
    fun setGoalWithGoal(textView: TextView, goal: Goal) {
        val color: Int
        val text: String
        if (goal.isGoalExist) {
            color = textView.resources.getColor(R.color.mainBlack, null)
            text = goal.goal!!
        } else {
            color = textView.resources.getColor(R.color.gray, null)
            text = textView.resources.getString(R.string.msg_set_goal)
        }
        textView.setTextColor(color)
        textView.text = text
    }

}