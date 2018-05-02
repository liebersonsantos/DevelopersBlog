package com.example.lieberson.developersblog.data.dao

import android.arch.persistence.room.*
import com.example.lieberson.developersblog.model.Post
import io.reactivex.Flowable

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(post: Post): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(posts: List<Post>?)

    @Update
    fun update(post: Post)

    @Delete
    fun delete(post: Post)

    @Query("DELETE FROM posts")
    fun deleteAll()

    @Query("SELECT * FROM posts LIMIT 30")
    fun getAll(): Flowable<List<Post>>

    @Query("select * from posts where id = :id")
    fun getByID(id: Int): Flowable<Post>

    @Query("SELECT * FROM posts where title LIKE '%'||:title|| '%' LIMIT 30")
    fun getAllByTitle(title: String): Flowable<List<Post>>
}