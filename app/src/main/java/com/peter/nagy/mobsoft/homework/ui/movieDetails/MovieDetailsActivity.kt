package com.peter.nagy.mobsoft.homework.ui.movieDetails

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.peter.nagy.mobsoft.homework.R
import com.peter.nagy.mobsoft.homework.db.AppDatabase
import com.peter.nagy.mobsoft.homework.injector
import com.peter.nagy.mobsoft.homework.ui.movies.MoviesActivity
import io.swagger.client.model.MovieDetailsObject
import kotlinx.android.synthetic.main.activity_details.description
import kotlinx.android.synthetic.main.activity_details.image
import kotlinx.android.synthetic.main.activity_details.like_image
import kotlinx.android.synthetic.main.activity_details.movie_title
import kotlinx.android.synthetic.main.activity_details.rating
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsScreen {
    @Inject
    lateinit var moviesPresenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        injector.inject(this)
        supportActionBar?.hide()
        like_image.setOnClickListener {
            moviesPresenter.likePressed()
        }
    }

    override fun onStart() {
        super.onStart()
        val movieId = intent.getIntExtra(MoviesActivity.KEY_MOVIE_ID, 0)
        moviesPresenter.attachScreen(this, movieId)
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.detachScreen()
    }

    override fun showMovie(movie: MovieDetailsObject) {
        val url = "https://image.tmdb.org/t/p/w500/" + movie.backdropPath
        Glide.with(applicationContext).load(url).into(image)
        description.text = movie.overview
        rating.text = movie.voteAverage.toString()
        movie_title.text = movie.title
    }

    override fun removeLike() {
        like_image.drawable.alpha = 50
    }

    override fun showLike() {
        like_image.drawable.alpha = 255
    }
}