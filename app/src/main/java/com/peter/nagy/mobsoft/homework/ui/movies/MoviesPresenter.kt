package com.peter.nagy.mobsoft.homework.ui.movies

import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMoviesEvent
import com.peter.nagy.mobsoft.homework.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class MoviesPresenter @Inject constructor(private val executor: Executor, private val moviesInteractor: MoviesInteractor) : Presenter<MoviesScreen>() {
    override fun attachScreen(screen: MoviesScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun showMovie(id: Int) {
        screen?.showMovie(id)
    }

    fun onStart() {
        executor.execute {
            moviesInteractor.getMovies(null)
        }
    }

    fun searchTermChanged(query: String) {
        executor.execute {
            moviesInteractor.getMovies(query)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showMovies(moviesEvent: GetMoviesEvent) {
        if (moviesEvent.throwable != null) {
            moviesEvent.throwable?.printStackTrace()
        } else {
            val movies = moviesEvent.movies
            if (movies != null) {
                screen?.showMovies(movies)
            }
        }
    }
}