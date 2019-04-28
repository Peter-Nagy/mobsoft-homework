package com.peter.nagy.mobsoft.homework.ui.movies

import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class MoviesPresenter @Inject constructor(private val executor: Executor, private val moviesInteractor: MoviesInteractor) : Presenter<MoviesScreen>() {
    fun showMovieSearchList() {
        executor.execute {
            moviesInteractor.getMovies(null)
        }
    }
    fun buttonPressed() {
        screen?.showMovie("")
    }
}