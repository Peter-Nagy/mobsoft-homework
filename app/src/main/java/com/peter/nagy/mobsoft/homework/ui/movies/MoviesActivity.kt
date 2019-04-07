package com.peter.nagy.mobsoft.homework.ui.movies

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.peter.nagy.mobsoft.homework.R
import com.peter.nagy.mobsoft.homework.injector
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.content_main.button

import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), MoviesScreen {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)
        button.setOnClickListener {
            moviesPresenter.buttonPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        moviesPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.detachScreen()
    }

    override fun showMovies(searchTerm: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMovie(movieId: String) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(KEY_MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
    }
}