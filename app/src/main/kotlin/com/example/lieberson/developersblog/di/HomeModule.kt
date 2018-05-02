package com.example.lieberson.developersblog.di

import com.example.lieberson.developersblog.domain.HomeLocalRepository
import com.example.lieberson.developersblog.domain.HomeRemoteRepository
import com.example.lieberson.developersblog.domain.HomeUseCase
import com.example.lieberson.developersblog.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Define HomeFragment-specific dependencies here.
 */
@Module
class HomeModule {

    @Provides
    internal fun provideHomeViewModelFactory(homeUseCase: HomeUseCase): HomeViewModelFactory {
        return HomeViewModelFactory(homeUseCase)
    }

    @Provides
    internal fun provideHomeUseCase(homeLocalRepository: HomeLocalRepository,
                                    remoteRepository: HomeRemoteRepository): HomeUseCase {
        return HomeUseCase(homeLocalRepository, remoteRepository)
    }
}