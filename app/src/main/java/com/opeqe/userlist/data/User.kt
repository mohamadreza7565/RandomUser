package com.opeqe.userlist.data

import androidx.room.Entity

data class User(
    val info: Info,
    val results: MutableList<Result>
)