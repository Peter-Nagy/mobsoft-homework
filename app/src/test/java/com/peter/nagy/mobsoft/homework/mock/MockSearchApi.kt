package com.peter.nagy.mobsoft.homework.mock

import io.swagger.client.api.SearchApi
import io.swagger.client.model.InlineResponse200
import io.swagger.client.model.MovieListObject
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MockSearchApi: SearchApi {
    override fun searchMovie(query: String): Call<InlineResponse200> {
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

}