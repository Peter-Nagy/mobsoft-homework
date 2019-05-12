package com.peter.nagy.mobsoft.homework.db

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideMoviesInteractor(context: Context) = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
}