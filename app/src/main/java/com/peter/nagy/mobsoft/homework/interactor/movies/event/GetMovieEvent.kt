package com.peter.nagy.mobsoft.homework.interactor.movies.event

import io.swagger.client.model.MovieDetailsObject

data class GetMovieEvent(
    var code: Int = 0,
    var movie: MovieDetailsObject? = null,
    var throwable: Throwable? = null
)