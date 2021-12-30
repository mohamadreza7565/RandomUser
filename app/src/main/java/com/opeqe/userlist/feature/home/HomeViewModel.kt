package com.opeqe.userlist.feature.home

import androidx.lifecycle.MutableLiveData
import com.opeqe.userlist.common.Base
import com.opeqe.userlist.common.CustomSingleObserver
import com.opeqe.userlist.common.asyncNetworkRequest
import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User
import com.opeqe.userlist.database.AppDatabase
import com.opeqe.userlist.database.UserDao
import com.opeqe.userlist.repo.HomeRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DefaultSubscriber
import timber.log.Timber
import java.lang.Exception

class HomeViewModel(
    val homeRepository: HomeRepository,
    val appDatabase: AppDatabase
) : Base.CustomViewModel() {

    val userListLiveData = MutableLiveData<MutableList<Result>>()
    private var dbResult: MutableList<Result> = ArrayList()
    private var userDao: UserDao

    init {
        userDao = appDatabase.userDao()
        dbResult = userDao.get()
        if (dbResult.isNotEmpty())
            userListLiveData.value = appDatabase.userDao().get()
        getUsers()
    }

    private fun getUsers() {
        if (dbResult.isEmpty())
            progressBarLiveData.value = true
        homeRepository.getUsers()
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : CustomSingleObserver<User>(compositeDisposable) {
                override fun onSuccess(t: User) {

                    userDao.nukeTable()
                    t.results.forEach {
                        try {
                            userDao.insert(it)
                        } catch (e: Exception) {

                        }
                    }

                    userListLiveData.value = t.results
                }

            })
    }


}