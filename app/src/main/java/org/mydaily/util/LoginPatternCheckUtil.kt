package org.mydaily.util

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object LoginPatternCheckUtil {
    fun isNotValidName(name: String?): Boolean {
        val namePattern: Pattern = Pattern.compile("[ㄱ-ㅎ가-힣ㅏ-ㅣa-zA-Z0-9\\!\\@\\#\\$]{0,24}")
        return name.isNullOrEmpty() || !namePattern.matcher(name).matches()
    }

    fun isNotValidEmail(email: String?): Boolean {
        return email.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isNotValidPassword(password: String?): Boolean {
        val passwordPattern: Pattern = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{6,24}")
        return password.isNullOrEmpty() || !passwordPattern.matcher(password).matches()
    }

    /**
     * use to check first password and second password are valid
     */
    fun isPasswordCheckSuccess(password: String?, password2: String?): Boolean {
        return TextUtils.equals(password, password2)
    }
}