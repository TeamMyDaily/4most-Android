package org.mydaily.data.local

import android.content.Context
import android.content.SharedPreferences

object FourMostPreference {
    private const val USER_TOKEN = "USER_TOKEN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_EMAIL = "USER_EMAIL"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserToken(): String {
        return preferences.getString(USER_TOKEN, "4most") ?: ""
    }

    fun setUserToken(value: String) {
        preferences.edit().putString(USER_TOKEN, value).apply()
    }

    fun getUserName(): String {
        return preferences.getString(USER_NAME, "4most") ?: ""
    }

    fun setUserName(value: String) {
        preferences.edit().putString(USER_NAME, value).apply()
    }

    fun getUserEmail(): String {
        return preferences.getString(USER_EMAIL, "4most@gmail.com") ?: ""
    }

    fun setUserEmail(value: String) {
        preferences.edit().putString(USER_EMAIL, value).apply()
    }
}