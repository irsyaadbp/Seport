package com.irsyaad.dicodingsubmission.seport.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailEventModel(
    val home: String?,
    val title: String?,
    val away: String?
): Parcelable