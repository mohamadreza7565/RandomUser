package com.opeqe.userlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.opeqe.userlist.common.Converters
import com.opeqe.userlist.data.Result
import com.opeqe.userlist.data.User

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "db_opeqe"
    }

}

fun createDataBaseInstance(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

}