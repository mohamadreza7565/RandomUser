package com.opeqe.userlist.common

import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class CustomCompletableObserver(var compositeDisposable: CompositeDisposable) : CompletableObserver{
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        EventBus.getDefault().post(CustomExceptionMapper.map(e))
    }
}