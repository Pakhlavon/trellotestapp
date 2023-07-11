package com.app.trello.utils

import com.chibatching.kotpref.KotprefModel

object SharedPref : KotprefModel() {
    var token by stringPref("")
    var user_name by stringPref("")
}