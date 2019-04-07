package com.peter.nagy.mobsoft.homework.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("movies")
    var movies: List<Movie>? = null
)