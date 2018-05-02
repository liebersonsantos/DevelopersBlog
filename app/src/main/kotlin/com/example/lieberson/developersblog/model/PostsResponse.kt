package com.example.lieberson.developersblog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsResponse(
        @SerializedName("kind") var kind: String = "",
        @SerializedName("nextPageToken") var nextPageToken: String = "",
        @SerializedName("items") var posts: List<Post> = ArrayList(),
        @SerializedName("etag") var etag: String = ""
) : Parcelable