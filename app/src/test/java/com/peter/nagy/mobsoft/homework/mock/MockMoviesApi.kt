package com.peter.nagy.mobsoft.homework.mock

import io.swagger.client.model.InlineResponse200
import io.swagger.client.model.MovieDetailsObject
import io.swagger.client.model.MovieListObject
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

inline fun mockMovieDetails(movieId: Int?): Call<MovieDetailsObject> {
    val movieDetails = MovieDetailsObject()
    movieDetails.backdropPath = "backgropPath"
    movieDetails.id = movieId
    movieDetails.overview = "overview"

    val call = object : Call<MovieDetailsObject> {
        @Throws(IOException::class)
        override fun execute(): Response<MovieDetailsObject> {
            return Response.success(movieDetails)
        }

        override fun enqueue(callback: Callback<MovieDetailsObject>) {

        }

        override fun isExecuted(): Boolean {
            return false
        }

        override fun cancel() {

        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun clone(): Call<MovieDetailsObject> {
            return this
        }

        override fun request(): Request? {
            return null
        }
    }

    return call
}

inline fun mockPopularMovies(): Call<InlineResponse200> {
    val response = InlineResponse200()

    val movie1 = MovieListObject()
    movie1.backdropPath = "backdrop1"
    movie1.title = "title1"
    movie1.overview = "overview1"
    val movie2 = MovieListObject()
    movie2.backdropPath = "backdrop1"
    movie2.title = "title1"
    movie2.overview = "overview1"

    response.results = listOf(
        movie1, movie2
    )

    val call = object : Call<InlineResponse200> {
        @Throws(IOException::class)
        override fun execute(): Response<InlineResponse200> {
            return Response.success(response)
        }

        override fun enqueue(callback: Callback<InlineResponse200>) {

        }

        override fun isExecuted(): Boolean {
            return false
        }

        override fun cancel() {

        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun clone(): Call<InlineResponse200> {
            return this
        }

        override fun request(): Request? {
            return null
        }
    }

    return call
}

inline fun mockPopularMoviesFailing(): Call<InlineResponse200> {
    val call = object : Call<InlineResponse200> {
        @Throws(IOException::class)
        override fun execute(): Response<InlineResponse200> {
            return Response.error(404, ResponseBody.create(MediaType.get("Application/json"), "{}"))
        }

        override fun enqueue(callback: Callback<InlineResponse200>) {

        }

        override fun isExecuted(): Boolean {
            return false
        }

        override fun cancel() {

        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun clone(): Call<InlineResponse200> {
            return this
        }

        override fun request(): Request? {
            return null
        }
    }
    return call
}

inline fun mockMovieDetailsFailing(movieId: Int?): Call<MovieDetailsObject> {
    val call = object : Call<MovieDetailsObject> {
        @Throws(IOException::class)
        override fun execute(): Response<MovieDetailsObject> {
            return Response.error(404, ResponseBody.create(MediaType.get("Application/json"), "{}"))
        }

        override fun enqueue(callback: Callback<MovieDetailsObject>) {

        }

        override fun isExecuted(): Boolean {
            return false
        }

        override fun cancel() {

        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun clone(): Call<MovieDetailsObject> {
            return this
        }

        override fun request(): Request? {
            return null
        }
    }
    return call
}