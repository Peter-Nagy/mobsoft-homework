package com.peter.nagy.mobsoft.homework

import com.peter.nagy.mobsoft.homework.db.DbModule
import com.peter.nagy.mobsoft.homework.interactor.InteractorModule
import com.peter.nagy.mobsoft.homework.network.NetworkModule
import com.peter.nagy.mobsoft.homework.ui.UIModule
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsActivity
import com.peter.nagy.mobsoft.homework.ui.movies.MoviesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class, DbModule::class])
interface MoviesApplicationComponent {
    fun inject(moviesActivity: MoviesActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}