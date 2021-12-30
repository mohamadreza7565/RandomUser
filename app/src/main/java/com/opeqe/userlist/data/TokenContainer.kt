package com.opeqe.userlist.data

import timber.log.Timber

object TokenContainer {

    var token: String? = null
        private set
    var refreshToken: String? = null
        private set

    fun update(token: String?, refreshToken: String?) {
        Timber.i("Access Token -> ${token?.subSequence(0, 10)},\n Refresh Token -> ${refreshToken?.subSequence(0,10)}")
        this.token = token
        this.refreshToken = refreshToken
    }

}