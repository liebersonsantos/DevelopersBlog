package com.example.lieberson.developersblog.di
import android.content.Context
import com.example.lieberson.developersblog.CustomApplication
import com.example.lieberson.developersblog.data.AppDatabase
import com.example.lieberson.developersblog.data.HomeLocalDataStore
import com.example.lieberson.developersblog.data.HomeRemoteDataStore
import com.example.lieberson.developersblog.data.dao.PostsDao
import com.example.lieberson.developersblog.domain.HomeLocalRepository
import com.example.lieberson.developersblog.domain.HomeRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
class AppModule {

    @Provides
    internal fun provideContext(application: CustomApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal fun providePostsDao(context: Context): PostsDao {
        return AppDatabase.getInstance(context).postsDAO()
    }

    @Singleton
    @Provides
    internal fun provideHomeLocalRepository(postsDao: PostsDao): HomeLocalRepository {
        return HomeLocalDataStore(postsDao)
    }

    @Singleton
    @Provides
    internal fun provideHomeRemoteRepository(): HomeRemoteRepository {
        return HomeRemoteDataStore()
    }

}