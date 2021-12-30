package com.opeqe.userlist.repo

import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import io.reactivex.Single

interface HomeRepository {

    fun getUsers(): Single<User>

    fun getUsersFromDb(): Single<MutableList<Result>>

}