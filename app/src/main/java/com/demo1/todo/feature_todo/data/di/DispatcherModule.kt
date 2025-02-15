package com.demo1.todo.feature_todo.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDipatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @IoDipatcher
    @Provides
    fun providesIoDispatcher() : CoroutineDispatcher = Dispatchers.IO
}