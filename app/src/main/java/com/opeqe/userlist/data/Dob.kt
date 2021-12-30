package com.opeqe.userlist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dob(
    val age: Int,
    @ColumnInfo(name = "dobDate")
    val date: String
):Parcelable