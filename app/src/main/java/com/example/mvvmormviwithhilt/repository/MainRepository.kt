package com.example.mvvmormviwithhilt.repository

import com.example.mvvmormviwithhilt.model.Blog
import com.example.mvvmormviwithhilt.retrofit.BlogRetrofit
import com.example.mvvmormviwithhilt.retrofit.NetworkMapper
import com.example.mvvmormviwithhilt.room.BlogDao
import com.example.mvvmormviwithhilt.room.CacheMapper
import com.example.mvvmormviwithhilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class MainRepository
@Inject
constructor(

    private val blogDao:BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper:CacheMapper,
    private val networkMapper: NetworkMapper
){


    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{

        emit(DataState.Loading)
        delay(1000)

        try {
            val networkBlogs=blogRetrofit.get()
            val blogs=networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs)
            {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            //get from the cache
            val cachedBlogs=blogDao.get()
//            then set to the ui
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))

        }catch (e:Exception){
            DataState.Error(e)

        }

    }

}