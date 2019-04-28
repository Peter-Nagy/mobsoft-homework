package com.peter.nagy.mobsoft.homework.interactor.movies

import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMovieEvent
import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMoviesEvent
import io.swagger.client.api.MoviesApi
import io.swagger.client.api.SearchApi
import io.swagger.client.model.InlineResponse200
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private var moviesApi: MoviesApi, private val searchApi: SearchApi) {
    fun getMovies(moviesQuery: String?) {
        val event = GetMoviesEvent()

        val query = if (moviesQuery != null) {
            searchApi.searchMovie(moviesQuery)
        } else {
            moviesApi.popularMovies()
        }

        val response = query.execute()

        if (response.code() != 200) {
            throw Exception("Result code is not 200")
        }
        event.code = response.code()
        event.movies = response.body()?.results
        EventBus.getDefault().post(event)
    }

    fun getMovie(movieId: Int) {
        val event = GetMovieEvent()

        val query = moviesApi.movieDetails(movieId)

        val response = query.execute()

        if (response.code() != 200) {
            throw Exception("Result code is not 200")
        }
        event.code = response.code()
        event.movie = response.body()
        EventBus.getDefault().post(event)
    }
}