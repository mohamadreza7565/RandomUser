package com.opeqe.userlist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Street(
    @ColumnInfo(name="streetName")
    val name: String,
    val number: Int
):Parcelable