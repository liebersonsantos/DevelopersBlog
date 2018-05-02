package com.example.lieberson.developersblog.domain

import com.example.lieberson.developersblog.model.PostsResponse
import io.reactivex.Single

interface HomeRemoteRepository {

    fun getPosts(): Single<PostsResponse>

}