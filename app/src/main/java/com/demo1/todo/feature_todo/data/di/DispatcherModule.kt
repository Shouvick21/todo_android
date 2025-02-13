package com.demo1.todo.feature_todo.data.di

import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDipatcher

object DispatcherModule {
    @IoDipatcher
    @Provides
    fun providesIoDispatcher() : CoroutineDispatcher = Dispatchers.IO
}