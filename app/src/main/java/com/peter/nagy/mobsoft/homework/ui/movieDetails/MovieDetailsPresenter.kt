package com.peter.nagy.mobsoft.homework.ui.movieDetails

import com.peter.nagy.mobsoft.homework.ui.Presenter

class MovieDetailsPresenter : Presenter<MovieDetailsScreen>() {
    override fun attachScreen(screen: MovieDetailsScreen) {
        super.attachScreen(screen)
        loadMovies()
    }
    private fun loadMovies() {
        // TODO
    }
}