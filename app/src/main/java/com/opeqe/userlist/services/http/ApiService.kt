package com.opeqe.userlist.services.http


import com.opeqe.userlist.data.TokenContainer
import com.opeqe.userlist.data.User
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getUsers(@Query("format") format: String,@Query("results") results : Int): Single<User>

}


fun createApiServiceInstance(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
            if (TokenContainer.token != null)
                newRequestBuilder.addHeader("Authorization", "Bearer ${TokenContainer.token}")

            newRequestBuilder.addHeader("Accept", "application/json")

            newRequestBuilder.method(oldRequest.method(), oldRequest.body())

            return@addInterceptor it.proceed(newRequestBuilder.build())
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)

}