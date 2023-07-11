package com.app.trello.model

import com.google.gson.annotations.SerializedName

class AllTasksResponse {
    @SerializedName("task_id")
    val task_id: Int = 0

    @SerializedName("index")
    val index: Int = 0

    @SerializedName("project_id")
    val project_id: String =""

    @SerializedName("project_name")
    val project_name: String= ""

    @SerializedName("owner_id")
    val owner_id: Int = 0

    @SerializedName("owner_name")
    val owner_name: String = ""

    @SerializedName("owner_avatar")
    val owner_avatar: String = ""

    @SerializedName("executor_id")
    val executor_id: Int =0

    @SerializedName("executor_name")
    val executor_name: String= ""

    @SerializedName("executor_avatar")
    val executor_avatar: String = ""

    @SerializedName("task_date")
    val task_date: String = ""

    @SerializedName("term_date")
    val term_date: String = ""

    @SerializedName("name")
    val name: String = ""

    @SerializedName("priority")
    val priority: String = ""

    @SerializedName("status")
    val status: String = ""

}