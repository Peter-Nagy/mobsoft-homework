package com.peter.nagy.mobsoft.homework.ui.movieDetails

import com.peter.nagy.mobsoft.homework.interactor.movies.LikeInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.MoviesInteractor
import com.peter.nagy.mobsoft.homework.interactor.movies.event.GetMovieEvent
import com.peter.nagy.mobsoft.homework.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor

class MovieDetailsPresenter(private val executor: Executor,
                            private val moviesInteractor: MoviesInteractor,
                            private val likeInteractor: LikeInteractor) : Presenter<MovieDetailsScreen>() {
    var movieId: Int = 0
    fun attachScreen(screen: MovieDetailsScreen, movieId: Int) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
        this.movieId = movieId
        loadMovie()
    }
    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }
    private fun loadMovie() {
        executor.execute {
            moviesInteractor.getMovie(movieId)
        }
    }

    fun likePressed() {
        executor.execute {
            if (likeInteractor.isLiked(movieId)) {
                likeInteractor.dislikeMovie(movieId)
                screen?.removeLike()
            } else {
                likeInteractor.likeMovie(movieId)
                screen?.showLike()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun showMovie(movieEvent: GetMovieEvent) {
        if (movieEvent.throwable != null) {
            movieEvent.throwable?.printStackTrace()
        } else {
            val movie = movieEvent.movie
            if (movie != null) {
                screen?.showMovie(movie)
                this.movieId = movie.id
                executor.execute {
                    if (likeInteractor.isLiked(movie.id)) {
                        screen?.showLike()
                    } else {
                        screen?.removeLike()
                    }
                }
            }
        }
    }
}