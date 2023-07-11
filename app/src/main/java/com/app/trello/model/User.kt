package com.app.trello.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("user_id")
    val user_id: Int = 0

    @SerializedName("full_name")
    val full_name: String = ""

    @SerializedName("avatar")
    val avatar: String =""
}