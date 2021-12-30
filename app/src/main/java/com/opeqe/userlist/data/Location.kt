package com.opeqe.userlist.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Ignore
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val city: String,
    @Embedded
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    @Embedded
    val street: Street,
    @Embedded
    val timezone: Timezone
):Parcelable