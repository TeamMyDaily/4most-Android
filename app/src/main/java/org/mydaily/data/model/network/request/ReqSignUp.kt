package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqSignUp(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("passwordConfirm")
    val passwordConfirm: String,
    @SerializedName("userName")
    val userName: String
)