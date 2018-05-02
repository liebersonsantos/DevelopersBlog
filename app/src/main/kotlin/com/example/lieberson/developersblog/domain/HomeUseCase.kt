package com.example.lieberson.developersblog.domain

import com.example.lieberson.developersblog.model.Post
import com.example.lieberson.developersblog.model.PostsResponse
import io.reactivex.Flowable
import io.reactivex.Single

class HomeUseCase(private val homeLocalRepository: HomeLocalRepository,
                  private val homeRemoteRepository: HomeRemoteRepository) {

    fun getAllPosts(): Single<PostsResponse> {
        return homeRemoteRepository.getPosts()
    }

    fun getAllPostsFromDB(): Flowable<List<Post>> {
        return homeLocalRepository.getAll()
    }

    fun deleteAllPosts() {
        homeLocalRepository.deleteAll()
    }

    fun addAllPosts(posts: List<Post>) {
        homeLocalRepository.addAll(posts)
    }
}