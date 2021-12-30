package com.opeqe.userlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.opeqe.userlist.data.Result

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(results: MutableList<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result): Long

    @Query("SELECT * FROM tbl_user")
    fun get(): MutableList<Result>

    @Query("DELETE FROM tbl_user")
    fun nukeTable()

}