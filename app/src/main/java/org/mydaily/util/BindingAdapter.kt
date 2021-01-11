package org.mydaily.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.mydaily.R
import org.mydaily.data.model.network.response.ResGoalGet
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
    @BindingAdapter("setTextColorWithGoalCompleted")
    fun setTextColorWithGoalCompleted(view: TextView, isGoalCompleted: Boolean) {
        if (isGoalCompleted) {
            view.setTextColor(view.resources.getColor(R.color.mainOrange, null))
        } else {
            view.setTextColor(view.resources.getColor(R.color.mainGray, null))
        }
    }

    @JvmStatic
    @BindingAdapter("setCheckBoxImageWithGoalCompleted")
    fun setCheckBoxImageWithGoalCompleted(view: ImageView, isGoalCompleted: Boolean) {
        if (isGoalCompleted) {
            view.setImageResource(R.drawable.ic_goal_on)
        } else {
            view.setImageResource(R.drawable.ic_goal_off)
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
    fun setGoalWithGoal(textView: TextView, goal: ResGoalGet.Data.Keyword) {
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

    @JvmStatic
    @BindingAdapter("setAchievementButtonVisibility")
    fun setAchievementButtonVisibility(textView: TextView, goal: ResGoalGet.Data.Keyword) {
        if(goal.isGoalCompleted && goal.isGoalCreated){
            textView.visibility = View.VISIBLE
        }
        else {
            textView.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("setNotAchievementButtonVisibility")
    fun setNotAchievementButtonVisibility(textView: TextView, goal: ResGoalGet.Data.Keyword) {
        if(!goal.isGoalCompleted && goal.isGoalCreated){
            textView.visibility = View.VISIBLE
        }
        else {
            textView.visibility = View.GONE
        }
    }
}