package com.opeqe.userlist.repo.source

import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import com.opeqe.userlist.database.AppDatabase
import io.reactivex.Single

class HomeLocalDataSource(private val appDatabase: AppDatabase) : HomeDataSource {
    override fun getUsers(): Single<User> {
        TODO("Not yet implemented")
    }

    override fun getUsersFromDb(): Single<MutableList<Result>> {
        val users = appDatabase.userDao().get()
        return Single.just(users)
    }
}