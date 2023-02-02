package com.example.dstvmovie.di

import com.example.dstvmovie.data.repository.MovieRepository
import com.example.dstvmovie.data.repository.impl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(userRepositoryImpl: MovieRepositoryImpl): MovieRepository =
        userRepositoryImpl
}