package com.example.lieberson.developersblog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Blog(
        @SerializedName("id") var id: String = ""

) : Parcelable