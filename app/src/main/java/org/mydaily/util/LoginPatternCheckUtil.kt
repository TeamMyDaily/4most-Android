package org.mydaily.util

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object LoginPatternCheckUtil {
    fun isNotValidName(name: String?): Boolean {
        return name.isNullOrEmpty()
    }

    private fun isNotValidEmail(email: String?): Boolean {
        return email.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isNotValidPassword(password: String?): Boolean {
        val passwordPattern: Pattern = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{6,24}")
        return password.isNullOrEmpty() || !passwordPattern.matcher(password).matches()
    }

    fun isNotValidEmailAndPassword(email: String?, password: String?): Boolean {
        return isNotValidEmail(email) || isNotValidPassword(password)
    }

    /**
     * use to check first password and second password are valid
     */
    fun isPasswordCheckSuccess(password: String?, password2: String?): Boolean {
        return !isNotValidPassword(password) && !isNotValidPassword(password2) && TextUtils.equals(password, password2)
    }
}