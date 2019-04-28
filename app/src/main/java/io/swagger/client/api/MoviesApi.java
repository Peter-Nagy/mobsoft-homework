package io.swagger.client.api;

import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.MovieDetailsObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MoviesApi {
  /**
   * Get Details
   * Get the details of a movie.
   * @param movieId  (required)
   * @return Call&lt;MovieDetailsObject&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("movie/{movie_id}")
  Call<MovieDetailsObject> movieDetails(
    @retrofit2.http.Path("movie_id") Integer movieId
  );

  /**
   * Get Popular
   * Get list of popular movies.
   * @return Call&lt;InlineResponse200&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("movie/popular")
  Call<InlineResponse200> popularMovies();
    

}
