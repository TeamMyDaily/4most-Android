package org.mydaily.data.local

import android.content.Context
import android.content.SharedPreferences

object FourMostPreference {
    private const val USER_TOKEN = "USER_TOKEN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_EMAIL = "USER_EMAIL"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("4most", Context.MODE_PRIVATE)
    }

    var userToken: String
        get() = preferences.getString(USER_TOKEN, "4most") ?: ""
        set(value) = preferences.edit().putString(USER_TOKEN, value).apply()

    var userName: String
        get() = preferences.getString(USER_NAME, "4most") ?: ""
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    var userEmail: String
        get() = preferences.getString(USER_EMAIL, "4most@gmail.com") ?: ""
        set(value) = preferences.edit().putString(USER_EMAIL, value).apply()
}