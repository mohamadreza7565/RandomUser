package com.opeqe.userlist.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_user")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val tblId : Int,
    @Embedded
    val id: Id,
    val cell: String,
    @Embedded
    val dob: Dob,
    val email: String,
    val gender: String,
    @Embedded
    val location: Location,
    @Embedded
    val login: Login,
    @Embedded
    val name: Name,
    val nat: String,
    val phone: String,
    @Embedded
    val picture: Picture,
    @Embedded
    val registered: Registered
):Parcelable