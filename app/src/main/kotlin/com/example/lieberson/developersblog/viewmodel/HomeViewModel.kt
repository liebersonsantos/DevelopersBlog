package com.example.lieberson.developersblog.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.lieberson.developersblog.domain.HomeUseCase
import com.example.lieberson.developersblog.model.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val response: MutableLiveData<List<Post>> = MutableLiveData()

    private val loadingStatus = MutableLiveData<Boolean>()

    private val errorStatus = MutableLiveData<Boolean>()

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return loadingStatus
    }

    fun getErrorStatus(): MutableLiveData<Boolean> {
        return errorStatus
    }

    fun getResponse(): MutableLiveData<List<Post>> {
        return response
    }

    fun getAllPosts() {
        disposable.add(homeUseCase.getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.value = true }
                .doAfterTerminate { loadingStatus.value = false }
                .doOnError { loadResponseFromDB() }
                .subscribe(
                        { postResponse ->
                            response.value = postResponse.posts

                            doAsync {
                                homeUseCase.deleteAllPosts()
                                homeUseCase.addAllPosts(postResponse.posts)
                            }
                        },
                        { throwable ->
                            Log.d("TAG", "ERROR: ${throwable.message}")
                            loadResponseFromDB()
                        })
        )
    }

    private fun loadResponseFromDB() {
        disposable.add(homeUseCase.getAllPostsFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.value = true }
                .doAfterTerminate { loadingStatus.value = false }
                .subscribe(
                        { postResponse ->
                            response.value = postResponse
                        },
                        { throwable ->
                            Log.d("TAG", "ERROR DB: ${throwable.message}")
                        }
                ))
    }
}