package com.peter.nagy.mobsoft.homework.test.presenter

import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMoviesEvent
import com.peter.nagy.mobsoft.homework.ui.movies.MoviesPresenter
import com.peter.nagy.mobsoft.homework.ui.movies.MoviesScreen
import com.peter.nagy.mobsoft.homework.util.UiExecutor
import io.swagger.client.model.MovieListObject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MoviesPresenterTest {

    @Mock
    lateinit var screen: MoviesScreen

    @Mock
    lateinit var interactor: MoviesInteractor

    lateinit var presenter: MoviesPresenter

    @Before
    fun setUp() {
        val executor = UiExecutor()
        MockitoAnnotations.initMocks(this)
        presenter = MoviesPresenter(executor, interactor)
        presenter.attachScreen(screen)
    }
    @After
    fun tearDown() {
        presenter.detachScreen()
    }
    @Test
    fun `it should download movies when started`() {
        presenter.onStart()
        verify(interactor).getMovies(null)
    }
    @Test
    fun `it should navigate to movie`() {
        presenter.showMovie(0)
        verify(screen, times(1)).showMovie(0)
    }
    @Test
    fun `it should query for movies after search query change`() {
        val query = "test"
        presenter.searchTermChanged(query)

        verify(interactor).getMovies(query)
    }
    @Test
    fun `it should show movies after receiving event`() {
        val event = GetMoviesEvent()
        val movies: List<MovieListObject> = listOf()
        event.movies = movies
        presenter.showMovies(event)

        verify(screen).showMovies(movies)
    }
    @Test
    fun `it should not show movies if there was an error`() {
        val event = GetMoviesEvent()
        val movies: List<MovieListObject> = listOf()
        event.movies = movies
        event.throwable = Throwable()
        event.movies = movies
        presenter.showMovies(event)

        verify(screen, times(0)).showMovies(movies)
    }
}