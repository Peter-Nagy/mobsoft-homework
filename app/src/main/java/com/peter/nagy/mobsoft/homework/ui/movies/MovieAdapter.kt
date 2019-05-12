package com.peter.nagy.mobsoft.homework.ui.movies

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.peter.nagy.mobsoft.homework.R
import io.swagger.client.model.MovieListObject
import kotlinx.android.synthetic.main.movie_list_item.view.description
import kotlinx.android.synthetic.main.movie_list_item.view.thumbnail
import kotlinx.android.synthetic.main.movie_list_item.view.title

class MovieAdapter(val context: Context) : ListAdapter<MovieListObject, MovieAdapter.MovieListObjectViewHolder>(ListItemCallback()) {
    var onItemClick: ((MovieListObject) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieListObjectViewHolder, position: Int) {
        val item = getItem(position)
        (holder as MovieListObjectViewHolder).bind(item, position)
    }

    class ListItemCallback : DiffUtil.ItemCallback<MovieListObject>() {
        override fun areItemsTheSame(oldItem: MovieListObject, newItem: MovieListObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieListObject, newItem: MovieListObject): Boolean {
            return false
        }
    }

    inner class MovieListObjectViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView) {
        val title = movieView.title
        val thumbnail = movieView.thumbnail
        val description = movieView.description

        fun bind(movie : MovieListObject, position : Int) {
            title.text = movie.title
            description.text = movie.overview
            val url = "https://image.tmdb.org/t/p/w500/" + movie.backdropPath
            Glide.with(context).load(url).into(thumbnail)
            itemView.setOnClickListener {
                onItemClick?.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieListObjectViewHolder(view)
    }
}