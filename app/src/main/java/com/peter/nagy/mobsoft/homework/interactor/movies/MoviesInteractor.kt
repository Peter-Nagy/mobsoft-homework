package com.peter.nagy.mobsoft.homework.interactor.movies

import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMoviesEvent
import com.peter.nagy.mobsoft.homework.network.MoviesApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private var moviesApi: MoviesApi) {
    fun getArtists(moviesQuery: String) {
        val event = GetMoviesEvent()
        EventBus.getDefault().post(event)
        // TODO implement
    }
}