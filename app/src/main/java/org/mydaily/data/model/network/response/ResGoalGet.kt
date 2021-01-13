package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResGoalGet(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var `data`: Data
) {
    data class Data(
        @SerializedName("keywordsExist")
        var keywordsExist: Boolean,
        @SerializedName("result")
        var result: Result
    ) {
        data class Result(
            @SerializedName("notSetGoalCount")
            var notSetGoalCount: Int,
            @SerializedName("count")
            var count: Int,
            @SerializedName("keywords")
            var keywords: List<Keyword>
        ) {
            data class Keyword(
                @SerializedName("totalKeywordId")
                var totalKeywordId: Int,
                @SerializedName("priority")
                var priority: Int,
                @SerializedName("name")
                var name: String,
                @SerializedName("isGoalCreated")
                var isGoalCreated: Boolean,
                @SerializedName("weekGoalId")
                var weekGoalId: Int,
                @SerializedName("weekGoal")
                var weekGoal: String,
                @SerializedName("isGoalCompleted")
                var isGoalCompleted: Boolean
            )
        }
    }
}