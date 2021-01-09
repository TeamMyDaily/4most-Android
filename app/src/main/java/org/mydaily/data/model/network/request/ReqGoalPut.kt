package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqGoalPut(
    @SerializedName("goal")
    val goal: String
)