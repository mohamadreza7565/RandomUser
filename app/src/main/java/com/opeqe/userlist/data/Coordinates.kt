package com.opeqe.userlist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    val latitude: String,
    val longitude: String
):Parcelable