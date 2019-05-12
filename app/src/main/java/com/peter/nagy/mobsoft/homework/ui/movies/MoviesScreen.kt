package com.peter.nagy.mobsoft.homework.ui.movies

import io.swagger.client.model.MovieListObject

interface MoviesScreen {
    fun showMovie(movieId: Int)
    fun showMovies(movies: List<MovieListObject>)
}