package com.example.mvvmormviwithhilt.di

import com.example.mvvmormviwithhilt.repository.MainRepository
import com.example.mvvmormviwithhilt.retrofit.BlogRetrofit
import com.example.mvvmormviwithhilt.retrofit.NetworkMapper
import com.example.mvvmormviwithhilt.room.BlogDao
import com.example.mvvmormviwithhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ):MainRepository{

        return MainRepository(blogDao,retrofit,cacheMapper,networkMapper)

    }
}