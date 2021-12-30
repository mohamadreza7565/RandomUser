package com.opeqe.userlist.repo.source

import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import com.opeqe.userlist.services.http.ApiService
import io.reactivex.Single

class HomeRemoteDataSource(private val apiService: ApiService) : HomeDataSource {

    override fun getUsers(): Single<User> = apiService.getUsers("JSON", 20)

    override fun getUsersFromDb(): Single<MutableList<Result>> {
        TODO("Not yet implemented")
    }

}