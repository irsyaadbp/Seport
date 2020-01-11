package com.irsyaad.dicodingsubmission.seport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SportModel(
    val id: Int,
    val league: String?,
    val badge: Int,
    val description: String?
):Parcelable