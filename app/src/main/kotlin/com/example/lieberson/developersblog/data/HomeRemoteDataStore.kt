package com.example.lieberson.developersblog.data

import com.example.lieberson.developersblog.BuildConfig
import com.example.lieberson.developersblog.domain.HomeRemoteRepository
import com.example.lieberson.developersblog.domain.services.RemoteApiService
import com.example.lieberson.developersblog.model.PostsResponse
import io.reactivex.Single

class HomeRemoteDataStore : HomeRemoteRepository {
    override fun getPosts(): Single<PostsResponse> {
        return RemoteApiService.getInstance().getApiService().getPosts(BuildConfig.KEY)
    }
}