package com.demo1.todo.feature_todo.data.di

import android.content.Context
import androidx.room.Room
import com.demo1.todo.feature_todo.data.local.TodoDao
import com.demo1.todo.feature_todo.data.local.TodoDataBase
import com.demo1.todo.feature_todo.data.remote.TodoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
object TodoModule {

    @Provides
    fun provideDao(dataBase: TodoDataBase) : TodoDao{
        return  dataBase.dao
    }

    @Singleton
    @Provides
    fun ProvideRoomDb(@ApplicationContext appContext: Context):TodoDataBase {
    return Room.databaseBuilder(
        context = appContext,
        klass = TodoDataBase::class.java,
        name = TodoDataBase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://todoandroid-80860-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofit(retrofit : Retrofit) : TodoApi{
        return retrofit.create(TodoApi::class.java)
    }



}