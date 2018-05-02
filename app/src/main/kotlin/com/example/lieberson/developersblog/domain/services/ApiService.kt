package com.example.lieberson.developersblog.domain.services

import com.example.lieberson.developersblog.model.PostsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts/")
    fun getPosts(@Query("key") key: String): Single<PostsResponse>
}