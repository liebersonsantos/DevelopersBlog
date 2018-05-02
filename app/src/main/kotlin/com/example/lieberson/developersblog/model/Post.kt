package com.example.lieberson.developersblog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class Post(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: String = "",

        @ColumnInfo(name = "kind")
        @SerializedName("kind")
        var kind: String = "",

        @ColumnInfo(name = "blog")
        @SerializedName("blog")
        var blog: Blog? = null,

        @ColumnInfo(name = "published")
        @SerializedName("published")
        var published: String = "",

        @ColumnInfo(name = "updated")
        @SerializedName("updated")
        var updated: String = "",

        @ColumnInfo(name = "etag")
        @SerializedName("etag")
        var etag: String = "",

        @ColumnInfo(name = "url")
        @SerializedName("url")
        var url: String = "",

        @ColumnInfo(name = "selfLink")
        @SerializedName("selfLink")
        var selfLink: String = "",

        @ColumnInfo(name = "title")
        @SerializedName("title")
        var title: String = "",

        @ColumnInfo(name = "content")
        @SerializedName("content")
        var content: String = "",

        @ColumnInfo(name = "author")
        @SerializedName("author")
        var author: Author? = null,

        @ColumnInfo(name = "replies")
        @SerializedName("replies")
        var replies: Replies? = null,

        @ColumnInfo(name = "labels")
        @SerializedName("labels")
        var labels: List<String>? = ArrayList()
) : Parcelable