package org.mydaily.util

import org.mydaily.R
import java.util.regex.Pattern

object KoreanInWordCheckUtil {
    fun isKoreanInWord(keyword: String): Boolean {
        val keywordPattern: Pattern = Pattern.compile(R.string.korean_keyboard.toString())
        return !keywordPattern.matcher(keyword).matches()
    }
}