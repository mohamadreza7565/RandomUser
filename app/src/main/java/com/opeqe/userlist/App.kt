package com.opeqe.userlist

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import com.facebook.drawee.backends.pipeline.Fresco
import com.opeqe.userlist.database.createDataBaseInstance
import com.opeqe.userlist.feature.home.HomeViewModel
import com.opeqe.userlist.feature.home.UserListAdapter
import com.opeqe.userlist.repo.HomeRepository
import com.opeqe.userlist.repo.HomeRepositoryImpl
import com.opeqe.userlist.repo.source.HomeLocalDataSource
import com.opeqe.userlist.repo.source.HomeRemoteDataSource
import com.opeqe.userlist.services.FrescoImageLoadingService
import com.opeqe.userlist.services.ImageLoadingService
import com.opeqe.userlist.services.http.createApiServiceInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    companion object {
        @get:Synchronized
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Timber.plant(Timber.DebugTree())

        Fresco.initialize(this)

        val myModules = module {

            single { createApiServiceInstance() }
            single { createDataBaseInstance(this@App) }

            single<ImageLoadingService> { FrescoImageLoadingService() }

            single<SharedPreferences> {
                this@App.getSharedPreferences(
                    "app_settings",
                    MODE_PRIVATE
                )
            }

            factory<HomeRepository> {
                HomeRepositoryImpl(
                    HomeRemoteDataSource(get()),
                    HomeLocalDataSource(get())
                )
            }

            factory { UserListAdapter(this@App, get()) }

            viewModel { HomeViewModel(get(), get()) }

        }

        startKoin {
            modules(myModules)
        }

    }

}