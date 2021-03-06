package com.peter.nagy.mobsoft.homework.interactor.movies.event

import io.swagger.client.model.MovieListObject

data class GetMoviesEvent(
    var code: Int = 0,
    var movies: List<MovieListObject>? = null,
    var throwable: Throwable? = null
)