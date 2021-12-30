package com.opeqe.userlist.repo.source

import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import io.reactivex.Single

interface HomeDataSource {

    fun getUsers(): Single<User>

    fun getUsersFromDb(): Single<MutableList<Result>>

}