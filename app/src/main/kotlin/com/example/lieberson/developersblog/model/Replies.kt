package com.example.lieberson.developersblog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Replies(
        @SerializedName("totalItems") var totalItems: String = "",
        @SerializedName("selfLink") var selfLink: String = ""

) : Parcelable

