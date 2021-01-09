package org.mydaily.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.mydaily.R
import org.mydaily.data.model.domain.Goal
import org.mydaily.util.CalendarUtil.isWeekSame
import java.util.*

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setDateByCalendar")
    fun setDateByCalendar(textView: TextView, calendar: Calendar) {
        val color = if (Calendar.getInstance().isWeekSame(calendar)) {
            textView.resources.getColor(R.color.mainOrange, null)
        } else {
            textView.resources.getColor(R.color.mainBlack, null)
        }
        textView.setTextColor(color)
        textView.text = CalendarUtil.convertCalendarToWeekString(calendar)
    }

    @JvmStatic
    @BindingAdapter("setKeywordWithGoal")
    fun setKeywordWithGoal(textView: TextView, goal: Goal) {
        val color = if (goal.isGoalCreated) {
            textView.resources.getColor(R.color.mainBlack, null)
        } else {
            textView.resources.getColor(R.color.mainGray, null)
        }
        textView.setTextColor(color)
        textView.text = goal.name
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
    @BindingAdapter("setGoalWithGoal")
    fun setGoalWithGoal(textView: TextView, goal: Goal) {
        val color: Int
        val text: String
        if (goal.isGoalCreated) {
            color = textView.resources.getColor(R.color.mainBlack, null)
            text = goal.weekGoal
        } else {
            color = textView.resources.getColor(R.color.mainGray, null)
            text = textView.resources.getString(R.string.msg_set_goal)
        }
        textView.setTextColor(color)
        textView.text = text
    }

}