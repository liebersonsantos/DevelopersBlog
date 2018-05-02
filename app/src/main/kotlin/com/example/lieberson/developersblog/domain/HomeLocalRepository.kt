package com.example.lieberson.developersblog.domain

import com.example.lieberson.developersblog.model.Post
import io.reactivex.Flowable

interface HomeLocalRepository {

    fun add(post: Post) : Long
    fun addAll(posts: List<Post>)
    fun update(post: Post)
    fun delete(post: Post)
    fun deleteAll()
    fun getAll(): Flowable<List<Post>>
    fun getById(id:Int) : Flowable<Post>
    fun getByTitle(title:String) : Flowable<List<Post>>
}