package com.peter.nagy.mobsoft.homework.ui

import android.content.Context
import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsPresenter
import com.peter.nagy.mobsoft.homework.ui.movies.MoviesPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)

    @Provides
    @Singleton
    fun moviesPresenter(executor: Executor, moviesInteractor: MoviesInteractor) = MoviesPresenter(executor, moviesInteractor)

    @Provides
    @Singleton
    fun movieDetailsPresenter() = MovieDetailsPresenter()

}