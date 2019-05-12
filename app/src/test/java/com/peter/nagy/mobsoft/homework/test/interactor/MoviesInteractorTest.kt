package com.peter.nagy.mobsoft.homework.test.interactor

import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.mock.mockMovieDetails
import com.peter.nagy.mobsoft.homework.mock.mockMovieDetailsFailing
import com.peter.nagy.mobsoft.homework.mock.mockPopularMovies
import com.peter.nagy.mobsoft.homework.mock.mockPopularMoviesFailing
import io.swagger.client.api.MoviesApi
import io.swagger.client.api.SearchApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MoviesInteractorTest {
    lateinit var interactor: MoviesInteractor

    @Mock
    lateinit var moviesApi: MoviesApi

    @Mock
    lateinit var searchApi: SearchApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(moviesApi.popularMovies()).thenReturn(mockPopularMovies())
        Mockito.`when`(searchApi.searchMovie(any())).thenReturn(mockPopularMovies())
        interactor = MoviesInteractor(moviesApi, searchApi)
    }
    @Test
    fun `it should call popularMovies when no query is specified`() {
        interactor.getMovies(null)

        verify(moviesApi).popularMovies()
        verify(searchApi, times(0)).searchMovie("")
    }
    @Test
    fun `it should call searchMovie when a query is specified`() {
        val query = "test"
        interactor.getMovies(query)

        verify(moviesApi, times(0)).popularMovies()
        verify(searchApi).searchMovie(query)
    }
    @Test(expected = Exception::class)
    fun `it should throw an exception if response code is not 200`() {
        Mockito.`when`(moviesApi.popularMovies()).thenReturn(mockPopularMoviesFailing())

        interactor.getMovies(null)
    }
    @Test(expected = Exception::class)
    fun `it should throw an exception if response code is not 200 and query is provided`() {
        Mockito.`when`(searchApi.searchMovie(any())).thenReturn(mockPopularMoviesFailing())

        interactor.getMovies("test")
    }

    @Test
    fun `it should query movieDetails when getMovie was called`() {
        Mockito.`when`(moviesApi.movieDetails(1)).thenReturn(mockMovieDetails(movieId = 1))
        interactor.getMovie(1)

        verify(moviesApi).movieDetails(1)
    }

    @Test(expected = Exception::class)
    fun `it should throw an exception if movieDetails returns a non-200 response`() {
        Mockito.`when`(moviesApi.movieDetails(1)).thenReturn(mockMovieDetailsFailing(movieId = 1))
        interactor.getMovie(1)
    }

}