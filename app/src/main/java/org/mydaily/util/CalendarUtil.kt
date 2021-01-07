package org.mydaily.util

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtil {
    private val simpleDateFormat = SimpleDateFormat("yy년 MM월 dd일", Locale.KOREA)
    private val simpleDateFormatWithWeek = SimpleDateFormat("yy년 MM월 W주", Locale.KOREA)

    fun convertCalendarToString(calendar: Calendar): String = simpleDateFormat.format(calendar.time)

    fun convertCalendarToWeekString(calendar: Calendar): String  = simpleDateFormatWithWeek.format(calendar.time)

    fun convertCalendarToString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return simpleDateFormat.format(calendar.time)
    }

    fun Calendar.compareDateTo(c1: Calendar): Boolean {
        return get(Calendar.YEAR) == c1.get(Calendar.YEAR)
                && get(Calendar.MONTH) == c1.get(Calendar.MONTH)
                && get(Calendar.DAY_OF_MONTH) == c1.get(Calendar.DAY_OF_MONTH)
    }

    fun Calendar.isWeekSame(c1: Calendar): Boolean {
        return get(Calendar.YEAR) == c1.get(Calendar.YEAR)
                && get(Calendar.MONTH) == c1.get(Calendar.MONTH)
                && get(Calendar.WEEK_OF_MONTH) == c1.get(Calendar.WEEK_OF_MONTH)
    }
}