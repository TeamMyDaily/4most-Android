package org.mydaily.data.local

import android.content.Context
import android.content.SharedPreferences

object FourMostPreference {
    private const val USER_TOKEN = "USER_TOKEN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_EMAIL = "USER_EMAIL"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val IS_FIRST_VISIT = "IS_FIRST_VISIT"
    private const val IS_KEYWORD_EXIST = "IS_KEYWORD_EXIST"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserToken(): String {
        return preferences.getString(USER_TOKEN, "") ?: ""
    }

    fun setUserToken(value: String) {
        preferences.edit().putString(USER_TOKEN, value).apply()
    }

    fun getUserName(): String {
        return preferences.getString(USER_NAME, "") ?: ""
    }

    fun setUserName(value: String) {
        preferences.edit().putString(USER_NAME, value).apply()
    }

    fun getUserEmail(): String {
        return preferences.getString(USER_EMAIL, "") ?: ""
    }

    fun setUserEmail(value: String) {
        preferences.edit().putString(USER_EMAIL, value).apply()
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, true)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }


    fun getFirstVisit(): Boolean {
        return preferences.getBoolean(IS_FIRST_VISIT, true)
    }

    fun setFirstVisit(value: Boolean) {
        preferences.edit().putBoolean(IS_FIRST_VISIT, value).apply()
    }

    fun getKeywordExist(): Boolean {
        return preferences.getBoolean(IS_KEYWORD_EXIST, false)
    }

    fun setKeywordExist(value: Boolean) {
        preferences.edit().putBoolean(IS_KEYWORD_EXIST, value).apply()
    }
}