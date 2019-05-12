package com.peter.nagy.mobsoft.homework.test.interactor

import com.peter.nagy.mobsoft.homework.db.AppDatabase
import com.peter.nagy.mobsoft.homework.db.Like
import com.peter.nagy.mobsoft.homework.db.LikeDao
import com.peter.nagy.mobsoft.homework.interactor.movies.LikeInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LikeInteractorTest {
    lateinit var interactor: LikeInteractor

    @Mock
    lateinit var db: AppDatabase

    @Mock
    lateinit var mockLikeDao: LikeDao

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(db.likeDao()).thenReturn(mockLikeDao)
        interactor = LikeInteractor(db)
    }
    @Test
    fun `it should return false when like is null`() {
        Mockito.`when`(mockLikeDao.getLike(1)).thenReturn(null)

        assert(!interactor.isLiked(1))
    }
    @Test
    fun `it should return true when like is not null`() {
        Mockito.`when`(mockLikeDao.getLike(1)).thenReturn(Like(movieId = 1))

        assert(interactor.isLiked(1))
    }
    @Test
    fun `it should create a like when liking is like is null`() {
        interactor.likeMovie(1)

        verify(mockLikeDao).insertLike(any())
    }
    @Test
    fun `it should delete the like when dislike is called`() {
        val like = Like(movieId = 1)
        Mockito.`when`(mockLikeDao.getLike(1)).thenReturn(like)
        interactor.dislikeMovie(1)

        verify(mockLikeDao).deleteLike(like)
    }
}