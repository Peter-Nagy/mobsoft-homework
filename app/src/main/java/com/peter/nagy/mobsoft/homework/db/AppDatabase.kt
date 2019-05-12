package com.peter.nagy.mobsoft.homework.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Like::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun likeDao(): LikeDao
}