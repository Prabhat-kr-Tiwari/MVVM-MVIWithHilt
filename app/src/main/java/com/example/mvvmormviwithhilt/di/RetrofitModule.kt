package com.example.mvvmormviwithhilt.di

import com.example.mvvmormviwithhilt.retrofit.BlogRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//retrofit dependency here
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson):Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    //retrofit instance
    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder):BlogRetrofit{
        return retrofit.build().create(BlogRetrofit::class.java)
    }



}