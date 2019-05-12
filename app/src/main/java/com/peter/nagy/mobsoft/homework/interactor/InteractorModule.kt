package com.peter.nagy.mobsoft.homework.interactor

import com.peter.nagy.mobsoft.homework.db.AppDatabase
import com.peter.nagy.mobsoft.homework.interactor.movies.LikeInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import dagger.Module
import dagger.Provides
import io.swagger.client.api.MoviesApi
import io.swagger.client.api.SearchApi
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideMoviesInteractor(moviesApi: MoviesApi, searchApi: SearchApi) = MoviesInteractor(moviesApi, searchApi)
    @Provides
    @Singleton
    fun provideLikeInteractor(db: AppDatabase) = LikeInteractor(db)
}