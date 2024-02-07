package com.example.mvvmormviwithhilt.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmormviwithhilt.room.BlogDao
import com.example.mvvmormviwithhilt.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {


    //building blog database object
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(

            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.blogDao()
    }
}