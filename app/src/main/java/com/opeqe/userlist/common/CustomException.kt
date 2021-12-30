package com.opeqe.userlist.common

import androidx.annotation.StringRes

class CustomException(
    val type: Type, @StringRes val userFriendlyMessage: Int = 0,
    val serverMessage: String? = null
) : Throwable() {

    enum class Type {
        SIMPLE, DIALOG, AUTH
    }

}