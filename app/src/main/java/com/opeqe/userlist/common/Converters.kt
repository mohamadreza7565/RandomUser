package com.opeqe.userlist.common

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.opeqe.userlist.data.Result


class Converters {

    @TypeConverter
    fun listToJson(value: MutableList<Result>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Result>::class.java).toList()

}