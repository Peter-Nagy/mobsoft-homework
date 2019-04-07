package com.peter.nagy.mobsoft.homework.ui.movieDetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.peter.nagy.mobsoft.homework.R
import com.peter.nagy.mobsoft.homework.injector
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsScreen {

    @Inject
    lateinit var moviesPresenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        moviesPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.detachScreen()
    }

    override fun showMovie(movieId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}