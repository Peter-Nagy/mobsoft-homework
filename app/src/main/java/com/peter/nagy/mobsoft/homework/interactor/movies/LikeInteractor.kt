package com.peter.nagy.mobsoft.homework.interactor.movies

import com.peter.nagy.mobsoft.homework.db.AppDatabase
import com.peter.nagy.mobsoft.homework.db.Like

class LikeInteractor(private val db: AppDatabase) {
    fun likeMovie(movieId: Int) {
        val existingLike = db.likeDao().getLike(movieId)
        if (existingLike == null) {
            val like = Like(movieId = movieId)
            db.likeDao().insertLike(like)
        }
    }
    fun dislikeMovie(movieId: Int) {
        val like = db.likeDao().getLike(movieId)
        if (like != null) {
            db.likeDao().deleteLike(like)
        }
    }
    fun isLiked(movieId: Int): Boolean {
        val like = db.likeDao().getLike(movieId)
        return like != null
    }
}