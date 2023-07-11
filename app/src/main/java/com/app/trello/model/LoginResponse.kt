package com.app.trello.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token")
    val token: String = ""

    @SerializedName("user")
    lateinit var user: User
}