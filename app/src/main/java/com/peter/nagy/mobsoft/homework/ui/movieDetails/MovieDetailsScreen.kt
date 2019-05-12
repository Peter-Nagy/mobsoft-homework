package com.peter.nagy.mobsoft.homework.ui.movieDetails

import io.swagger.client.model.MovieDetailsObject

interface MovieDetailsScreen {
    fun showMovie(movie: MovieDetailsObject)
    fun showLike()
    fun removeLike()
}