package com.example.lieberson.developersblog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
        @SerializedName("id") var id: String = "",
        @SerializedName("displayName") var displayName: String = "",
        @SerializedName("url") var url: String = "",
        @SerializedName("image") var image: Image? = null

) : Parcelable

