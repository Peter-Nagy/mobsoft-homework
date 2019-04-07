package com.peter.nagy.mobsoft.homework.interactor.movies.event

import com.peter.nagy.mobsoft.homework.model.Movie

data class GetMoviesEvent(
    var code: Int = 0,
    var movies: List<Movie>? = null,
    var throwable: Throwable? = null
)