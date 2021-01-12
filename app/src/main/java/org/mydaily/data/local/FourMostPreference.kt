package org.mydaily.data.local

import android.content.Context
import android.content.SharedPreferences

object FourMostPreference {
    private const val USER_TOKEN = "USER_TOKEN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_EMAIL = "USER_EMAIL"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getUserToken(): String {
        //return preferences.getString(USER_TOKEN, "4most") ?: ""
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwibmFtZSI6InFxIiwiZW1haWwiOiJxcUBxcS5xcSIsImlhdCI6MTYxMDMzMzQ0MywiZXhwIjoxNjEyOTI1NDQzLCJpc3MiOiJjeWoifQ.k3HAJg9K_NMVscJWafGBdCB4Odj6qua9VUL2N3_siYo"
    }

    fun setUserToken(value: String) {
        preferences.edit().putString(USER_TOKEN, value).apply()
    }

    fun getUserName(): String {
        return preferences.getString(USER_NAME, "qq") ?: ""
    }

    fun setUserName(value: String) {
        preferences.edit().putString(USER_NAME, value).apply()
    }

    fun getUserEmail(): String {
        return preferences.getString(USER_EMAIL, "qq@qq.qq") ?: ""
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
}