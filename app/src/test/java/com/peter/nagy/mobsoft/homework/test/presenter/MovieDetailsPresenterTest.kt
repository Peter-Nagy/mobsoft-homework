package com.peter.nagy.mobsoft.homework.test.presenter

import com.peter.nagy.mobsoft.homework.interactor.movies.LikeInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMovieEvent
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsPresenter
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsScreen
import com.peter.nagy.mobsoft.homework.util.UiExecutor
import io.swagger.client.model.MovieDetailsObject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDetailsPresenterTest {
    @Mock
    lateinit var screen: MovieDetailsScreen

    @Mock
    lateinit var interactor: MoviesInteractor

    @Mock
    lateinit var likeInteractor: LikeInteractor

    lateinit var presenter: MovieDetailsPresenter

    @Before
    fun setUp() {
        val executor = UiExecutor()
        MockitoAnnotations.initMocks(this)
        presenter = MovieDetailsPresenter(executor, interactor, likeInteractor)
    }
    @After
    fun tearDown() {
        presenter.detachScreen()
    }
    @Test
    fun `it should show movie`() {
        presenter.attachScreen(screen)
        val event = GetMovieEvent()
        val movie = MovieDetailsObject()
        movie.id = 1
        event.movie = movie
        presenter.showMovie(event)

        verify(screen).showMovie(movie)
    }
    @Test
    fun `it should call getMovie after attach`() {
        presenter.attachScreen(screen, 1)

        verify(interactor).getMovie(1)
    }
    @Test
    fun `it should show like when movie is liked`() {
        presenter.attachScreen(screen, 1)
        Mockito.`when`(likeInteractor.isLiked(1)).thenReturn(false)

        presenter.likePressed()

        verify(screen).showLike()
        verify(screen, times(0)).removeLike()
    }
    @Test
    fun `it should hide like when movie is liked`() {
        presenter.attachScreen(screen, 1)
        Mockito.`when`(likeInteractor.isLiked(1)).thenReturn(true)

        presenter.likePressed()

        verify(screen).removeLike()
        verify(screen, times(0)).showLike()
    }

    @Test
    fun `it should not show movies if there was an error`() {
        val event = GetMovieEvent()
        val movie = MovieDetailsObject()
        event.movie = movie
        event.throwable = Throwable()
        event.movie = movie
        presenter.showMovie(event)

        verify(screen, times(0)).showMovie(movie)
    }
}