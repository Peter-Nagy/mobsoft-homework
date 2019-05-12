package com.peter.nagy.mobsoft.homework.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query

@Entity
data class Like(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieId: Int)

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLike(like: Like)

    @Delete
    fun deleteLike(like: Like)

    @Query("SELECT * FROM `LIKE` WHERE movieId == :movieId")
    fun getLike(movieId: Int): Like?
}