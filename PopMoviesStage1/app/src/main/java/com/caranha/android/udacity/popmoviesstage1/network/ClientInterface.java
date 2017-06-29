package com.caranha.android.udacity.popmoviesstage1.network;

import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieDataModel;
import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieResponseDataModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by caranha on 6/22/17.
 * these will be called depending on the user selected of top rated/ popular
 */

public interface ClientInterface {
    @GET("movie/top_rated")
    Call<MovieResponseDataModel> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponseDataModel> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieDataModel> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}