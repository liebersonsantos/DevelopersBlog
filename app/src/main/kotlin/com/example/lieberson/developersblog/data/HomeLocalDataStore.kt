package com.example.lieberson.developersblog.data

import com.example.lieberson.developersblog.data.dao.PostsDao
import com.example.lieberson.developersblog.domain.HomeLocalRepository
import com.example.lieberson.developersblog.model.Post
import io.reactivex.Flowable

class HomeLocalDataStore(private  val postsDao: PostsDao) : HomeLocalRepository {
    override fun add(post: Post): Long {
       return postsDao.add(post)
    }

    override fun addAll(posts: List<Post>) {
        postsDao.addAll(posts)
    }

    override fun update(post: Post) {
        postsDao.update(post)
    }

    override fun delete(post: Post) {
        postsDao.delete(post)
    }

    override fun deleteAll() {
        postsDao.deleteAll()
    }

    override fun getAll(): Flowable<List<Post>> {
      return  postsDao.getAll()
    }

    override fun getById(id: Int): Flowable<Post> {
      return  postsDao.getByID(id)
    }

    override fun getByTitle(title: String): Flowable<List<Post>> {
      return  postsDao.getAllByTitle(title)
    }
}