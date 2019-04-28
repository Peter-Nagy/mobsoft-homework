package io.swagger.client.api;

import io.swagger.client.model.InlineResponse200;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SearchApi {
  /**
   * Search Movies
   * Search for movies.
   * @param query Query string to search (required)
   * @return Call&lt;InlineResponse200&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("search/movie")
  Call<InlineResponse200> searchMovie(
    @retrofit2.http.Query("query") String query
  );

}
