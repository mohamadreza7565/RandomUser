package com.opeqe.userlist.repo

import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import com.opeqe.userlist.repo.source.HomeDataSource
import com.opeqe.userlist.repo.source.HomeLocalDataSource
import com.opeqe.userlist.repo.source.HomeRemoteDataSource
import io.reactivex.Single
import javax.sql.DataSource

class HomeRepositoryImpl(
    private val remoteDataSource: HomeDataSource,
    private val localDataSource: HomeDataSource
) : HomeRepository {

    override fun getUsers(): Single<User> = remoteDataSource.getUsers()


    override fun getUsersFromDb(): Single<MutableList<Result>> = localDataSource.getUsersFromDb()

}