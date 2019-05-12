package com.peter.nagy.mobsoft.homework.ui.movies

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SearchView
import com.peter.nagy.mobsoft.homework.R
import com.peter.nagy.mobsoft.homework.injector
import com.peter.nagy.mobsoft.homework.ui.movieDetails.MovieDetailsActivity
import io.swagger.client.model.MovieListObject
import kotlinx.android.synthetic.main.activity_main.searchView

import javax.inject.Inject
import com.google.firebase.analytics.FirebaseAnalytics



class MoviesActivity : AppCompatActivity(), MoviesScreen {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main)
        injector.inject(this)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MovieAdapter(context = applicationContext)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.onItemClick = { movie ->
            moviesPresenter.showMovie(movie.id)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                moviesPresenter.searchTermChanged(query)
                return false
            }

        })
    }

    override fun onStart() {
        super.onStart()
        moviesPresenter.attachScreen(this)
        moviesPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        moviesPresenter.detachScreen()
    }

    override fun showMovies(movies: List<MovieListObject>) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "MOVIES_SHOWN")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MOVIES_SHOWN")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "movie list")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        viewAdapter.submitList(movies)
    }

    override fun showMovie(movieId: Int) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "MOVIE_TAPPED")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MOVIE_TAPPED")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "movie")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(KEY_MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
    }
}