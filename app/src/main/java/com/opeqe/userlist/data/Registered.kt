package com.opeqe.userlist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Registered(
    @ColumnInfo(name = "registeredAge")
    val age: Int,
    val date: String
):Parcelable