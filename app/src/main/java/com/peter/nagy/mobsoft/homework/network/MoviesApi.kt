package com.peter.nagy.mobsoft.homework.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search")
    fun getMovies(
        @Query("query") artist: String
        )
}