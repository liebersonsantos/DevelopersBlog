package com.example.lieberson.developersblog.data

import android.arch.persistence.room.TypeConverter
import android.media.Image
import com.example.lieberson.developersblog.model.Author
import com.example.lieberson.developersblog.model.Blog
import com.example.lieberson.developersblog.model.Replies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? = if (timestamp == null) null else Date(timestamp)

    @TypeConverter
    fun toTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromBlog(blog: String): Blog {
        val listType = object : TypeToken<Blog>() {

        }.type
        return Gson().fromJson<Blog>(blog, listType)
    }

    @TypeConverter
    fun fromBlog(blog: Blog): String {
        val gson = Gson()
        return gson.toJson(blog)
    }

    @TypeConverter
    fun fromAuthor(author: String): Author {
        val listType = object : TypeToken<Blog>() {

        }.type
        return Gson().fromJson<Author>(author, listType)
    }

    @TypeConverter
    fun fromAuthor(author: Author): String {
        val gson = Gson()
        return gson.toJson(author)
    }

    @TypeConverter
    fun fromImage(image: String): Image {
        val listType = object : TypeToken<Image>() {

        }.type
        return Gson().fromJson<Image>(image, listType)
    }

    @TypeConverter
    fun fromImage(image: Image): String {
        val gson = Gson()
        return gson.toJson(image)
    }

    @TypeConverter
    fun fromReplies(replies: String): Replies {
        val listType = object : TypeToken<Replies>() {

        }.type
        return Gson().fromJson<Replies>(replies, listType)
    }

    @TypeConverter
    fun fromReplies(replies: Replies): String {
        val gson = Gson()
        return gson.toJson(replies)
    }

}

